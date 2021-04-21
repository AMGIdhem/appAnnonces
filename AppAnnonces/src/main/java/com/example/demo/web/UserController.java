package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;

@RestController
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
}
