/******************************************************
 * Program History
 * 
 * Project Name	            :  QAWeb
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.web.model
 * Program ID 	            :  ReferenceModel.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  1 ¾.Â. 2555
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa.web.model;

import java.io.Serializable;

/**
 * @author Thanompong.W
 *
 */
public class ReferenceModel implements Serializable {
	
	/** serialVersionUID property **/
    private static final long serialVersionUID = -678233322963714906L;
    
    private String code;
    private String value;

	/**
	 * Default Constructor of ReferenceModel.java
	 */
	public ReferenceModel() {
	}
	
	public ReferenceModel(String code, String value) {
		this.code = code;
		this.value = value;
	}

	/**
     * @return the code
     */
    public String getCode() {
    	return code;
    }

	/**
     * @param code the code to set
     */
    public void setCode(String code) {
    	this.code = code;
    }

	/**
     * @return the value
     */
    public String getValue() {
    	return value;
    }

	/**
     * @param value the value to set
     */
    public void setValue(String value) {
    	this.value = value;
    }
	
}
