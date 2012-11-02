/******************************************************
 * Program History
 * 
 * Project Name	            :  CRMReport
 * Client Name				:  
 * Package Name             :  co.th.genth.crm.common.util
 * Program ID 	            :  ErrorUtil.java
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
package co.th.genth.qa.common.util;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import co.th.genth.qa.QAConstant;
import co.th.genth.qa.exception.*;

/**
 * @author Thanompong.W
 */
public class ErrorUtil {
	
	@Resource(name = "configService")
	private static ConfigurationResolver resolver = new ConfigurationResolver();
	
	@Resource(name="messageService")
	private static MessageResolver messageResolver = new MessageResolver();
	
	public static List<ErrorMessage> getErrors(CommonException e) {
        ErrorList errorList = e.getErrorList();
        
        if (errorList != null) {
            List<BusinessError> beList = errorList.getErrorList();
            List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
            ErrorMessage errorMessage = new ErrorMessage();
            
            for (BusinessError be : beList) {
                String[] stSubsValues = be.getSubstitutionValues();
                
                if (stSubsValues != null) {
                    String formattedSubsValues[] = new String[stSubsValues.length];
                    for (int j = 0; j < formattedSubsValues.length; j++) {
                       formattedSubsValues[j] = removeSpChar(stSubsValues[j]);
                    }
                    errorMessage.setMessage(messageResolver.getMessage(be.getErrorkey(), 
                                                                       formattedSubsValues));
                } else {
                	errorMessage.setMessage(messageResolver.getMessage(be.getErrorkey()));
                }
                
                errorMessages.add(errorMessage);
            }
            
            return errorMessages;
        }
        
        return null;
    }
	
	public static void generateError(CommonException eNew,
	                                 String stKey,
	                                 String[] stErrArg,
	                                 int errFlag) {
		ErrorList obErrList = eNew.getErrorList();
		
		if (obErrList == null) {
			obErrList = new ErrorList();
		}
		
		obErrList.addError(stKey, stErrArg);
		eNew.setErrIndex(errFlag);
		eNew.setErrorList(obErrList);
	}
	
	public static void generateError(CommonException eNew,
	                                 String stKey,
	                                 String stErrArg,
	                                 int errFlag) {
		ErrorList obErrList = eNew.getErrorList();
		
		if (obErrList == null) {
			obErrList = new ErrorList();
		}
		
		String[] stErrMsg = { stErrArg };
		obErrList.addError(stKey, stErrMsg);
		eNew.setErrIndex(errFlag);
		eNew.setErrorList(obErrList);
	}
	
	public static void generateError(CommonException eNew, String stKey, String stErrArg) {
		ErrorList obErrList = eNew.getErrorList();
		
		if (obErrList == null) {
			obErrList = new ErrorList();
		}
		if (stErrArg != null) {
			String[] stErrMsg = { removeSpChar(stErrArg) };
			obErrList.addError(stKey, stErrMsg);
		} else {
			obErrList.addError(stKey);
		}
		eNew.setErrorList(obErrList);
	}
	
	public static void generateError(CommonException eNew, ErrorList errList) {
		ErrorList obErrList = eNew.getErrorList();
		
		if (obErrList == null) {
			obErrList = new ErrorList();
		}
		if (errList != null) {
			ArrayList<BusinessError> arrErrList = obErrList.getErrorList();
			arrErrList.addAll(errList.getErrorList());
			obErrList = new ErrorList(arrErrList);
		}
		eNew.setErrorList(obErrList);
	}
	
	public static CommonException generateError(String stKey, String stErrArg) {
		CommonException e = new CommonException(stKey);
		generateError(e, stKey, stErrArg);
		return e;
	}
	
	public static CommonException generateError(String stKey, String stErrArg, int intErrorIndex) {
		CommonException e = new CommonException(stKey);
		generateError(e, stKey, stErrArg, intErrorIndex);
		return e;
	}
	
	public static CommonException generateError(String stKey, String[] stErrArg, int intErrFlag) {
		CommonException e = new CommonException(stKey);
		generateError(e, stKey, stErrArg, intErrFlag);
		return e;
	}
	
	public static CommonException generateError(ErrorList ErrorList) {
		return new CommonException(ErrorList);
	}
	
	public static String removeSpChar(String stSource) {
		return stSource == null ? "" : (((stSource.replace((char) 0xd, (char) 0x0))
		        .replace((char) 0xa, (char) 0x0)).replace('\'', (char) 0x0)).replace('\"',
		                                                                             (char) 0x0);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getMessages(Array arr) throws SQLException {
		List arrRes = new ArrayList();
		if (arr != null) {
			String[] msgs = (String[]) arr.getArray();
			for (int i = 0; i < msgs.length; i++)
				arrRes.add(msgs[i]);
		}
		return arrRes;
	}
	
	/**
	 * Use this function to handle specific Oracle Error only. For other
	 * database this method can not be used
	 * 
	 * @param e
	 * @throws CommonException
	 */
	public static void handleCommonException(SQLException e) throws CommonException {
		
		if (e.getErrorCode() == 54
		           || resolver.getProperty(QAConstant.ERR_CONCURRENT_LOCK).equals(e.getMessage())) {
			throw ErrorUtil.generateError(resolver.getProperty(QAConstant.ERR_CONCURRENT_LOCK),
			                              null);
		} else if (e.getMessage().toLowerCase().indexOf("io exception") > -1) {
			throw generateError(resolver.getProperty(QAConstant.CON_FAILED), null);
		} else {
			throw ErrorUtil.generateError(resolver.getProperty(QAConstant.UNDEFINED_ERR),
			                              e.getMessage());
		}
	}
	
	public static void handleSystemException(Exception e) throws CommonException {
		
		throw ErrorUtil.generateError(resolver.getProperty(QAConstant.UNDEFINED_ERR),
		                              e.getMessage());
	}
}
