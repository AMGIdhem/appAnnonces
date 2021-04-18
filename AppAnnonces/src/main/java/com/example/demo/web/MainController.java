package com.example.demo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;

@Controller
public class MainController {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/account")
	public String account(Model model) {
		model.addAttribute("user", new User());
		return "account";
	}
	
	@GetMapping("/")
	public String account() {
		return "account";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String verif(@RequestParam(name="email") String email, @RequestParam(name="mdp") String mdp) {
		List<User> usrs = userRepository.findAll();
		for(User u : usrs) {
			if(u.getEmail().equals(email) && u.getMdp().equals(mdp)) {
				return "index";
			}
		}
		return "account";
	}

}
