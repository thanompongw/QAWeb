/**
 * 
 */
package co.th.genth.qa.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Thanompong.W
 *
 */
@Controller
public class MediatorController {
	
	/**
	 * Retrieves the JSP index page.
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndexPage() {
		
		return "login";
	}
	
	/**
	 * Retrieves the JSP home page.
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String getHomePage() {
		
		return "dashboard";
	}
	
	/**
	 * Retrieves the JSP home page.
	 */
	@RequestMapping(value = "/staff/index", method = RequestMethod.GET)
	public String getStaff() {
		
		return "staff/list";
	}
	
	/**
	 * Retrieves the JSP index page.
	 */
	@RequestMapping(value = "/accessDenied")
	public String getAccessDeniedPage() {
		return "accessDenied";
	}
}
