/******************************************************
 * Program History
 * 
 * Project Name	            :  CCS
 * Client Name				:  
 * Package Name             :  co.th.genth.ccs.domain
 * Program ID 	            :  User.java
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
public class User implements Serializable {
	
	/** serialVersionUID property **/
    private static final long serialVersionUID = -6092456125078981761L;
    
    private String userId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Timestamp lastLoginTime;
    private Integer invalidLogin;
    private String activeFlag;
    private Integer passwordStatus;
	private UserRole userRole;
    private Timestamp createdDate;
    private String createdBy;
    private Timestamp updatedDate;
    private String updatedBy;

	/**
	 * Default Constructor of User.java
	 */
	public User() {
	}

	/**
     * @return the userId
     */
    public String getUserId() {
    	return userId;
    }

	/**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
    	this.userId = userId;
    }

	/**
     * @return the userName
     */
    public String getUserName() {
    	return userName;
    }

	/**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
    	this.userName = userName;
    }

	/**
     * @return the password
     */
    public String getPassword() {
    	return password;
    }

	/**
     * @param password the password to set
     */
    public void setPassword(String password) {
    	this.password = password;
    }

	/**
     * @return the firstName
     */
    public String getFirstName() {
    	return firstName;
    }

	/**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }

	/**
     * @return the lastName
     */
    public String getLastName() {
    	return lastName;
    }

	/**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }

	/**
     * @return the email
     */
    public String getEmail() {
    	return email;
    }

	/**
     * @param email the email to set
     */
    public void setEmail(String email) {
    	this.email = email;
    }

	/**
     * @return the lastLoginTime
     */
    public Timestamp getLastLoginTime() {
    	return lastLoginTime;
    }

	/**
     * @param lastLoginTime the lastLoginTime to set
     */
    public void setLastLoginTime(Timestamp lastLoginTime) {
    	this.lastLoginTime = lastLoginTime;
    }

	/**
     * @return the invalidLogin
     */
    public Integer getInvalidLogin() {
    	return invalidLogin;
    }

	/**
     * @param invalidLogin the invalidLogin to set
     */
    public void setInvalidLogin(Integer invalidLogin) {
    	this.invalidLogin = invalidLogin;
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
     * @return the passwordStatus
     */
    public Integer getPasswordStatus() {
    	return passwordStatus;
    }

	/**
     * @param passwordStatus the passwordStatus to set
     */
    public void setPasswordStatus(Integer passwordStatus) {
    	this.passwordStatus = passwordStatus;
    }

	/**
     * @return the userRole
     */
    public UserRole getUserRole() {
	    return userRole;
    }

	/**
     * @param userRole the userRole to set
     */
    public void setUserRole(UserRole userRole) {
	    this.userRole = userRole;
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
