package com.demo.JobSearchApp.util;

/**
 * @author ravi
 * Custom Exception Implementation
 */
public class CustomException extends Exception
{

	private static final long serialVersionUID = 1L;
	Integer errorId;
	String errorMessage;
	public CustomException()
	{
		super();
	}
	public CustomException(Integer errorId, String errorMessage){  
		  super(errorMessage);  
		  this.errorMessage=errorMessage;
		  this.errorId=errorId;
		 }
	public String getErrorMessage() {
		return errorMessage;
	}	 
	public Integer getErrorId() {
		return errorId;
	}
	
}