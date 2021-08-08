package com.demo.JobSearchApp.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.demo.JobSearchApp.dto.JobResponse;

/**
 * @author ravi
 * Global Exception Handler
 */
@ControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler(CustomException.class)
  public ResponseEntity<JobResponse> handleCustomException(CustomException exception){
	  JobResponse errResp = new JobResponse();
	  errResp.setStatusMessage(exception.getErrorMessage());
	  if(exception.getErrorId() == 1)
		  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errResp);	
	  return ResponseEntity.status(HttpStatus.OK).body(errResp);	  
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<JobResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
	  JobResponse errResp = new JobResponse();
	  errResp.setStatusMessage("Invalid Request Parameter: "+exception.getValue());
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResp);	  
  }

  
  
}

