package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public void verification() {
		//userRepository.findByEmail(@RequestParam(name="email"));
		
	}
	
	public User addUser(User user,String role) {
		Role r = roleRepository.getOne(role);
		List<Role> roles = new ArrayList<Role>();
		roles.add(r);
		user.setRoles(roles);
		user.setActived(true);
		return userRepository.save(user);
	}

}
