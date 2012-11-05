/**
 * 
 */
package co.th.genth.qa.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;

import co.th.genth.qa.exception.Message;

/**
 * @author Thanopong.W
 * 
 */
@Service("messageService")
public class MessageResolver {

	private Properties properties;
	private Logger logger = Logger.getLogger(MessageResolver.class);

	/**
	 * Default Constructor.
	 */
	public MessageResolver() {
		InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream("messages.properties");

		properties = new Properties();
		try {
			if (is != null) {
				properties.load(is);
			} else {
				logger.error("No messages.properties file found");
			}

		} catch (IOException e) {
			logger.error("Error in reading resource bundle file", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("Error in closing resource bundle file", e);
				}
			}
		}
	}

	public String getMessageStr(String keyCode) {
		return this.getMessageStr(keyCode, null);
	}

	public String getMessageStr(String keyCode, Object[] msgArgs) {
		String message = null;
		if (properties.get(keyCode) != null) {
			message = properties.get(keyCode).toString();
		}

		if (message == null) {
			throw new RuntimeException(
					"Can not found message for messsage code = " + keyCode);
		}

		if (msgArgs == null || msgArgs.length == 0) {
			return message;
		}
		message = MessageFormat.format(addSingleQuotation(message), msgArgs);

		return message;
	}

	public Message getMessage(String keyCode, Object[] msgArgs) {
		Message message = new Message();
		if (properties.get(keyCode) != null) {
			message.setMessage(properties.get(keyCode).toString());
		}

		if (msgArgs == null || msgArgs.length == 0) {
			return message;
		}
		
		message.setMessage(MessageFormat.format(addSingleQuotation(properties.get(keyCode).toString()), 
		                                        msgArgs));

		return message;
	}

	public Message getErrorMessage(String keyCode, Object[] msgArgs) {
		Message errorMessage = new Message();
		if (properties.get(keyCode) != null) {
			errorMessage.setMessage(properties.get(keyCode).toString());
		}

		if (msgArgs == null || msgArgs.length == 0) {
			return errorMessage;
		}
		
		errorMessage.setMessage(MessageFormat.format(addSingleQuotation(properties.get(keyCode).toString()), 
		                                             msgArgs));

		return errorMessage;
	}
	
	public List<Message> handleFieldErrorMessage(BindingResult result) {
		List<Message> errors = new ArrayList<Message>();
		
		for (Object object : result.getAllErrors()) {
			Message message = null;
			if(object instanceof FieldError) {
		        FieldError fieldError = (FieldError) object;
		        
		        message = this.getErrorMessage(fieldError.getCode(), fieldError.getArguments());
		    }

		    if(object instanceof ObjectError) {
		        ObjectError objectError = (ObjectError) object;
		        
		        message = this.getMessage(objectError.getCode(), objectError.getArguments());
		    }
		    
		    errors.add(message);
		}
		
		return errors;
	}
	
	public List<Message> handleMessage(String keyCode, Object[] msgArgs) {
		List<Message> errors = new ArrayList<Message>();
		
		errors.add(getMessage(keyCode, msgArgs));
		
		return errors;
	}
	
	/**
	 * Replace SingleQuotation(') in targetString to two-SringQuatation('').<br>
	 * if targetString is null, return null.
	 * @param target targetString
	 * @return replaced String.
	 */
	private String addSingleQuotation(String target) {
		if (target == null) {
			return null;
		}
		return target.replaceAll("'", "''");	
	}

}
