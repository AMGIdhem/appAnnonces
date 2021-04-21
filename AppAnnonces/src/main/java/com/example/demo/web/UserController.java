package com.example.demo.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/addUser")
	public User save(User u) {
		return userRepository.save(u);
	}
	
	@RequestMapping(value="/findUsers")
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@RequestMapping(value="/getLogedUser")
	public String getLogedUser(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext=(SecurityContext) 
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username=securityContext.getAuthentication().getName();
		List<String> roles = new ArrayList<>();
		for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		for(String r : roles) {
			if(r.equals("ETUDIANT")) {
				return "homeEtudiant";
			}
			else if(r.equals("ANNONCEUR")) {
				return "homeAnnonceur";
			}
		}
		Map<String, Object> params=new HashMap<>();
		params.put("username", username);
		params.put("roles", roles);
		return "login";
	}
}
