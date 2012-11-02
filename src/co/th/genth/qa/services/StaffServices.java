/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.services
 * Program ID 	            :  StaffServices.java
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
package co.th.genth.qa.services;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import co.th.genth.qa.common.util.ErrorUtil;
import co.th.genth.qa.dao.StaffDao;
import co.th.genth.qa.domain.Staff;
import co.th.genth.qa.exception.CommonException;

@Service(value = "staffService")
public class StaffServices {
	
	private static Logger logger = Logger.getLogger(StaffServices.class);
	
	@Resource(name="staffDao")
	private StaffDao dao; 
	
	private PlatformTransactionManager txManager;
	private TransactionStatus txStatus;
	
	/**
	 * Default Constructor of StaffServices.java
	 */
	public StaffServices() {
	}

	/**
     * @param txManager the txManager to set
     */
    public void setTxManager(PlatformTransactionManager txManager) {
    	this.txManager = txManager;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void createStaff(Staff staff) throws CommonException {

    	TransactionDefinition txDef = new DefaultTransactionDefinition();
		txStatus = txManager.getTransaction(txDef);
		
		try {
			
			dao.add(staff);
	        
	        txManager.commit(txStatus);
	    } catch (CommonException cx) {
	    	txManager.rollback(txStatus);
	    	throw cx;
	    } catch (Exception ex) {
	    	txManager.rollback(txStatus);
			logger.error(ex.getMessage(), ex);
			throw ErrorUtil.generateError("MSTD0004AERR", ex.getMessage());
	    }
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void updateStaff(Staff staff) throws CommonException {

    	TransactionDefinition txDef = new DefaultTransactionDefinition();
		txStatus = txManager.getTransaction(txDef);
		
		try {
			
			Staff existingStaff = dao.findById(staff.getStaffCode());
			
			existingStaff.setStaffName(staff.getStaffName());
			existingStaff.setSectionCode(staff.getSectionCode());
			existingStaff.setTaskRatio(staff.getTaskRatio());
			existingStaff.setUpdatedBy(staff.getUpdatedBy());
			existingStaff.setUpdatedDate(staff.getUpdatedDate());
			
			dao.edit(existingStaff);
	        
	        txManager.commit(txStatus);
	    } catch (CommonException cx) {
	    	txManager.rollback(txStatus);
	    	throw cx;
	    } catch (Exception ex) {
	    	txManager.rollback(txStatus);
			logger.error(ex.getMessage(), ex);
			throw ErrorUtil.generateError("MSTD0004AERR", ex.getMessage());
	    }
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void deleteStaff(Staff staff) throws CommonException {

    	TransactionDefinition txDef = new DefaultTransactionDefinition();
		txStatus = txManager.getTransaction(txDef);
		
		try {
			
			Staff existingStaff = dao.findById(staff.getStaffCode());
			
			existingStaff.setStatusCode(staff.getStatusCode());
			existingStaff.setUpdatedBy(staff.getUpdatedBy());
			existingStaff.setUpdatedDate(staff.getUpdatedDate());
			
			dao.remove(existingStaff);
	        
	        txManager.commit(txStatus);
	    } catch (CommonException cx) {
	    	txManager.rollback(txStatus);
	    	throw cx;
	    } catch (Exception ex) {
	    	txManager.rollback(txStatus);
			logger.error(ex.getMessage(), ex);
			throw ErrorUtil.generateError("MSTD0004AERR", ex.getMessage());
	    }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Staff> search(Staff searchObj) throws CommonException {
    	List<Staff> staffs = null;
    	try {
			
			staffs = dao.search(searchObj);
	        
	    } catch (CommonException cx) {
	    	throw cx;
	    } catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ErrorUtil.generateError("MSTD0004AERR", ex.getMessage());
	    }
    	
    	return staffs;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Staff> list() throws CommonException {
    	List<Staff> staffs = null;
    	try {
			
			staffs = dao.list();
	        
	    } catch (CommonException cx) {
	    	throw cx;
	    } catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ErrorUtil.generateError("MSTD0004AERR", ex.getMessage());
	    }
    	
    	return staffs;
    }
	
}
