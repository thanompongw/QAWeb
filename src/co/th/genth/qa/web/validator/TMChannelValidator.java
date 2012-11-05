/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.web.validator
 * Program ID 	            :  TMChannelValidator.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  26/10/2012
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa.web.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.*;

import co.th.genth.qa.common.util.ErrorUtil;
import co.th.genth.qa.dao.TMChannelDao;
import co.th.genth.qa.domain.TMChannel;
import co.th.genth.qa.exception.CommonException;
import co.th.genth.qa.exception.Message;

/**
 * @author Thanompong.W
 *
 */
@Component("tmChannelValidator")
public class TMChannelValidator implements Validator {
	
	@Resource(name = "tmChannelDao")
	private TMChannelDao dao;
	
	/**
	 * Default Constructor of TMChannelValidator.java
	 */
	public TMChannelValidator() {
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return TMChannel.class.isAssignableFrom(clazz);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object model, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
												  "tmChannelCode", 
												  "MSTD0031AERR",
												  new Object[] { "TM Channel Code" });
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
												  "Name", 
												  "MSTD0031AERR",
												  new Object[] { "TM Channel Description" });
		
		TMChannel tmChannel = (TMChannel) model;
		
		if (errors.getObjectName() != null 
				&& errors.getObjectName().equals("newTmChannel")) {
			
			try {
	            TMChannel existingTMChannel = dao.findById(tmChannel.getTmChannelId());
	            
	            if (existingTMChannel != null) {
					errors.reject("MSTD0011AERR", 
								  new Object[] { "TM Channel", tmChannel.getTmChannelCode() }, 
								  "");
				}
            } catch (CommonException cx) {
            	errors.reject("MSTD0000AERR", ((Message) ErrorUtil.getErrors(cx).get(0)).getMessage());
            }
		}
		
	}
	
}
