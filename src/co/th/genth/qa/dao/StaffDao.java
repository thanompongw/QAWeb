/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.dao
 * Program ID 	            :  StaffDao.java
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

import co.th.genth.qa.domain.Staff;
import co.th.genth.qa.exception.CommonException;

public interface StaffDao {

	/**
	 * Get staff data from a database by id.
	 * 
	 * @return staff Staff
	 */
	public Staff findById(final String staffCode) throws CommonException;

	/**
	 * Get staff data from a database by staff name.
	 * 
	 * @return list of staff
	 */
	public List<Staff> search(final Staff staff) throws CommonException;
	
	/**
	* Retrieves all staff data from a database.
	* 
	* @return list of staff
	*/
	public List<Staff> list() throws CommonException;
	 
	/**
	* Add a new staff
	* 
	* @param staff the new staff
	*/
	public void add(final Staff staff) throws CommonException;
	 
	/**
	* Edit an existing staff
	* 
	* @param staff the existing staff
	*/
	public void edit(final Staff staff) throws CommonException;
	 
	/**
	* Remove an existing staff
	* 
	* @param staff the existing staff
	*/
	public void remove(final Staff staff) throws CommonException;
	
}
