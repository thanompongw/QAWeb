/******************************************************
 * Program History
 * 
 * Project Name	            :  QAWeb
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.web.model
 * Program ID 	            :  ErrorMessage.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  1/11/2012
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa.exception;

import java.io.Serializable;

/**
 * @author Thanompong.W
 *
 */
public class Message implements Serializable {
	
	/** serialVersionUID property **/
    private static final long serialVersionUID = 298432234151730486L;

	private String fieldName;
	private String message;

	/**
	 * Default Constructor of ErrorMessage.java
	 */
	public Message() {
	}

	/**
     * @return the fieldName
     */
    public String getFieldName() {
    	return fieldName;
    }

	/**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(String fieldName) {
    	this.fieldName = fieldName;
    }

	/**
     * @return the message
     */
    public String getMessage() {
    	return message;
    }

	/**
     * @param message the message to set
     */
    public void setMessage(String message) {
    	this.message = message;
    }
	
}
