/******************************************************
 * Program History
 * 
 * Project Name	            :  CCS
 * Client Name				:  
 * Package Name             :  co.th.genth.ccs.domain
 * Program ID 	            :  TMChannel.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  30 Ê.¤. 2555
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
public class TMChannel implements Serializable {
	
	/** serialVersionUID property **/
    private static final long serialVersionUID = -8361822550264639603L;
    
    private Integer tmChannelId;
    private String tmChannelCode;
    private String tmChannelDesc;
    private String activeFlag;
    private Timestamp createdDate;
    private String createdBy;
    private Timestamp updatedDate;
    private String updatedBy;

	/**
	 * Default Constructor of TMChannel.java
	 */
	public TMChannel() {
	}

	/**
     * @return the tmChannelId
     */
    public Integer getTmChannelId() {
    	return tmChannelId;
    }

	/**
     * @param tmChannelId the tmChannelId to set
     */
    public void setTmChannelId(Integer tmChannelId) {
    	this.tmChannelId = tmChannelId;
    }

	/**
     * @return the tmChannelCode
     */
    public String getTmChannelCode() {
    	return tmChannelCode;
    }

	/**
     * @param tmChannelCode the tmChannelCode to set
     */
    public void setTmChannelCode(String tmChannelCode) {
    	this.tmChannelCode = tmChannelCode;
    }

	/**
     * @return the tmChannelDesc
     */
    public String getTmChannelDesc() {
    	return tmChannelDesc;
    }

	/**
     * @param tmChannelDesc the tmChannelDesc to set
     */
    public void setTmChannelDesc(String tmChannelDesc) {
    	this.tmChannelDesc = tmChannelDesc;
    }

	/**
     * @return the activeFlag
     */
    public String getActiveFlag() {
    	return activeFlag;
    }

	/**
     * @param activeFlag the activeFlag to set
     */
    public void setActiveFlag(String activeFlag) {
    	this.activeFlag = activeFlag;
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
