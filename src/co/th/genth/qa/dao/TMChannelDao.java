/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.dao
 * Program ID 	            :  TMChannelDao.java
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

import co.th.genth.qa.domain.TMChannel;
import co.th.genth.qa.exception.CommonException;

public interface TMChannelDao {

	/**
	 * Get staff data from a database by id.
	 * 
	 * @return tmChannel TMChannel
	 */
	public TMChannel findById(final Integer tmChannelId) throws CommonException;

	/**
	 * Get tmChannel data from a database by tmChannel name.
	 * 
	 * @return list of tmChannel
	 */
	public List<TMChannel> search(final TMChannel tmChannel) throws CommonException;
	
	/**
	* Retrieves all tmChannel data from a database.
	* 
	* @return list of tmChannel
	*/
	public List<TMChannel> list() throws CommonException;
	 
	/**
	* Add a new tmChannel
	* 
	* @param tmChannel the new tmChannel
	*/
	public void add(final TMChannel tmChannel) throws CommonException;
	 
	/**
	* Edit an existing tmChannel
	* 
	* @param tmChannel the existing tmChannel
	*/
	public void edit(final TMChannel tmChannel) throws CommonException;
	 
	/**
	* Remove an existing tmChannel
	* 
	* @param tmChannel the existing tmChannel
	*/
	public void remove(final TMChannel tmChannel) throws CommonException;
	
}
