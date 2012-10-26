/******************************************************
 * Program History
 * 
 * Project Name	            :  APS
 * Client Name				:  
 * Package Name             :  co.th.genth.aps.domain
 * Program ID 	            :  AccessLevel.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  21 มี.ค. 2555
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa.domain;

import java.io.Serializable;

/**
 * @author Thanompong.W
 *
 */
public class AccessLevel implements Serializable {
	
	/** serialVersionUID property **/
    private static final long serialVersionUID = -1420526028141438792L;
    
    private Integer id;
    private String description;

	/**
	 * Default Constructor of AccessLevel.java
	 */
	public AccessLevel() {
	}

	/**
     * @return the id
     */
    public Integer getId() {
    	return id;
    }

	/**
     * @param id the id to set
     */
    public void setId(Integer id) {
    	this.id = id;
    }

	/**
     * @return the description
     */
    public String getDescription() {
    	return description;
    }

	/**
     * @param description the description to set
     */
    public void setDescription(String description) {
    	this.description = description;
    }
	
}
