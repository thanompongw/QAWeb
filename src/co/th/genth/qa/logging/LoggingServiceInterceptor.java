/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.logging
 * Program ID 	            :  LoggingInterceptor.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  22/10/2012
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa.logging;

import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * @author Thanompong.W
 *
 */
public class LoggingServiceInterceptor implements MethodInterceptor {
	
	private final Logger log = Logger.getLogger(getClass());
	
	/**
	 * Default Constructor of LoggingInterceptor.java
	 */
	public LoggingServiceInterceptor() {
	}
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object returnValue = null;
		if (!invocation.getMethod().getName().equals("isImportComplete")) {
			if (log.isInfoEnabled()) {
	            log.info("####################################################################");
	            log.info("# " + new Date());
	            log.info("# Beginning Service method: "
	                    + invocation.getMethod().getDeclaringClass() + "::"
	                    + invocation.getMethod().getName());

	            log.info("# Method parameters: "
	                    + invocation.getArguments().length);

	            for (Object object : invocation.getArguments()) {
	                log.info("# " + LoggingUtil.createToString(object));
	            }
	            log.info("####################################################################");
	        }

	        long startTime = System.currentTimeMillis();

	        try {
	            returnValue = invocation.proceed();
	            if (log.isDebugEnabled()) {
	                log.debug("Return value of method: " + LoggingUtil.createToString(returnValue));
	            }
	        } finally {
	            if (log.isInfoEnabled()) {
	                log.info("####################################################################");
	                log.info("# Ending Service method: "
	                        + invocation.getMethod().getDeclaringClass()
	                        + "::" + invocation.getMethod().getName());
	                log.info("# Method invocation time: "
	                        + (System.currentTimeMillis() - startTime) + " msecs.");
	                log.info("####################################################################");
	            }
	        }
		} else {
			returnValue = invocation.proceed();
		}
        return returnValue;
	}
	
}
