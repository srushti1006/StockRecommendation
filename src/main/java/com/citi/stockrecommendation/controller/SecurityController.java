package com.citi.stockrecommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@RequestMapping("/")
//	means the default request will redirect to index.jsp
	public String index()
	{
		return "index.jsp";
	}
	@RequestMapping("/login")
	public String login()
	{
		return "login.jsp";
	}
	@RequestMapping("/logout-success")
	public String logout()
	{
		return "logout.jsp";
	}
}
