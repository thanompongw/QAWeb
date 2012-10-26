/******************************************************
 * Program History
 * 
 * Project Name	            :  CRMReport
 * Client Name				:  
 * Package Name             :  co.th.genth.crm.exception
 * Program ID 	            :  ErrorList.java
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thanompong.W
 */
public class ErrorList implements Serializable {
	
	/** serialVersionUID property **/
	private static final long serialVersionUID = 8187504399984567786L;
	private List<BusinessError> errorList;
	
	/**
	 * Default Constructor of ErrorList.java
	 */
	public ErrorList() {
		this.errorList = new ArrayList<BusinessError>();
	}
	
	public ErrorList(ArrayList<BusinessError> arrErrList) {
		errorList = new ArrayList<BusinessError>(arrErrList);
	}
	
	public ArrayList<BusinessError> getErrorList() {
		return (ArrayList<BusinessError>) errorList;
	}
	
	public void addError(String errorKey, int type) {
		BusinessError bx = null;
		if (errorKey != null) {
			bx = new BusinessError(errorKey, type);
			errorList.add(bx);
		}
	}
	
	public void addError(String errorKey, String[] args) {
		BusinessError bx = null;
		if (errorKey != null) {
			bx = new BusinessError(errorKey, args);
			errorList.add(bx);
		}
	}
	
	public void addError(String errorKey) {
		BusinessError bx = null;
		if (errorKey != null) {
			bx = new BusinessError(errorKey, null);
			errorList.add(bx);
		}
	}
	
	public void addError(String errorKey, int type, String[] args) {
		BusinessError bx = null;
		if (errorKey != null) {
			bx = new BusinessError(errorKey, type, args);
			errorList.add(bx);
		}
	}
	
	public boolean isTransactionSuccess() {
		boolean isTxSuccess = true;
		if (errorList.size() == 0) {
			isTxSuccess = true;
		} else {
			BusinessError bx = null;
			for (int i = 0; i < errorList.size(); i++) {
				bx = (BusinessError) errorList.get(i);
				if (bx.getErrorType() == 3) {
					isTxSuccess = false;
					break;
				}
			}
			
		}
		return isTxSuccess;
	}
	
	public int getNumberOfErrors() {
		return errorList.size();
		
	}
	
	public CommonException createAppException() {
		CommonException cx = new CommonException(this);
		return cx;
	}
	
	public void throwExceptionIfErrors() throws CommonException {
		if (getNumberOfErrors() > 0) {
			CommonException cx = createAppException();
			throw cx;
		}
	}
	
}
