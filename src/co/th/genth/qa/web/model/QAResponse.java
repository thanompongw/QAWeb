/**
 * 
 */
package co.th.genth.qa.web.model;

import java.io.Serializable;
import java.util.List;

import co.th.genth.qa.exception.ErrorMessage;


/**
 * A custom POJO that will be automatically converted to JSON format. 
 * <p>
 * We can use this to send generic messages to our JqGrid, whether a request is successful or not.
 * Of course, you will use plain JavaScript to parse the JSON response. 
 * </p>
 * @author Thanompong.W
*/
public class QAResponse implements Serializable {

	/** serialVersionUID property **/
    private static final long serialVersionUID = -309605945948282955L;
	
	private String statusCode;
	private List<ErrorMessage> errorMessages;

	/**
     * Default Constructor of GenericModel.java
     */
    public QAResponse() {
    }
	
	/**
     * @return the statusCode
     */
    public String getStatusCode() {
    	return statusCode;
    }
    
	/**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
    	this.statusCode = statusCode;
    }

	/**
     * @return the errorMessages
     */
    public List<ErrorMessage> getErrorMessages() {
    	return errorMessages;
    }

	/**
     * @param errorMessages the errorMessages to set
     */
    public void setErrorMessages(List<ErrorMessage> errorMessages) {
    	this.errorMessages = errorMessages;
    }

}
