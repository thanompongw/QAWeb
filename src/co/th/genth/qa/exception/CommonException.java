/******************************************************
 * Program History
 * 
 * Project Name	            :  CRMReport
 * Client Name				:  
 * Package Name             :  co.th.genth.crm.exception
 * Program ID 	            :  CommonException.java
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

/**
 * @author Thanompong.W
 */
public class CommonException extends Exception {
	/** serialVersionUID property **/
    private static final long serialVersionUID = -393747328869623929L;
	protected ErrorList errorList = null;
	protected int errIndex;
	
	public CommonException() {
		super();
	}
	
	public CommonException(String msg) {
		super(msg);
		
	}
	
	public int getErrIndex() {
		return errIndex;
	}
	
	public void setErrIndex(int errIndex) {
		this.errIndex = errIndex;
	}
	
	public CommonException(ErrorList errorList) {
		this.errorList = errorList;
	}
	
	public ErrorList getErrorList() {
		return errorList;
	}
	
	public void setErrorList(ErrorList errorList) {
		this.errorList = errorList;
	}
	
}
