package com.devlover.app.performr.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devlover.app.performr.config.JwtTokenUtil;
import com.devlover.app.performr.model.CommonResponse;
import com.devlover.app.performr.model.JwtRequest;
import com.devlover.app.performr.model.JwtResponse;
import com.devlover.app.performr.model.OtpDetails;
import com.devlover.app.performr.model.Users;
import com.devlover.app.performr.service.JwtUserDetailsService;
import com.devlover.app.performr.service.OtpService;
import com.devlover.app.performr.util.Constants;
import com.devlover.app.performr.util.CustomException;

import lombok.extern.slf4j.Slf4j;


@RestController
@CrossOrigin
@Slf4j
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private OtpService userService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new CommonResponse(Constants.SUCCESSCODE, Constants.SUCCESSMESSAGE, new JwtResponse(token)));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<CommonResponse> saveUser(@Valid @RequestBody Users user, Errors errors) throws CustomException,MethodArgumentNotValidException {
		try {
		if (errors.hasErrors()) {
				 throw new CustomException(errors.getFieldError().getDefaultMessage());
			    }
		log.debug("info", "inside register");
		return ResponseEntity.ok(userDetailsService.save(user));
		 }
		catch (RuntimeException e) 
		{
			if(e.getMessage().contains("ConstraintViolationException"))
				throw new CustomException("Number Already Registered");
			else
				throw new CustomException(e.getMessage());
			
		    	}
	}
	@RequestMapping(value = "/generateotp", method = RequestMethod.POST)
	public ResponseEntity<CommonResponse> generateOtp(@RequestBody Users user)
	{
		OtpDetails otpobj = userService.generateOtp(user);
		return ResponseEntity.ok(new CommonResponse(Constants.SUCCESSCODE, Constants.SUCCESSMESSAGE, otpobj));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}