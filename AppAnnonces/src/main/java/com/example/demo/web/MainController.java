package com.example.demo.web;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.AnnonceRepository;
import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Annonce;
import com.example.demo.entities.User;

@Controller
public class MainController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AnnonceRepository annonceRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@Value("${dir.images}")
	String imageDir;
	
	@GetMapping("/login")
	public String account(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		return "login";
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("annonces", annonceRepository.findAll());
		return "index";
	}
	
	@GetMapping("/inscription")
	public String inscription(Model model) {
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("user", new User());
		return "inscription";
	}
	
	@RequestMapping(value="/getPhoto", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception {
		File f = new File(imageDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/getAnnonce")
	public String getAnnonce(Long id, Model model) throws Exception {
		Annonce annonce = annonceRepository.getOne(id);
		model.addAttribute("annonce", annonce);
		return "annonce";
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
//	@RequestMapping(value="/user", method = RequestMethod.POST)
//	public String verif(@RequestParam(name="email") String email, @RequestParam(name="mdp") String mdp) {
//		List<User> usrs = userRepository.findAll();
//		for(User u : usrs) {
//			if(u.getEmail().equals(email) && u.getMdp().equals(mdp)) {
//				return "index";
//			}
//		}
//		return "account";
//	}
	
	

}
