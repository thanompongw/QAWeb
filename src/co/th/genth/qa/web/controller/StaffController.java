/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.web.controller
 * Program ID 	            :  StaffController.java
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
package co.th.genth.qa.web.controller;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import co.th.genth.qa.QAConstant;
import co.th.genth.qa.common.util.ErrorUtil;
import co.th.genth.qa.common.util.MessageResolver;
import co.th.genth.qa.domain.Staff;
import co.th.genth.qa.exception.CommonException;
import co.th.genth.qa.services.StaffServices;
import co.th.genth.qa.web.model.*;
import co.th.genth.qa.web.validator.StaffValidator;

/**
 * @author Thanompong.W
 *
 */
@Controller
@RequestMapping("staff")
public class StaffController {
	
	@Resource(name = "staffService")
	private StaffServices services;
	
	@Autowired
	private StaffValidator validator;
	
	@Resource(name="messageService")
	private MessageResolver messageResolver;
	
	/**
	 * Default Constructor of StaffController.java
	 */
	public StaffController() {
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public ModelAndView initCreatePage() {

		// Initialize our custom agent model wrapper
		return new ModelAndView("staff/create", "staff", new StaffModel());
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView initEditPage() {

		// Initialize our custom agent model wrapper
		return new ModelAndView("staff/edit", "command", new StaffModel());
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ModelAndView list() {

		// Initialize our custom agent model wrapper
		return new ModelAndView("staff/list", "command", new StaffModel());
	}
	
	@RequestMapping(value = "getListData", method = RequestMethod.POST)
	public @ResponseBody DataTablesResponse<Staff> list(@RequestBody DataTablesRequest dtReq, 
	                                                    HttpServletResponse response) {

		// Initialize our custom agent model wrapper
		return new DataTablesResponse<Staff>();
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody GenericModel add(@ModelAttribute("newStaff") Staff model,
				 						  BindingResult result,
				 						  SessionStatus status) {

		GenericModel returnModel = new GenericModel();
		try {
			// Do custom validation here or in your service
			validator.validate(model, result);
			if (result.hasErrors()) {
				// A failure. Return a custom model as well				
				returnModel.setMessages(messageResolver.handleErrorMsg(result));
				returnModel.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
			} else {
	
				// Construct our staff object
				// Assign the values from the parameters
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				
				String createdBy = auth.getName();
				Timestamp createdDate = new Timestamp(System.currentTimeMillis());
				
				Staff staff = new Staff();
				
				staff.setStaffCode(model.getStaffCode());
				staff.setStaffName(model.getStaffName());
				staff.setSectionCode(model.getSectionCode());
				staff.setTaskRatio(model.getTaskRatio());
				staff.setActiveFlag(QAConstant.ACTIVE_FLAG);
				staff.setCreatedBy(createdBy);
				staff.setCreatedDate(createdDate);
				staff.setUpdatedBy(createdBy);
				staff.setUpdatedDate(createdDate);
				
				// Call service to add
				services.createStaff(staff);

				// Success. Return a custom model
				returnModel.setStatusCode(QAConstant.SUCCESS_CODE);
				status.setComplete();
			}
		} catch (CommonException cx) {
			returnModel.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
			returnModel.setMessages(ErrorUtil.getErrors(cx));
        }
		
		return returnModel;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody GenericModel edit(@ModelAttribute("exitingStaff") Staff model,
				 						   BindingResult result,
				 						   SessionStatus status) {

		GenericModel returnModel = new GenericModel();
		try {
			// Do custom validation here or in your service
			validator.validate(model, result);
			if (result.hasErrors()) {
				// A failure. Return a custom model as well				
				returnModel.setMessages(messageResolver.handleErrorMsg(result));
				returnModel.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
			} else {
	
				// Construct our user object
				// Assign the values from the parameters
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				
				// Call service to get existing staff data.
				Staff staff = new Staff();
				
				staff.setStaffCode(model.getStaffCode());
				staff.setStaffName(model.getStaffName());
				staff.setSectionCode(model.getSectionCode());
				staff.setTaskRatio(model.getTaskRatio());
				staff.setUpdatedBy(auth.getName());
				staff.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
				
				// Call service to update
				services.updateStaff(staff);

				// Success. Return a custom model
				returnModel.setStatusCode(QAConstant.SUCCESS_CODE);
				status.setComplete();
			}
		} catch (CommonException cx) {
			returnModel.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
			returnModel.setMessages(ErrorUtil.getErrors(cx));
        }
		
		return returnModel;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody GenericModel delete(@ModelAttribute("exitingStaff") Staff model) {

		GenericModel returnModel = new GenericModel();
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			// Call service to get existing staff data.
			Staff staff = new Staff();

			staff.setActiveFlag(QAConstant.INACTIVE_FLAG);
			staff.setStaffCode(model.getStaffCode());
			staff.setUpdatedBy(auth.getName());
			staff.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
			
			// Call service to delete.
			services.deleteStaff(staff);

			// Success. Return a custom model
			returnModel.setStatusCode(QAConstant.SUCCESS_CODE);
		} catch (CommonException cx) {
			returnModel.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
			returnModel.setMessages(ErrorUtil.getErrors(cx));
        }
		
		return returnModel;
	}
	
	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public @ResponseBody GenericModel validate(@ModelAttribute("newStaff") StaffModel model,
	                                           BindingResult result,
	                                           SessionStatus status) {

		GenericModel returnModel = new GenericModel();
		
		// Do custom validation here or in your service
		validator.validate(model, result);
		if (result.hasErrors()) {
			// A failure. Return a custom model as well
			returnModel.setMessages(messageResolver.handleErrorMsg(result));
			returnModel.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
		} else {
			// Success. Return a custom model
			returnModel.setStatusCode(QAConstant.SUCCESS_CODE);
			status.setComplete();
		}
		
		return returnModel;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		dataBinder.registerCustomEditor(String.class, 
		                                new StringTrimmerEditor(true));
	}
	
}
