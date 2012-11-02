/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.web.model
 * Program ID 	            :  StaffModel.java
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
package co.th.genth.qa.web.model;

import java.util.List;

import co.th.genth.qa.domain.Staff;

/**
 * @author Thanompong.W
 *
 */
public class StaffModel extends Staff {
	
	/** serialVersionUID property **/
    private static final long serialVersionUID = 6553030331176465863L;
    
    private List<ReferenceModel> sections;
    private List<ReferenceModel> statuses;

	/**
     * Default Constructor of StaffModel.java
     */
    public StaffModel() {
	    super();
    }

	/**
     * @return the sections
     */
    public List<ReferenceModel> getSections() {
    	return sections;
    }

	/**
     * @param sections the sections to set
     */
    public void setSections(List<ReferenceModel> sections) {
    	this.sections = sections;
    }

	/**
     * @return the statuses
     */
    public List<ReferenceModel> getStatuses() {
    	return statuses;
    }

	/**
     * @param statuses the statuses to set
     */
    public void setStatuses(List<ReferenceModel> statuses) {
    	this.statuses = statuses;
    }
}
