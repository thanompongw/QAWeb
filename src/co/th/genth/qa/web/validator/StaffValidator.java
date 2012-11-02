/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.web.validator
 * Program ID 	            :  StaffValidator.java
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
import co.th.genth.qa.dao.StaffDao;
import co.th.genth.qa.domain.Staff;
import co.th.genth.qa.exception.CommonException;
import co.th.genth.qa.exception.ErrorMessage;

/**
 * @author Thanompong.W
 *
 */
@Component("staffValidator")
public class StaffValidator implements Validator {
	
	@Resource(name = "staffDao")
	private StaffDao dao;
	
	/**
	 * Default Constructor of StaffValidator.java
	 */
	public StaffValidator() {
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Staff.class.isAssignableFrom(clazz);
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object model, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
												  "staffCode", 
												  "MSTD0031AERR",
												  new Object[] { "Staff Code" });
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
												  "staffName", 
												  "MSTD0031AERR",
												  new Object[] { "Staff Name" });
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
												  "sectionCode", 
												  "MSTD0031AERR",
												  new Object[] { "Section" });
		
		ValidationUtils.rejectIfEmpty(errors, 
		                              "taskRatio", 
									  "MSTD0031AERR",
									  new Object[] { "Task Ratio" });
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
		                                          "activeFlag", 
		                                          "MSTD0031AERR",
		                                          new Object[] { "Status" });
		
		Staff staff = (Staff) model;
		
		if (!errors.hasErrors()) {
			if (errors.getObjectName() != null 
					&& errors.getObjectName().equals("newStaff")) {
			
				try {
		            Staff existingStaff = dao.findById(staff.getStaffCode());
		            
		            if (existingStaff != null) {
						errors.reject("MSTD0011AERR", 
									  new Object[] { "Staff Code", staff.getStaffCode() }, 
									  "");
					}
	            } catch (CommonException cx) {
	            	errors.reject("MSTD0000AERR", ((ErrorMessage) ErrorUtil.getErrors(cx).get(0)).getMessage());
	            }
			}			
		}
		
	}
	
}
