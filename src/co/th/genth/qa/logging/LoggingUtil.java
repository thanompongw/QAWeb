/******************************************************
 * Program History
 * 
 * Project Name	            :  QA Voice File
 * Client Name				:  
 * Package Name             :  co.th.genth.qa.logging
 * Program ID 	            :  LoggingUtil.java
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

import java.lang.reflect.Method;

/**
 * @author Thanompong.W
 */
public class LoggingUtil {
	
	/**
	 * Default Constructor of LoggingUtil.java
	 */
	public LoggingUtil() {
	}
	
	public static String createToString(Object object) {
		StringBuilder result = new StringBuilder();
		if (object != null) {
			
			result.append(object.getClass().getName());
			result.append("{ ");
			
			Method[] methods = object.getClass().getMethods();
			
			for (Method method : methods) {
				
				if (method.getName().startsWith("get") && method.getParameterTypes().length == 0) {
					
					String propertyNameWithoutFirstCharacter = method.getName().substring(4);
					String propertyNamefirstCharacter = method.getName().substring(3, 4)
					        .toLowerCase();
					String propertyName = propertyNamefirstCharacter
					                      + propertyNameWithoutFirstCharacter;
					
					Object propertyValue;
					
					try {
						propertyValue = method.invoke(object);
					} catch (Exception e) {
						propertyValue = e.getMessage();
					}
					result.append(" (");
					result.append(propertyName);
					result.append(": ");
					result.append(propertyValue);
					result.append(") ");
				}
			}
			result.append(" }");
		} else {
			return "null";
		}
		return result.toString();
	}
	
}
