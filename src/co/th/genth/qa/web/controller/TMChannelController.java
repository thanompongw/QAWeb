/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.web.controller
 * Program ID 	            :  TMChannelController.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  30/10/2012
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import co.th.genth.qa.common.util.MessageResolver;
import co.th.genth.qa.domain.Staff;
import co.th.genth.qa.domain.TMChannel;
import co.th.genth.qa.web.model.*;

/**
 * @author Thanompong.W
 *
 */
@Controller
@RequestMapping("tmChannel")
public class TMChannelController {
	
	@Resource(name="messageService")
	private MessageResolver messageResolver;
	
	/**
	 * Default Constructor of StaffController.java
	 */
	public TMChannelController() {
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public ModelAndView initCreatePage() {

		// Initialize our custom agent model wrapper
		return new ModelAndView("tmChannel/create", "tmChannel", new TMChannelModel());
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView initEditPage() {

		// Initialize our custom agent model wrapper
		return new ModelAndView("tmChannel/edit", "tmChannel", new TMChannelModel());
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public ModelAndView list() {

		// Initialize our custom agent model wrapper
		return new ModelAndView("tmChannel/list", "command", new TMChannelModel());
	}
	
	@RequestMapping(value = "getListData", method = RequestMethod.POST)
	public @ResponseBody DataTablesResponse<Staff> list(@RequestBody DataTablesRequest dtReq, 
	                                                    HttpServletResponse response) {

		// Initialize our custom agent model wrapper
		return new DataTablesResponse<Staff>();
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody QAResponse add(@ModelAttribute("newTMChannel") TMChannel model,
				 						  BindingResult result,
				 						  SessionStatus status) {

		QAResponse returnModel = new QAResponse();
		
		return returnModel;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody QAResponse edit(@ModelAttribute("exitingTMChannel") TMChannel model,
				 						   BindingResult result,
				 						   SessionStatus status) {

		QAResponse returnModel = new QAResponse();
		
		
		return returnModel;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody QAResponse delete(@ModelAttribute("exitingTMChannel") TMChannel model) {

		QAResponse returnModel = new QAResponse();
		
		
		return returnModel;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		dataBinder.registerCustomEditor(String.class, 
		                                new StringTrimmerEditor(true));
	}
	
}
