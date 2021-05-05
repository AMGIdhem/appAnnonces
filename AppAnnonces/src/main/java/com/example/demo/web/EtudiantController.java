package com.example.demo.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Dossier;

@Controller
@RequestMapping("/etudiant")
@Secured(value={"ROLE_ETUDIANT"})
public class EtudiantController {
	@RequestMapping("/home")
	public String Index(Model model) {
		return "homeEtudiant";
	}
	
	@RequestMapping("/deposer")
	public String deposer(Model model) {
		model.addAttribute("dossier", new Dossier());
		return "formDossier";
	}
}
