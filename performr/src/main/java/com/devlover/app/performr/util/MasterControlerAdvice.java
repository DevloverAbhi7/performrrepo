package com.devlover.app.performr.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.devlover.app.performr.model.CommonResponse;
@ControllerAdvice
@RestController
public class MasterControlerAdvice extends ResponseEntityExceptionHandler{
	 @ExceptionHandler(CustomException.class)
	  public final ResponseEntity<CommonResponse> handleUserNotFoundException(CustomException ex, WebRequest request) {
	 
	    return  ResponseEntity.ok(new CommonResponse("400",ex.getLocalizedMessage(),""));
	  }
}
