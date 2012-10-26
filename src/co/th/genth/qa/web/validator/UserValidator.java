/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.web.validator
 * Program ID 	            :  UserValidator.java
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
import co.th.genth.qa.dao.UserDao;
import co.th.genth.qa.domain.User;
import co.th.genth.qa.exception.CommonException;
import co.th.genth.qa.web.model.UserModel;

/**
 * @author Thanompong.W
 *
 */
@Component("userValidator")
public class UserValidator implements Validator {
	
	@Resource(name = "userDao")
	private UserDao userDao;
	
	/**
	 * Default Constructor of UserValidator.java
	 */
	public UserValidator() {
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class<?> clazz) {
		return UserModel.class.isAssignableFrom(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object model, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
												  "userName", 
												  "MSTD0031AERR",
												  new Object[] { "User Name" });
		

		UserModel user = (UserModel) model;

		if (!errors.hasErrors()) {
			
			if (errors.getObjectName() != null 
					&& errors.getObjectName().equals("newUser")) {
                try {
                	
                	User existingUser = userDao.findByUserName(user.getUserName());
					if (existingUser != null) {
						errors.reject("MSTD0011AERR", 
									  new Object[] { "User Name", user.getUserName() }, 
									  "");
					}	
                } catch (CommonException cx) {
                	errors.reject("MSTD0000AERR", ErrorUtil.getErrors(cx).get(0));
                }			
			}
		}
		
		if (!errors.hasErrors()) {
		
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
													  "password", 
													  "MSTD0031AERR",
													  new Object[] { "Password" });
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
													  "confirmPassword", 
													  "MSTD0031AERR",
													  new Object[] { "Confirm Password" });
			
			if (!errors.hasErrors()) {
				
				/*if (!user.getPassword().equals(user.getConfirmPassword())) {
					errors.reject("MCCS0002AERR");
				}*/
				
				if (!errors.hasErrors()) {
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
															  "firstName", 
															  "MSTD0031AERR",
															  new Object[] { "First Name" });
					
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
															  "lastName", 
															  "MSTD0031AERR",
															  new Object[] { "Last Name" });
					
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
															  "role", 
															  "MSTD0031AERR",
															  new Object[] { "Role" });
					
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
															  "status.name", 
															  "MSTD0031AERR",
															  new Object[] { "Status" });
				}
			}
		}
	}

}
