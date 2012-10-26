/******************************************************
 * Program History
 * 
 * Project Name	            :  CCS
 * Client Name				:  
 * Package Name             :  co.th.genth.ccs.domain
 * Program ID 	            :  UserRole.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  Aug 7, 2012
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Thanompong.W
 *
 */
public class UserRole implements Serializable {
	
	/** serialVersionUID property **/
    private static final long serialVersionUID = 6662977525211375767L;
    
    private String userId;
    private List<Role> roles;

	/**
	 * Default Constructor of UserRole.java
	 */
	public UserRole() {
	}

	/**
     * @return the userId
     */
    public String getUserId() {
    	return userId;
    }

	/**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
    	this.userId = userId;
    }

	/**
     * @return the roles
     */
    public List<Role> getRoles() {
    	return roles;
    }

	/**
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
    	this.roles = roles;
    }
	
}
