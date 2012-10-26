/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.dao
 * Program ID 	            :  UserDao.java
 * Program Description	    :  A custom DAO for accessing data from the database.
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
package co.th.genth.qa.dao;

import java.util.List;

import co.th.genth.qa.domain.*;
import co.th.genth.qa.exception.CommonException;

public interface UserDao {

	/**
	 * Simulates retrieval of data from a database.
	 * 
	 * @return user User
	 */
	public User findByUserName(String userName) throws CommonException;
	
	/**
	* Retrieves all user
	* 
	* @return list of user
	*/
	public List<User> list() throws CommonException;
	
	/**
	* Get user information by user id.
	* 
	* @return user User
	*/
	public User getUserById(String userId) throws CommonException;

	
	/**
	* Retrieves all user role
	* 
	* @return list of Role
	*/
	public List<Role> getRoleList() throws CommonException;
	 
	/**
	* Add a new user
	* 
	* @param user the new user
	*/
	public void add(final User user) throws CommonException;
	 
	/**
	* Remove an existing user
	* 
	* @param user the existing user
	*/
	public void remove(final User user) throws CommonException;
	 
	/**
	* Edit an existing user
	* 
	* @param user the existing user
	*/
	public void edit(final User user) throws CommonException;
	
	/**
	* Retrieves all user Authorities
	* 
	* @param id String user Id
	* 
	* @return user role UserRole
	* @throws CommonException 
	*/
	public UserRole getAuthorities(String userId) throws CommonException;
	
	/**
	* Add a new role of user
	* 
	* @param userId String
	* @param roleId Integer
	* 
	* @return Boolean true : exist, false : not exist
	* @throws CommonException 
	*/
	public Boolean existRole(final String userId, final Integer roleId) throws CommonException;
	
	/**
	* Add a new role of user
	* 
	* @param userId String
	* @param roleId Integer
	* @throws CommonException 
	*/
	public void addRole(final String userId, final Integer roleId) throws CommonException;
	
	/**
	* delete existing role of user
	* 
	* @param userId String
	* @param roleId Integer
	* @throws CommonException 
	*/
	public void deleteRole(final String userId, final Integer roleId) throws CommonException;

}
