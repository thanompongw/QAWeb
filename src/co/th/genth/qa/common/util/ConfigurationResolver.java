/**
 * 
 */
package co.th.genth.qa.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Thanopong.W
 */
@Service("configService")
public class ConfigurationResolver {
	
	private Properties properties;
	
	private static final String config = "config.properties";
	private Logger logger = Logger.getLogger(ConfigurationResolver.class);
	
	/**
	 * Default Constructor.
	 */
	public ConfigurationResolver() {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(config);
		
		properties = new Properties();
		try {
			if (is != null) {
				properties.load(is);
			} else {
				logger.error("No config.properties file found");
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
	
	public String getProperty(String keyCode) {
		String message = null;
		if (properties.getProperty(keyCode) != null) {
			message = properties.getProperty(keyCode);
		}
		
		if (message == null) {
			throw new RuntimeException("Can not found message for messsage code = " + keyCode);
		}
		
		return message;
	}
	
	public boolean isPropertyEmpty() {
		boolean empty = true;
		
		if ((properties != null) && (properties.size() > 0)) {
			empty = false;
		}
		
		return empty;
	}	
}
