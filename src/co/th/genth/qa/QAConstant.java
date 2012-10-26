/******************************************************
 * Program History
 * 
 * Project Name	            :  APS
 * Client Name				:  
 * Package Name             :  co.th.genth.aps
 * Program ID 	            :  APSConstant.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  20 มี.ค. 2555
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa;

/**
 * @author Thanompong.W
 *
 */
public interface QAConstant {
    
	public static final String UNDEFINED_ERR = "undefined_err";
	public static final String ERR_CONCURRENT_LOCK = "concurrency_by_locked";
	public static final String DATA_USED = "data_used";
	public static final String CON_FAILED = "con_failed";
	
	public static final String REPORT_TEMPLATE_PATH = "report_template_path";
	public static final String REPORT_EXTENTION = "report_extention";
	public static final String TEXT_EXTENTION = ".txt";
	public static final String XLS_REPORT_EXTENTION = ".xls";
	public static final String XLSX_REPORT_EXTENTION = ".xlsx";
	public static final String BATCH_EXTENTION = ".bat";
	
	public static final String ACTIVE_FLAG = "Y";
	public static final String INACTIVE_FLAG = "N";
	
	public static final Integer PASSWORD_EXPIRED = 1;
	public static final Integer PASSWORD_NORMAL = 2;
	public static final Integer PASSWORD_FORCE_CHANGE = 3;
	
	public static final String INTERNAL_ERROR_CODE = "500";
	public static final String WARNING_CODE = "400";
    public static final String CONFIRMATION_CODE = "300";
	public static final String SUCCESS_CODE = "200";
    
}
