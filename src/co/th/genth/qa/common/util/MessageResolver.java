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

import co.th.genth.qa.exception.ErrorMessage;

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

	public String getMessage(String keyCode) {
		return this.getMessage(keyCode, null);
	}

	public String getMessage(String keyCode, Object[] msgArgs) {
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

	public ErrorMessage getErrorMessage(String keyCode, Object[] msgArgs) {
		ErrorMessage errorMessage = new ErrorMessage();
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
	
	public List<ErrorMessage> handleFieldErrorMessage(BindingResult result) {
		List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
		
		for (FieldError fieldError : result.getFieldErrors()) {
			ErrorMessage errorMessage = this.getErrorMessage(fieldError.getCode(), fieldError.getArguments());
		    errorMessage.setFieldName(fieldError.getField());
		    
		    errors.add(errorMessage);
		}
		
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
