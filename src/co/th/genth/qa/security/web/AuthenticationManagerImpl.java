/**
 * 
 */
package co.th.genth.qa.security.web;

import java.sql.Timestamp;
import java.util.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import co.th.genth.qa.common.util.ErrorUtil;
import co.th.genth.qa.common.util.MessageResolver;
import co.th.genth.qa.dao.UserDao;
import co.th.genth.qa.domain.*;
import co.th.genth.qa.exception.CommonException;


/**
 * @author Thanompong.W 
 * A custom authentication manager that allows access if
 *         the user details exist in the database and if the username and
 *         password are not the same. Otherwise, throw a
 *         {@link BadCredentialsException}
 */
public class AuthenticationManagerImpl implements AuthenticationManager {
	
	protected static Logger logger = Logger.getLogger(AuthenticationManagerImpl.class);
	
	// Our custom DAO layer
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="messageService")
	private MessageResolver messageResolver;
	
	// We need an Md5 encoder since our passwords in the database are Md5
	// encoded.
	private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
	
	public AuthenticationManagerImpl() {
	}
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		logger.info("####################################################################");
		logger.info("# " + new Date());
		logger.info("# Beginning Login to Agent Persistency System");
		logger.info("# USER Name:: " + auth.getName());
		
		// Init a database user object
		Timestamp lastLoginDatetime = new Timestamp(System.currentTimeMillis());
		User user = null;
		try {
			// Retrieve user details from database
			user = userDao.findByUserName(auth.getName());
		} catch (CommonException cx) {
			
			/*List<String> errors = ErrorUtil.getErrors(cx);
			
			logger.error(errors.get(0), cx);*/
			throw new BadCredentialsException("", cx);
		}
			
		if (user == null) {
			String msg = messageResolver.getMessageStr("MSTD0070AERR",
			                                           new String[] { auth.getName() });
			
			logger.warn(msg);
			throw new BadCredentialsException(msg);
		}
		
		if (user.getInvalidLogin() >= 3) {
			String msg = messageResolver.getMessageStr("MSTD0073AERR",
			                                           new String[] { auth.getName() });
			
			logger.warn(msg);
			throw new BadCredentialsException(msg);
		}
		
		if (user.getActiveFlag().equals("")) {
			String msg = messageResolver.getMessageStr("MSTD0072AERR",
			                                           new String[] { auth.getName() });
			
			logger.warn(msg);
			throw new BadCredentialsException(msg);
		}
		
		// Compare passwords
		// Make sure to encode the password first before comparing
		if (!passwordEncoder.isPasswordValid(user.getPassword(),
		                                     (String) auth.getCredentials(),
		                                     null)) {
			
			String msg = messageResolver.getMessageStr("MSTD0071AERR");
			
			logger.error(msg);
			
			user.setInvalidLogin(user.getInvalidLogin() + 1);
			user.setUpdatedDate(lastLoginDatetime);
			user.setUpdatedBy(user.getUserId());
			
			if (user.getInvalidLogin() == 3) {
				user.setActiveFlag("");
			}
			
			try {
	            userDao.edit(user);
            } catch (CommonException cx) {
    			
    			/*List<String> errors = ErrorUtil.getErrors(cx);
    			
    			logger.error(errors.get(0), cx);*/
    			throw new BadCredentialsException("", cx);
    		}
			
			throw new BadCredentialsException(msg);
		}
		
		user.setInvalidLogin(0);
		user.setLastLoginTime(lastLoginDatetime);
		user.setUpdatedDate(lastLoginDatetime);
		user.setUpdatedBy(user.getUserId());
		
		try {
	        userDao.edit(user);
        } catch (CommonException cx) {
			/*
			List<String> errors = ErrorUtil.getErrors(cx);
			
			logger.error(errors.get(0), cx);*/
			throw new BadCredentialsException("", cx);
		}
		
		return new UsernamePasswordAuthenticationToken(auth.getName(),
		                                               auth.getCredentials(),
		                                               getAuthorities(user.getUserRole()));
	}
	
	/**
	 * Retrieves the correct ROLE type depending on the access level, where
	 * access level is an Integer. Basically, this interprets the access value
	 * whether it's for a regular user or admin or supervisor.
	 * 
	 * @param list
	 *            of UserRole userRole.
	 * @return collection of granted authorities.
	 */
	public Collection<GrantedAuthority> getAuthorities(UserRole userRole) {
		// Create a list of grants for this user
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		
		// Get user role to granted access
		for (Role role : userRole.getRoles()) {
			logger.info("# Role:: " + role.getRoleName());
			authList.add(new GrantedAuthorityImpl(role.getRoleName()));
		}
		
		logger.info("# Login Successfully");
		logger.info("# Welcome to APS(Agent Persistency System)");
		logger.info("####################################################################");
		
		// Return list of granted authorities
		return authList;
	}
	
}
