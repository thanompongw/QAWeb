/******************************************************
 * Program History
 * 
 * Project Name	            :  CCS
 * Client Name				:  
 * Package Name             :  co.th.genth.ccs.web.model
 * Program ID 	            :  DataTablesResponse.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  12 ¡.Â. 2555
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The data tables Response.
 * 
 * @author Thanompong.W
 *
 */
public class DataTablesResponse<T> implements Serializable {
	
	/** serialVersionUID property **/
    private static final long serialVersionUID = 8522877847153021858L;
    
    @JsonProperty(value = "iTotalRecords")
    public int totalRecords;

    @JsonProperty(value = "iTotalDisplayRecords")
    public int totalDisplayRecords;

    @JsonProperty(value = "sEcho")
    public String echo;

    @JsonProperty(value = "sColumns")
    public String columns;

    @JsonProperty(value = "aaData")
    public List<T> data = new ArrayList<T>();

	/**
	 * Default Constructor of DataTablesResponse.java
	 */
	public DataTablesResponse() {
	}

	/**
     * @return the totalRecords
     */
    public int getTotalRecords() {
    	return totalRecords;
    }

	/**
     * @param totalRecords the totalRecords to set
     */
    public void setTotalRecords(int totalRecords) {
    	this.totalRecords = totalRecords;
    }

	/**
     * @return the totalDisplayRecords
     */
    public int getTotalDisplayRecords() {
    	return totalDisplayRecords;
    }

	/**
     * @param totalDisplayRecords the totalDisplayRecords to set
     */
    public void setTotalDisplayRecords(int totalDisplayRecords) {
    	this.totalDisplayRecords = totalDisplayRecords;
    }

	/**
     * @return the echo
     */
    public String getEcho() {
    	return echo;
    }

	/**
     * @param echo the echo to set
     */
    public void setEcho(String echo) {
    	this.echo = echo;
    }

	/**
     * @return the columns
     */
    public String getColumns() {
    	return columns;
    }

	/**
     * @param columns the columns to set
     */
    public void setColumns(String columns) {
    	this.columns = columns;
    }

	/**
     * @return the data
     */
    public List<T> getData() {
    	return data;
    }

	/**
     * @param data the data to set
     */
    public void setData(List<T> data) {
    	this.data = data;
    }
	
}
