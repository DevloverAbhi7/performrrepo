package com.devlover.app.performr.controller;

import java.io.IOException;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.devlover.app.performr.model.CommonResponse;
import com.devlover.app.performr.model.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
public class IndexController 
{
	@GetMapping({"","index"})
		public String getLogin()
		{
			return "index";
		}
	
	@GetMapping("register")
	public String getRegister()
	{
		return "register";
	}
	
	@PostMapping("registeruser")
	public String registerUser(@ModelAttribute User usr ) throws IOException
	{
		final String uri = "http://localhost:8080/register";
		OkHttpClient client = new OkHttpClient();
		ObjectMapper mapper = new ObjectMapper();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, mapper.writeValueAsString(usr));
		Request request = new Request.Builder()
		  .url("http://localhost:8080/register")
		  .post(body)
		  .addHeader("Content-Type", "application/json")
		  .addHeader("Accept", "*/*")
		  .addHeader("Cache-Control", "no-cache")
		  .addHeader("Host", "localhost:8080")
		  .addHeader("Accept-Encoding", "gzip, deflate")
		  .addHeader("Content-Length", "100")
		  .addHeader("Connection", "keep-alive")
		  .addHeader("cache-control", "no-cache")
		  .build();

		Response response = client.newCall(request).execute();
		CommonResponse user = mapper.readValue(response.body().string(), CommonResponse.class);
		
		if(user.getCode().equals("200"))
		{
			return "success";
		}
		else 
		{
			return "failed";
		}
	}
	
	static class User
	{
		
		private String firstname;
		
	
		private String lastname;
		
		
		
		private String username;
		
		private String password;

		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
