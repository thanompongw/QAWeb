/******************************************************
 * Program History
 * 
 * Project Name	            :  APS
 * Client Name				:  
 * Package Name             :  co.th.genth.aps.common.util
 * Program ID 	            :  ScriptHandler.java
 * Program Description	    :  
 * Environment	 	        :  
 * Author					:  thanompongw
 * Version					:  1.0
 * Creation Date            :  May 22, 2012
 *
 * Modification History	    :
 * Version	   Date		   Person Name		Chng Req No		Remarks
 *
 * Copyright(C) 2011-Generali Life Insurance (Thailand) Co.,Ltd. All Rights Reserved.             
 ********************************************************/
package co.th.genth.qa.common.util;

import java.io.*;

/**
 * @author Thanompong.W
 */
public class ScriptHandler {
	
	private static ScriptHandler handler;
	private boolean _waitForResult;
	
	/**
	 * Default Constructor of ScriptHandler.java
	 */
	public ScriptHandler() {
		_waitForResult = true;
	}
	
	public ScriptHandler(boolean waitForResult) {
		_waitForResult = waitForResult;
	}
	
	public static synchronized ScriptHandler getInstance() {
		if (handler == null) {
			handler = new ScriptHandler();
		}
		
		return handler;
	}
	
	public int executeCmd(String cmd) {
		return executeCmd(cmd, null);
	}
	
	public int silentExecuteCmd(String cmd) {
		int retCode = 1;
		
		try {
			Runtime rtime = Runtime.getRuntime();
			Process child = rtime.exec(cmd);
			
			if (_waitForResult) {
				child.waitFor();
				retCode = child.exitValue();
			} else {
				retCode = 0;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
		}
		
		return retCode;
	}
	
	public int executeCmd(String cmd, String output) {
		int retCode = 1;
		
		try {
			Runtime rtime = Runtime.getRuntime();
			Process child = rtime.exec(cmd);
			
			// any output?
			StreamGobbler outputGobbler = new StreamGobbler(child.getInputStream(),
			                                                "OUTPUT",
			                                                output);
			outputGobbler.start();
			
			if (_waitForResult) {
				child.waitFor();
				retCode = child.exitValue();
			} else {
				retCode = 0;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
		}
		
		return retCode;
	}
	
	class StreamGobbler extends Thread {
		InputStream is;
		String type;
		String outputfilename;
		
		StreamGobbler(InputStream is, String type, String output) {
			this.is = is;
			this.type = type;
			this.outputfilename = output;
		}
		
		public void run() {
			BufferedWriter bw = null;
			
			try {
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				String outputLine;
				
				if (outputfilename != null) {
					bw = new BufferedWriter(new FileWriter(outputfilename));
				}
				
				while ((line = br.readLine()) != null) {
					outputLine = type + "> " + line;
					
					// write to output filename if needed
					if (bw != null) {
						bw.write(outputLine);
						bw.newLine();
					}
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				try {
					if (bw != null) {
						bw.flush();
						bw.close();
					}
				} catch (Exception ex) {
					
				}
			}
		}
	}
	
}
