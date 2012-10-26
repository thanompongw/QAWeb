/******************************************************
 * Program History
 * 
 * Project Name	            :  CCS
 * Client Name				:  
 * Package Name             :  co.th.genth.ccs.domain
 * Program ID 	            :  Role.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  Aug 7, 2012
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
 */
public class Role implements Serializable {
	
	/** serialVersionUID property **/
	private static final long serialVersionUID = 908199219309708195L;
	
	private Integer roleId;
	private String roleName;
	private String roleDesc;
	private Timestamp createdDate;
	private String createdBy;
	private Timestamp updatedDate;
	private String updatedBy;
	
	/**
	 * Default Constructor of Role.java
	 */
	public Role() {
	}
	
	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}
	
	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	
	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * @return the roleDesc
	 */
	public String getRoleDesc() {
		return roleDesc;
	}
	
	/**
	 * @param roleDesc
	 *            the roleDesc to set
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	
	/**
	 * @param createdDate
	 *            the createdDate to set
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
	 * @param createdBy
	 *            the createdBy to set
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
	 * @param updatedDate
	 *            the updatedDate to set
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
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}
