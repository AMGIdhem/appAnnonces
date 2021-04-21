package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/etudiant")
public class EtudiantController {
	@RequestMapping("/home")
	public String Index(Model model) {
		return "homeEtudiant";
	}
}
