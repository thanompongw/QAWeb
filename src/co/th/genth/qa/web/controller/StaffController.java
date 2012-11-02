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
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
	public ModelAndView showCreatePage(ModelMap model) {
		
		// Initialize our custom agent model wrapper
		return new ModelAndView("tmChannel/create", "tmChannel", new StaffModel());
	}
	
	@ModelAttribute("sections")
	public List<ReferenceModel> getAllSections() {
		
		List<ReferenceModel> sections = new ArrayList<ReferenceModel>();
		
		sections.add(new ReferenceModel("CCC", "Customer Care Center"));
		sections.add(new ReferenceModel("BR", "Business Retention"));
		sections.add(new ReferenceModel("POS", "Policy Operation Services"));
		
		return sections;
	}
	
	@ModelAttribute("statuses")
	public List<ReferenceModel> getAllStatuses() {
		
		List<ReferenceModel> statuses = new ArrayList<ReferenceModel>();
		
		statuses.add(new ReferenceModel("Y", "Active"));
		statuses.add(new ReferenceModel("N", "Inactive"));
		
		return statuses;
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView showEditPage() {

		// Initialize our custom agent model wrapper
		return new ModelAndView("staff/edit", "staff", new StaffModel());
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public @ResponseBody DataTablesResponse<Staff> list(@RequestBody DataTablesRequest dtReq, 
	                                                    HttpServletResponse response) {

		// Initialize our custom agent model wrapper
		return new DataTablesResponse<Staff>();
	}
	
	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public @ResponseBody QAResponse validate(Model model, 
	                                         @ModelAttribute(value="staff") StaffModel staff, 
	                                         BindingResult result) {
		QAResponse response = new QAResponse();

		// Do custom validation here or in your service
		validator.validate(staff, result);
		
		if (!result.hasErrors()) {
			response.setStatusCode(QAConstant.SUCCESS_CODE);
		} else {
			// A failure. Return a custom model as well				
			response.setErrorMessages(messageResolver.handleFieldErrorMessage(result));
			response.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
		}
		return response;
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String add(@ModelAttribute("newStaff") Staff model,
				 	  BindingResult result,
				 	  QAResponse response) {
		try {
			// Do custom validation here or in your service
			validator.validate(model, result);
			if (result.hasErrors()) {
				// A failure. Return a custom model as well				
				response.setErrorMessages(messageResolver.handleFieldErrorMessage(result));
				response.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
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
				staff.setStatusCode(QAConstant.ACTIVE_FLAG);
				staff.setCreatedBy(createdBy);
				staff.setCreatedDate(createdDate);
				staff.setUpdatedBy(createdBy);
				staff.setUpdatedDate(createdDate);
				
				// Call service to add
				services.createStaff(staff);

				// Success. Return a custom model
				response.setStatusCode(QAConstant.SUCCESS_CODE);
			}
		} catch (CommonException cx) {
			response.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
			response.setErrorMessages(ErrorUtil.getErrors(cx));
        }
		
		return "create";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody QAResponse edit(@ModelAttribute("exitingStaff") Staff model,
				 						   BindingResult result,
				 						   SessionStatus status) {

		QAResponse returnModel = new QAResponse();
		try {
			// Do custom validation here or in your service
			validator.validate(model, result);
			if (result.hasErrors()) {
				// A failure. Return a custom model as well				
				returnModel.setErrorMessages(messageResolver.handleFieldErrorMessage(result));
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
			returnModel.setErrorMessages(ErrorUtil.getErrors(cx));
        }
		
		return returnModel;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody QAResponse delete(@ModelAttribute("exitingStaff") Staff model) {

		QAResponse returnModel = new QAResponse();
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			// Call service to get existing staff data.
			Staff staff = new Staff();

			staff.setStatusCode(QAConstant.INACTIVE_FLAG);
			staff.setStaffCode(model.getStaffCode());
			staff.setUpdatedBy(auth.getName());
			staff.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
			
			// Call service to delete.
			services.deleteStaff(staff);

			// Success. Return a custom model
			returnModel.setStatusCode(QAConstant.SUCCESS_CODE);
		} catch (CommonException cx) {
			returnModel.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
			returnModel.setErrorMessages(ErrorUtil.getErrors(cx));
        }
		
		return returnModel;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		dataBinder.registerCustomEditor(String.class, 
		                                new StringTrimmerEditor(true));
	}
	
}
