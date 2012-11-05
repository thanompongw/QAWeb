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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
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
	
	@Resource(name = "staffServices")
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
	public ModelAndView showCreatePage() {
		
		// Initialize our custom agent model wrapper
		return new ModelAndView("staff/create", "staff", new StaffModel());
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
	public ModelAndView showEditPage(@RequestParam("staffCode") String staffCode) {
		
		Staff staff = null;
		
		try {
			staff = services.findById(staffCode);
        } catch (CommonException e) {
        	/*dtRes.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
        	dtRes.setMessages(ErrorUtil.getErrors(cx));*/
        }

		// Initialize our custom agent model wrapper
		return new ModelAndView("staff/edit", "staff", staff);
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public @ResponseBody DataTablesResponse<Staff> list(@RequestBody DataTablesRequest<Staff> dtReq, 
	                                                    HttpServletResponse response) {
		
		DataTablesResponse<Staff> dtRes = new DataTablesResponse<Staff>();
		
		// Call service get data list
		try {
			
			List<Staff> staffs = services.list();
			
			dtRes.setTotalDisplayRecords(10);
			dtRes.setTotalRecords(staffs.size());
			dtRes.setEcho(dtReq.echo);
			dtRes.setData(staffs);
        } catch (CommonException cx) {
        	/*dtRes.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
        	dtRes.setMessages(ErrorUtil.getErrors(cx));*/
        }
		
		// Initialize our custom agent model wrapper
		return dtRes;
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public @ResponseBody QAResponse add(@ModelAttribute("newStaff") StaffModel staff,
	                                    BindingResult result) {
		QAResponse response = new QAResponse();
		try {
			// Do custom validation here or in your service
			validator.validate(staff, result);
			if (result.hasErrors()) {
				// A failure. Return a custom model as well				
				response.setMessages(messageResolver.handleFieldErrorMessage(result));
				response.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
			} else {
	
				// Construct our staff object
				// Assign the values from the parameters
//				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				
//				String createdBy = auth.getName();
				String createdBy = "006369";
				Timestamp createdDate = new Timestamp(System.currentTimeMillis());
				
				staff.setCreatedBy(createdBy);
				staff.setCreatedDate(createdDate);
				staff.setUpdatedBy(createdBy);
				staff.setUpdatedDate(createdDate);
				
				// Call service to add
				services.createStaff(staff);

				// Success. Return a custom model
				response.setStatusCode(QAConstant.SUCCESS_CODE);
				response.setMessages(messageResolver.handleMessage("MQA0001INFO", null));
			}
		} catch (CommonException cx) {
			response.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
			response.setMessages(ErrorUtil.getErrors(cx));
        }
		
		return response;
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public @ResponseBody QAResponse edit(@ModelAttribute("exitingStaff") StaffModel staff,
				 						 BindingResult result) {

		QAResponse response = new QAResponse();
		try {
			// Do custom validation here or in your service
			validator.validate(staff, result);
			if (result.hasErrors()) {
				// A failure. Return a custom model as well				
				response.setMessages(messageResolver.handleFieldErrorMessage(result));
				response.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
			} else {
	
				// Construct our user object
				// Assign the values from the parameters
//				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				
				// Call service to get existing staff data.
				Staff existingStaff = new Staff();
				
				existingStaff.setStaffName(staff.getStaffName());
				existingStaff.setSectionCode(staff.getSectionCode());
				existingStaff.setTaskRatio(staff.getTaskRatio());
				existingStaff.setStatusCode(staff.getStatusCode());
				existingStaff.setUpdatedBy("006369");
				existingStaff.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
				
				// Call service to update
				services.updateStaff(existingStaff);

				// Success. Return a custom model
				response.setStatusCode(QAConstant.SUCCESS_CODE);
				response.setMessages(messageResolver.handleMessage("MQA0001INFO", null));
			}
		} catch (CommonException cx) {
			response.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
			response.setMessages(ErrorUtil.getErrors(cx));
        }
		
		return response;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	/*public @ResponseBody QAResponse delete(@RequestBody StaffModel staff,
	                                        HttpServletResponse response) {*/
	public @ResponseBody QAResponse delete(@ModelAttribute("exitingStaff") StaffModel staff,
	                                       BindingResult result) {

		QAResponse response = new QAResponse();
		try {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			Staff existingStaff = new Staff();
			
			existingStaff.setStatusCode(QAConstant.INACTIVE_FLAG);
			existingStaff.setStaffCode(staff.getStaffCode());
			existingStaff.setUpdatedBy("006369");
			existingStaff.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
			
			// Call service to delete.
			services.deleteStaff(existingStaff);

			// Success. Return a custom model
			response.setStatusCode(QAConstant.SUCCESS_CODE);
			response.setMessages(messageResolver.handleMessage("MQA0001INFO", null));
			
		} catch (CommonException cx) {
			response.setStatusCode(QAConstant.INTERNAL_ERROR_CODE);
			response.setMessages(ErrorUtil.getErrors(cx));
        }
		
		return response;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		dataBinder.registerCustomEditor(String.class, 
		                                new StringTrimmerEditor(true));
	}
	
}
