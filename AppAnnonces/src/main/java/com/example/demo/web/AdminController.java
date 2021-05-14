package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;

@Controller
@RequestMapping("/admin")
//@Secured(value={"ROLE_ADMIN"})
public class AdminController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/home")
	public String Index(Model model) {
		return "homeAdmin";
	}
	
	
	@RequestMapping(value="/getAllUsers")
	public String getAllUsers(Model model) {
		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "users";
	}
	
	@RequestMapping(value="/supprimer")
	public String supprimer(String username) {
		userRepository.deleteById(username);
		return "redirect:getAllUsers";
	}

}
