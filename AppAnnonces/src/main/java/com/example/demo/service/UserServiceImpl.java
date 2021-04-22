package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void verification() {
		//userRepository.findByEmail(@RequestParam(name="email"));
		
	}
	
	public User addUser(User user) {
		
		return userRepository.save(user);
	}

}
