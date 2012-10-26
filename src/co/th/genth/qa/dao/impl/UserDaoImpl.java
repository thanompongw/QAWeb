/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.ccs.dao.impl
 * Program ID 	            :  UserDaoImpl.java
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
package co.th.genth.qa.dao.impl;

import java.sql.*;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.genth.qa.common.util.ErrorUtil;
import co.th.genth.qa.domain.*;
import co.th.genth.qa.dao.UserDao;
import co.th.genth.qa.exception.CommonException;

/**
 * @author Thanompong.W
 *
 */
@Service("userDao")
@Transactional(rollbackFor = CommonException.class)
public class UserDaoImpl implements UserDao {
	
	private static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	private JdbcTemplate ccsJdbcTemplate;
	
	/**
	 * Default Constructor of UserDaoImpl.java
	 */
	public UserDaoImpl() {
	}

	private static final class UserRowMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int i) throws SQLException {
        	User user = new User();
        	
        	user.setUserId(rs.getString("USER_ID"));
        	user.setUserName(rs.getString("USER_NAME"));
        	user.setPassword(rs.getString("PASSWORD"));
        	user.setFirstName(rs.getString("FIRST_NAME"));
        	user.setLastName(rs.getString("LAST_NAME"));
        	user.setEmail(rs.getString("EMAIL"));
        	user.setLastLoginTime(rs.getTimestamp("LAST_LOGIN_TIME"));
        	user.setInvalidLogin(rs.getInt("INVALID_LOGIN"));
        	user.setActiveFlag(rs.getString("ACTIVE_FLAG"));
        	user.setPasswordStatus(rs.getInt("PASSWORD_STATUS"));
        	user.setCreatedBy(rs.getString("CREATED_BY"));
        	user.setCreatedDate(rs.getTimestamp("CREATED_DT"));
        	user.setUpdatedBy(rs.getString("UPDATED_BY"));
        	user.setUpdatedDate(rs.getTimestamp("UPDATED_DT"));        	
        	
	        return user;
        }
		
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.UserDao#findByUserName(java.lang.String)
	 */
	public User findByUserName(String userName) throws CommonException {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT USER_ID, ");
		sql.append("       USER_NAME, ");
		sql.append("       FIRST_NAME, ");
		sql.append("       LAST_NAME, ");
		sql.append("       PASSWORD, ");
		sql.append("       EMAIL, ");
		sql.append("       LAST_LOGIN_TIME, ");
		sql.append("       INVALID_LOGIN, ");
		sql.append("       ACTIVE_FLAG, ");
		sql.append("       PASSWORD_STATUS, ");
		sql.append("       CREATED_DT, ");
		sql.append("       CREATED_BY, ");
		sql.append("       UPDATED_DT, ");
		sql.append("       UPDATED_BY ");
		sql.append("FROM TB_M_USER_INFO ");
		sql.append("WHERE USER_NAME = ?");
		
		User user = null;
		
		try {
			
			user = ccsJdbcTemplate.queryForObject(sql.toString(), 
			                                   new String[] { userName },
			                                   new UserRowMapper());
			
			if (user != null) {
				UserRole userRole = this.getAuthorities(user.getUserId());
				
				user.setUserRole(userRole);
			}
		} catch (EmptyResultDataAccessException ex) {
			return null;
		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}

		return user;
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.UserDao#list()
	 */
	public List<User> list() throws CommonException {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT USER_ID, ");
		sql.append("       USER_NAME, ");
		sql.append("       FIRST_NAME, ");
		sql.append("       LAST_NAME, ");
		sql.append("       PASSWORD, ");
		sql.append("       EMAIL, ");
		sql.append("       LAST_LOGIN_TIME, ");
		sql.append("       INVALID_LOGIN, ");
		sql.append("       ACTIVE_FLAG, ");
		sql.append("       PASSWORD_STATUS, ");
		sql.append("       CREATED_DT, ");
		sql.append("       CREATED_BY, ");
		sql.append("       UPDATED_DT, ");
		sql.append("       UPDATED_BY ");
		sql.append("FROM TB_M_USER_INFO ");
		
		List<User> users = null;
		
		try {
			
			users = ccsJdbcTemplate.query(sql.toString(), 
			                           new UserRowMapper());
			
		} catch (EmptyResultDataAccessException ex) {
			return null;
		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}

		return users;
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.UserDao#getUserId()
	 */
	public String getUserId() throws CommonException {
		
		String userName = 
				(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		User user = this.findByUserName(userName);
		if (user != null) {
			return user.getUserId();
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.UserDao#getUserById(java.lang.String)
	 */
	public User getUserById(String userId) throws CommonException {
		
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT USER_ID, ");
		sql.append("       USER_NAME, ");
		sql.append("       FIRST_NAME, ");
		sql.append("       LAST_NAME, ");
		sql.append("       PASSWORD, ");
		sql.append("       EMAIL, ");
		sql.append("       LAST_LOGIN_TIME, ");
		sql.append("       INVALID_LOGIN, ");
		sql.append("       ACTIVE_FLAG, ");
		sql.append("       PASSWORD_STATUS, ");
		sql.append("       CREATED_DT, ");
		sql.append("       CREATED_BY, ");
		sql.append("       UPDATED_DT, ");
		sql.append("       UPDATED_BY ");
		sql.append("FROM TB_M_USER_INFO ");
		sql.append("WHERE USER_ID = ?");
		
		User user = null;
		
		try {
			
			user = ccsJdbcTemplate.queryForObject(sql.toString(), 
			                                   new String[] { userId },
			                                   new UserRowMapper());
			
			if (user != null) {
				UserRole userRole = this.getAuthorities(user.getUserId());
				
				user.setUserRole(userRole);
			}
		} catch (EmptyResultDataAccessException ex) {
			return null;
		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}

		return user;
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.UserDao#getRoleList()
	 */
	public List<Role> getRoleList() throws CommonException {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ROLE_ID, ");
		sql.append("       ROLE_NAME, ");
		sql.append("       ROLE_DESC, ");
		sql.append("       CREATED_DT, ");
		sql.append("       CREATED_BY, ");
		sql.append("       UPDATED_DT, ");
		sql.append("       UPDATED_BY ");
		sql.append("FROM TB_M_ROLE ");
		
		List<Role> roles = null;
		
		try {
			
			RowMapper<Role> rowMapper = new RowMapper<Role>() {
				
				public Role mapRow(ResultSet rs, int i) throws SQLException {
					Role role = new Role();
					
					role.setRoleId(rs.getInt("ROLE_ID"));
					role.setRoleName(rs.getString("ROLE_NAME"));
					role.setRoleDesc(rs.getString("ROLE_DESC"));
					role.setCreatedBy(rs.getString("CREATED_BY"));
					role.setCreatedDate(rs.getTimestamp("CREATED_DT"));
					role.setUpdatedBy(rs.getString("UPDATED_BY"));
					role.setUpdatedDate(rs.getTimestamp("UPDATED_DT"));  
					
					return role;
				}
			};
			
			roles = ccsJdbcTemplate.query(sql.toString(), rowMapper);
			
		} catch (EmptyResultDataAccessException ex) {
			return null;
		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}

		return roles;
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.UserDao#add(co.th.genth.ccs.domain.User)
	 */
	public void add(final User user) throws CommonException {
		final StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO TB_M_USER_INFO ");
		sql.append("           (USER_ID, ");
		sql.append("           USER_NAME, ");
		sql.append("           FIRST_NAME, ");
		sql.append("           LAST_NAME, ");
		sql.append("           PASSWORD, ");
		sql.append("           EMAIL, ");
		sql.append("           ACTIVE_FLAG, ");
		sql.append("           PASSWORD_STATUS, ");
		sql.append("           CREATED_DT, ");
		sql.append("           CREATED_BY, ");
		sql.append("           UPDATED_DT, ");
		sql.append("           UPDATED_BY) ");
		sql.append("VALUES ");
		sql.append("           (?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?, ");
		sql.append("           ?) ");
		
		try {
		
			PreparedStatementSetter pss = new PreparedStatementSetter() {
				
				public void setValues(PreparedStatement pstm) throws SQLException {
					int parameterIndex = 1;
					
					pstm.setString(parameterIndex++, user.getUserId());
					pstm.setString(parameterIndex++, user.getUserName());
					pstm.setString(parameterIndex++, user.getFirstName());
					pstm.setString(parameterIndex++, user.getLastName());
					pstm.setString(parameterIndex++, user.getPassword());
					pstm.setString(parameterIndex++, user.getEmail());
					pstm.setString(parameterIndex++, user.getActiveFlag());
					pstm.setInt(parameterIndex++, user.getPasswordStatus());
					pstm.setString(parameterIndex++, user.getCreatedBy());
					pstm.setTimestamp(parameterIndex++, user.getCreatedDate());
					pstm.setString(parameterIndex++, user.getUpdatedBy());
					pstm.setTimestamp(parameterIndex++, user.getUpdatedDate());
					
				}
			};
	
			ccsJdbcTemplate.update(sql.toString(), pss);

		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.UserDao#remove(co.th.genth.ccs.domain.User)
	 */
	public void remove(final User user) throws CommonException {
		
		final StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE TB_M_USER_INFO ");
		sql.append("SET ACTIVE_FLAG = ?, ");
		sql.append("    UPDATED_DT = ?, ");
		sql.append("    UPDATED_BY = ? ");
		sql.append("WHERE USER_ID = ? ");
		
		try {
		
			PreparedStatementSetter pss = new PreparedStatementSetter() {
				
				public void setValues(PreparedStatement pstm) throws SQLException {
					int parameterIndex = 1;
					
					pstm.setString(parameterIndex++, user.getActiveFlag());
					pstm.setString(parameterIndex++, user.getUpdatedBy());
					pstm.setTimestamp(parameterIndex++, user.getUpdatedDate());
					pstm.setString(parameterIndex++, user.getUserId());
					
				}
			};
	
			ccsJdbcTemplate.update(sql.toString(), pss);

		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
	}
	
	/* (non-Javadoc)
	 * @see co.th.genth.ccs.dao.UserDao#edit(co.th.genth.ccs.domain.User)
	 */
	public void edit(final User user) throws CommonException {

		final StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE TB_M_USER_INFO");
		sql.append("SET FIRST_NAME = ?, ");
		sql.append("    LAST_NAME = ?, ");
		sql.append("    PASSWORD = ?, ");
		sql.append("    EMAIL = ?, ");
		sql.append("    LAST_LOGIN_TIME = ?, ");
		sql.append("    INVALID_LOGIN = ?, ");
		sql.append("    ACTIVE_FLAG = ?, ");
		sql.append("    PASSWORD_STATUS = ?, ");
		sql.append("    UPDATED_DT = ?, ");
		sql.append("    UPDATED_BY = ? ");
		sql.append("WHERE USER_ID = ?");

		try {
		
			PreparedStatementSetter pss = new PreparedStatementSetter() {
				
				public void setValues(PreparedStatement pstm) throws SQLException {
					int parameterIndex = 1;
					
					pstm.setString(parameterIndex++, user.getFirstName());
					pstm.setString(parameterIndex++, user.getLastName());
					pstm.setString(parameterIndex++, user.getPassword());
					pstm.setString(parameterIndex++, user.getEmail());
					pstm.setTimestamp(parameterIndex++, user.getLastLoginTime());
					pstm.setInt(parameterIndex++, user.getInvalidLogin());
					pstm.setString(parameterIndex++, user.getActiveFlag());
					pstm.setInt(parameterIndex++, user.getPasswordStatus());
					pstm.setString(parameterIndex++, user.getUpdatedBy());
					pstm.setTimestamp(parameterIndex++, user.getUpdatedDate());
					pstm.setString(parameterIndex++, user.getUserId());
					
				}
			};
	
			ccsJdbcTemplate.update(sql.toString(), pss);

		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
	}

	/* (non-Javadoc)
     * @see co.th.genth.ccs.dao.UserDao#getAuthorities(java.lang.String)
     */
    public UserRole getAuthorities(String userId) throws CommonException {
    	StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("	UR.ROLE_ID, ");
		sql.append("	R.ROLE_NAME ");
		sql.append("	R.ROLE_DESC ");
		sql.append("FROM TB_M_USER_ROLE UR, TB_M_ROLE R ");
		sql.append("WHERE UR.ROLE_ID  = R.ROLE_ID ");
		sql.append("AND   UR.USER_ID = ? ");
		
		UserRole userRole = new UserRole();
		
		List<Role> roles = null;
		
		try {
			
			RowMapper<Role> rowMapper = new RowMapper<Role>() {
				
				public Role mapRow(ResultSet rs, int i) throws SQLException {
					Role role = new Role();
					
					role.setRoleId(rs.getInt("ROLE_ID"));
					role.setRoleName(rs.getString("ROLE_NAME"));
					role.setRoleDesc(rs.getString("ROLE_DESC"));
					return role;
				}
			};
			
			roles = ccsJdbcTemplate.query(sql.toString(), 
			                           rowMapper, 
			                           userId);
			
			userRole.setUserId(userId);
			userRole.setRoles(roles);
		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
		return userRole;
    }

	/* (non-Javadoc)
     * @see co.th.genth.ccs.dao.UserDao#addRole(java.lang.String, java.lang.Integer)
     */
    public void addRole(final String userId, final Integer roleId) throws CommonException {
    	final StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO TB_M_USER_ROLE (USER_ID, ROLE_ID) VALUES (?, ?) ");
		
		try {
		
			PreparedStatementSetter pss = new PreparedStatementSetter() {
				
				public void setValues(PreparedStatement pstm) throws SQLException {
					int parameterIndex = 1;
					
					pstm.setString(parameterIndex++, userId);
					pstm.setInt(parameterIndex++, roleId);
					
				}
			};
	
			ccsJdbcTemplate.update(sql.toString(), pss);

		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
	    
    }

	/* (non-Javadoc)
     * @see co.th.genth.ccs.dao.UserDao#deleteRole(java.lang.String, java.lang.Integer)
     */
    public void deleteRole(final String userId, final Integer roleId) throws CommonException {
    	final StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE TB_M_USER_ROLE WHERE USER_ID = ? AND ROLE_ID = ? ");
		
		try {
		
			PreparedStatementSetter pss = new PreparedStatementSetter() {
				
				public void setValues(PreparedStatement pstm) throws SQLException {
					int parameterIndex = 1;
					
					pstm.setString(parameterIndex++, userId);
					pstm.setInt(parameterIndex++, roleId);
					
				}
			};
	
			ccsJdbcTemplate.update(sql.toString(), pss);

		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
	    
    }

	/* (non-Javadoc)
     * @see co.th.genth.ccs.dao.UserDao#existRole(java.lang.String, java.lang.Integer)
     */
    public Boolean existRole(final String userId, final Integer roleId) throws CommonException {
    	final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT COUNT(1) TB_M_USER_ROLE WHERE USER_ID = ? AND ROLE_ID = ? ");
		
		try {
	
			int result = ccsJdbcTemplate.queryForInt(sql.toString(), userId, roleId);
			
			if (result == 0) {
				return false;
			}

		} catch (Exception ex) {
    		logger.error(ex.getMessage(), ex);
    		throw ErrorUtil.generateError("MSTD0006AERR", ex.getMessage());
		}
		
		return true;
    }

	/**
     * @param ccsJdbcTemplate the ccsJdbcTemplate to set
     */
    public void setCcsJdbcTemplate(JdbcTemplate ccsJdbcTemplate) {
    	this.ccsJdbcTemplate = ccsJdbcTemplate;
    }
	
}
