/******************************************************
 * Program History
 * 
 * Project Name	            :  Qa Voice File System
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.services
 * Program ID 	            :  TMChannelServices.java
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
import co.th.genth.qa.dao.TMChannelDao;
import co.th.genth.qa.domain.TMChannel;
import co.th.genth.qa.exception.CommonException;

/**
 * @author Thanompong.W
 *
 */
@Service(value = "tmChannelService")
public class TMChannelServices {
	
	private static Logger logger = Logger.getLogger(TMChannelServices.class);
	
	@Resource(name = "tmChannelDao")
	private TMChannelDao tmChannelDao;
	
	private PlatformTransactionManager txManager;
	private TransactionStatus txStatus;
	
	/**
	 * Default Constructor of TMChannelServices.java
	 */
	public TMChannelServices() {
	}

	/**
     * @param txManager the txManager to set
     */
    public void setTxManager(PlatformTransactionManager txManager) {
    	this.txManager = txManager;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void createTMChannel(TMChannel tmChannel) throws CommonException {

    	TransactionDefinition txDef = new DefaultTransactionDefinition();
		txStatus = txManager.getTransaction(txDef);
		
		try {
			
			tmChannelDao.add(tmChannel);
	        
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
    public void updateTMChannel(TMChannel tmChannel) throws CommonException {

    	TransactionDefinition txDef = new DefaultTransactionDefinition();
		txStatus = txManager.getTransaction(txDef);
		
		try {
			
			TMChannel existingTmChannel = tmChannelDao.findById(tmChannel.getTmChannelId());
			
			existingTmChannel.setTmChannelCode(tmChannel.getTmChannelCode());
			existingTmChannel.setTmChannelDesc(tmChannel.getTmChannelDesc());
			existingTmChannel.setUpdatedBy(tmChannel.getUpdatedBy());
			existingTmChannel.setUpdatedDate(tmChannel.getUpdatedDate());
			
			tmChannelDao.edit(existingTmChannel);
	        
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
    public void deleteTMChannel(TMChannel tmChannel) throws CommonException {

    	TransactionDefinition txDef = new DefaultTransactionDefinition();
		txStatus = txManager.getTransaction(txDef);
		
		try {
			
			TMChannel existingTmChannel = tmChannelDao.findById(tmChannel.getTmChannelId());
			
			existingTmChannel.setActiveFlag(tmChannel.getActiveFlag());
			existingTmChannel.setUpdatedBy(tmChannel.getUpdatedBy());
			existingTmChannel.setUpdatedDate(tmChannel.getUpdatedDate());
			
			tmChannelDao.remove(existingTmChannel);
	        
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
    public List<TMChannel> search(TMChannel searchObj) throws CommonException {
    	List<TMChannel> tmChannels = null;
    	try {
			
    		tmChannels = tmChannelDao.search(searchObj);
	        
	    } catch (CommonException cx) {
	    	throw cx;
	    } catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ErrorUtil.generateError("MSTD0004AERR", ex.getMessage());
	    }
    	
    	return tmChannels;
    }
	
}
