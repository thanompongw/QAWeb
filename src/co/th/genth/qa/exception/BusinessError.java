/******************************************************
 * Program History
 * 
 * Project Name	            :  CRMReport
 * Client Name				:  
 * Package Name             :  co.th.genth.crm.exception
 * Program ID 	            :  BusinessError.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  9 มี.ค. 2555
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
 */
public class BusinessError implements Serializable {
	/** serialVersionUID property **/
	private static final long serialVersionUID = -2484349740965694331L;
	private String errorkey;
	private int errorType;
	private String[] substitutionValues;
	
	public final static int TYPE_INFO = 1;
	public final static int TYPE_ERROR = 2;
	public final static int TYPE_CRITICAL = 3;
	
	public BusinessError(String errorKey, int type, String[] args) {
		this.setErrorkey(errorKey);
		this.setErrorType(type);
		this.setSubstitutionValues(args);
		
	}
	
	public BusinessError(String errorKey, String[] args) {
		this.setErrorkey(errorKey);
		this.setSubstitutionValues(args);
	}
	
	public BusinessError(String errorKey, int type) {
		this.setErrorkey(errorKey);
		this.setErrorType(type);
		this.setSubstitutionValues(null);
		
	}
	
	public String getErrorkey() {
		return errorkey;
	}
	
	public void setErrorkey(String errorkey) {
		this.errorkey = errorkey;
	}
	
	public int getErrorType() {
		return errorType;
	}
	
	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}
	
	public String[] getSubstitutionValues() {
		return substitutionValues;
	}
	
	public void setSubstitutionValues(String[] substitutionValues) {
		this.substitutionValues = substitutionValues;
	}
	
}
