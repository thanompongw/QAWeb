/******************************************************
 * Program History
 * 
 * Project Name	            :  CCS
 * Client Name				:  
 * Package Name             :  co.th.genth.ccs.domain
 * Program ID 	            :  Status.java
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

/**
 * @author Thanompong.W
 *
 */
public class Status implements Serializable {
	
	/** serialVersionUID property **/
    private static final long serialVersionUID = 2856793230629082660L;
    
    private String statusCode;
    private String statusName;
    private String statusDesc;

	/**
	 * Default Constructor of Status.java
	 */
	public Status() {
	}

	/**
     * @return the statusCode
     */
    public String getStatusCode() {
    	return statusCode;
    }

	/**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
    	this.statusCode = statusCode;
    }

	/**
     * @return the statusName
     */
    public String getStatusName() {
    	return statusName;
    }

	/**
     * @param statusName the statusName to set
     */
    public void setStatusName(String statusName) {
    	this.statusName = statusName;
    }

	/**
     * @return the statusDesc
     */
    public String getStatusDesc() {
    	return statusDesc;
    }

	/**
     * @param statusDesc the statusDesc to set
     */
    public void setStatusDesc(String statusDesc) {
    	this.statusDesc = statusDesc;
    }
	
}
