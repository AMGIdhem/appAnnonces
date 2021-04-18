package com.example.demo.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.UserRepository;

public class UserMetierImpl implements UserMetier {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void verification() {
		//userRepository.findByEmail(@RequestParam(name="email"));
		
	}

}
