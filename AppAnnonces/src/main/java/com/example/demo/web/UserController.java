package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.UserRepository;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/User")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping("/Index")
	
	
}
