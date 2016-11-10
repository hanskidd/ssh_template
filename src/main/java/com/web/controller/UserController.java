package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.domain.User;
import com.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	
	
	@Autowired 
	private UserService userService;
	
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(User user) {
		userService.save(user);
		return "success";
	}
}
