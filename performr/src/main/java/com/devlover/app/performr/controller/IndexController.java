package com.devlover.app.performr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController 
{
	@GetMapping("index")
		public String getLogin()
		{
			return "index";
		}
	
	@GetMapping("register")
	public String getRegister()
	{
		return "register";
	}
	
	
}
