/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.services
 * Program ID 	            :  UserServices.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  26/10/2012
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa.services;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import co.th.genth.qa.common.util.ErrorUtil;
import co.th.genth.qa.dao.UserDao;
import co.th.genth.qa.domain.Role;
import co.th.genth.qa.domain.User;
import co.th.genth.qa.exception.CommonException;


/**
 * @author Thanompong.W
 *
 */
@Service(value = "userService")
public class UserServices {
	
	private static Logger logger = Logger.getLogger(UserServices.class);
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	private PlatformTransactionManager txManager;
	private TransactionStatus txStatus;
	
	/**
	 * Default Constructor of UserServices.java
	 */
	public UserServices() {
	}

	/**
     * @param txManager the txManager to set
     */
    public void setTxManager(PlatformTransactionManager txManager) {
    	this.txManager = txManager;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void createUser(User user) throws CommonException {
    	
    	TransactionDefinition txDef = new DefaultTransactionDefinition();
		txStatus = txManager.getTransaction(txDef);
		
		try {
		
			userDao.add(user);
			
			if (user.getUserRole() != null) {
				for (Role role : user.getUserRole().getRoles()) {
					
					userDao.addRole(user.getUserId(), role.getRoleId());					
				}
			}
	        
	        txManager.commit(txStatus);
	    } catch (CommonException cx) {
	    	txManager.rollback(txStatus);
	    	throw cx;
	    } catch (Exception ex) {
	    	txManager.rollback(txStatus);
			logger.error(ex.getMessage(), ex);
			throw ErrorUtil.generateError("MSTD0004AERR", ex.getMessage());
	    }
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updateUser(User user) throws CommonException {
    	
    	TransactionDefinition txDef = new DefaultTransactionDefinition();
		txStatus = txManager.getTransaction(txDef);
		
		try {
			
			User existingUser = userDao.getUserById(user.getUserId());
			
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setPassword(user.getPassword());
			existingUser.setEmail(user.getEmail());
			existingUser.setLastLoginTime(user.getLastLoginTime());
			existingUser.setInvalidLogin(user.getInvalidLogin());
			existingUser.setActiveFlag(user.getActiveFlag());
			existingUser.setPasswordStatus(user.getPasswordStatus());
			existingUser.setUpdatedBy(user.getUpdatedBy());
			existingUser.setUpdatedDate(user.getUpdatedDate());
		
			userDao.edit(existingUser);
			
			if (user.getUserRole() != null) {
				// Incase add new role or remove some existing role.
				for (Role role : user.getUserRole().getRoles()) {
					
					if (!userDao.existRole(user.getUserId(), role.getRoleId())) {
						userDao.addRole(user.getUserId(), role.getRoleId());
					}
				}
				
				if (existingUser.getUserRole() != null) {
					for (Role role : existingUser.getUserRole().getRoles()) {
						if (!user.getUserRole().getRoles().contains(role)) {
							userDao.deleteRole(user.getUserId(), role.getRoleId());
						}
					}
				}
			} else {
				// Incase not add any role then remove all existing role.
				if (existingUser.getUserRole() != null) {
					for (Role role : existingUser.getUserRole().getRoles()) {
						userDao.deleteRole(user.getUserId(), role.getRoleId());
					}
				}
			}
	        
	        txManager.commit(txStatus);
	    } catch (CommonException cx) {
	    	txManager.rollback(txStatus);
	    	throw cx;
	    } catch (Exception ex) {
	    	txManager.rollback(txStatus);
			logger.error(ex.getMessage(), ex);
			throw ErrorUtil.generateError("MSTD0004AERR", ex.getMessage());
	    }
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void deleteUser(User user) throws CommonException {
    	
    	TransactionDefinition txDef = new DefaultTransactionDefinition();
		txStatus = txManager.getTransaction(txDef);
		
		try {
			
			User existingUser = userDao.getUserById(user.getUserId());
			
			existingUser.setActiveFlag(user.getActiveFlag());
			existingUser.setUpdatedBy(user.getUpdatedBy());
			existingUser.setUpdatedDate(user.getUpdatedDate());
		
			userDao.remove(existingUser);
			
			// Incase not add any role then remove all existing role.
			if (existingUser.getUserRole() != null) {
				for (Role role : existingUser.getUserRole().getRoles()) {
					userDao.deleteRole(user.getUserId(), role.getRoleId());
				}
			}
	        
	        txManager.commit(txStatus);
	    } catch (CommonException cx) {
	    	txManager.rollback(txStatus);
	    	throw cx;
	    } catch (Exception ex) {
	    	txManager.rollback(txStatus);
			logger.error(ex.getMessage(), ex);
			throw ErrorUtil.generateError("MSTD0004AERR", ex.getMessage());
	    }
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void unlocked(User user) throws CommonException {
    	
    	TransactionDefinition txDef = new DefaultTransactionDefinition();
		txStatus = txManager.getTransaction(txDef);
		
		try {
			
			User existingUser = userDao.getUserById(user.getUserId());
			
			existingUser.setPassword(user.getPassword());
			existingUser.setInvalidLogin(0);
			existingUser.setActiveFlag(user.getActiveFlag());
			existingUser.setPasswordStatus(user.getPasswordStatus());
			existingUser.setUpdatedBy(user.getUpdatedBy());
			existingUser.setUpdatedDate(user.getUpdatedDate());
		
			userDao.edit(existingUser);
	        
	        txManager.commit(txStatus);
	    } catch (CommonException cx) {
	    	txManager.rollback(txStatus);
	    	throw cx;
	    } catch (Exception ex) {
	    	txManager.rollback(txStatus);
			logger.error(ex.getMessage(), ex);
			throw ErrorUtil.generateError("MSTD0004AERR", ex.getMessage());
	    }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public String getRoles() throws CommonException {

		// Initialize our custom agent model wrapper
		StringBuilder comboList = new StringBuilder();
		
		try {
			
			// Retrieve all agents from the service
			List<Role> roleList = userDao.getRoleList();
			
			// Assign the result from the service to this model
			int round = 0;
			for (Role role : roleList) {
				round++;
				comboList.append(role.getRoleId().toString());
				comboList.append(":");
				comboList.append(role.getRoleName());
				if (round < roleList.size()) {
					comboList.append(";");
				}
			}
	        
	        txManager.commit(txStatus);
	    } catch (CommonException cx) {
	    	txManager.rollback(txStatus);
	    	throw cx;
	    } catch (Exception ex) {
	    	txManager.rollback(txStatus);
			logger.error(ex.getMessage(), ex);
			throw ErrorUtil.generateError("MSTD0004AERR", ex.getMessage());
	    }
		
		return comboList.toString();
    }
	
}
