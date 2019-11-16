package com.devlover.app.performr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devlover.app.performr.model.CommonResponse;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class BasicErrorController extends AbstractErrorController {

	//...

	public BasicErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
		// TODO Auto-generated constructor stub
	}

	@RequestMapping
	public ResponseEntity<CommonResponse> error(HttpServletRequest request) {
		
		return ResponseEntity.ok(new CommonResponse("500", "There is some error",""));
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}
}