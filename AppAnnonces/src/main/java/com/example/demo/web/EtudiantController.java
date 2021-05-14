package com.example.demo.web;

import java.io.File;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.AnnonceRepository;
import com.example.demo.dao.DossierRepository;
import com.example.demo.entities.Dossier;

@Controller
@RequestMapping("/etudiant")
//@Secured(value={"ROLE_ETUDIANT"})
public class EtudiantController {
	
	@Autowired DossierRepository dossierRepository;
	@Autowired AnnonceRepository annonceRepository;
	
	@RequestMapping("/home")
	public String Index(Model model) {
		return "homeEtudiant";
	}
	
	@RequestMapping("/deposer")
	public String deposer(Model model, Long id) {
		model.addAttribute("dossier", new Dossier());
		model.addAttribute("idAnnonce", id);
		return "formDossier";
	}
	
	@RequestMapping(value="/saveDossier", method=RequestMethod.POST)
	public String saveDossier(Dossier d 
			,@RequestParam(name="idAnnonce")Long idAnnonce) throws Exception{
		annonceRepository.findById(idAnnonce);
		dossierRepository.save(d);
		return "mesDossiers";
	}
}
