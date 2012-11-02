/******************************************************
 * Program History
 * 
 * Project Name	            :  CCS
 * Client Name				:  
 * Package Name             :  co.th.genth.ccs.domain
 * Program ID 	            :  Staff.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  Aug 3, 2012
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Thanompong.W
 *
 */
public class Staff implements Serializable {

	/** serialVersionUID property **/
    private static final long serialVersionUID = 6030882917371557800L;
    
    private String staffCode;
    private String staffName;
    private String sectionCode;
    private String statusCode;
    private Integer taskRatio;
    private Timestamp createdDate;
    private String createdBy;
    private Timestamp updatedDate;
    private String updatedBy;
    

	/**
	 * Default Constructor of Staff.java
	 */
	public Staff() {
	}

	/**
     * @return the staffCode
     */
    public String getStaffCode() {
    	return staffCode;
    }

	/**
     * @param staffCode the staffCode to set
     */
    public void setStaffCode(String staffCode) {
    	this.staffCode = staffCode;
    }

	/**
     * @return the staffName
     */
    public String getStaffName() {
    	return staffName;
    }

	/**
     * @param staffName the staffName to set
     */
    public void setStaffName(String staffName) {
    	this.staffName = staffName;
    }

	/**
     * @return the sectionCode
     */
    public String getSectionCode() {
    	return sectionCode;
    }

	/**
     * @param sectionCode the sectionCode to set
     */
    public void setSectionCode(String sectionCode) {
    	this.sectionCode = sectionCode;
    }

	/**
     * @return the statusCode
     */
    public String getStatusCode() {
    	return statusCode;
    }

	/**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
    	this.statusCode = statusCode;
    }

	/**
     * @return the taskRatio
     */
    public Integer getTaskRatio() {
    	return taskRatio;
    }

	/**
     * @param taskRatio the taskRatio to set
     */
    public void setTaskRatio(Integer taskRatio) {
    	this.taskRatio = taskRatio;
    }

	/**
     * @return the createdDate
     */
    public Timestamp getCreatedDate() {
    	return createdDate;
    }

	/**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Timestamp createdDate) {
    	this.createdDate = createdDate;
    }

	/**
     * @return the createdBy
     */
    public String getCreatedBy() {
    	return createdBy;
    }

	/**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
    	this.createdBy = createdBy;
    }

	/**
     * @return the updatedDate
     */
    public Timestamp getUpdatedDate() {
    	return updatedDate;
    }

	/**
     * @param updatedDate the updatedDate to set
     */
    public void setUpdatedDate(Timestamp updatedDate) {
    	this.updatedDate = updatedDate;
    }

	/**
     * @return the updatedBy
     */
    public String getUpdatedBy() {
    	return updatedBy;
    }

	/**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(String updatedBy) {
    	this.updatedBy = updatedBy;
    }
    
}
