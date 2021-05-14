package com.example.demo.web;


import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.AnnonceRepository;
import com.example.demo.dao.QuartierRepository;
import com.example.demo.dao.TypeLogementRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Annonce;
import com.example.demo.entities.Quartier;
import com.example.demo.entities.TypeLogement;
import com.example.demo.entities.User;
import com.example.demo.service.UserService;


@Controller
@RequestMapping("/annonceur")
//@Secured(value={"ROLE_ANNONCEUR"})
public class AnnonceurController {
	@Autowired
	TypeLogementRepository typeLogementRepository;
	@Autowired
	QuartierRepository quartierRepository;
	@Autowired
	AnnonceRepository annonceRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Value("${dir.images}")
	String imageDir;
	
	@RequestMapping("/home")
	public String Index(Model model) {
		return "homeAnnonceur";
	}
	
	@RequestMapping("/formAnnonce")
	public String formAnnonce(Model model) {
		List<TypeLogement> types = typeLogementRepository.findAll();
		List<Quartier> quartiers = quartierRepository.findAll();
		model.addAttribute("quartiers", quartiers);
		model.addAttribute("types", types);
		model.addAttribute("annonce", new Annonce());
		return "formAnnonce";
	}
	
	@RequestMapping(value="/saveAnnonce", method=RequestMethod.POST)
	public String save(@Valid Annonce an,
			BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file,
			@RequestParam(name="typeLogement")Long idType,
			@RequestParam(name="quartier")Long idQuartier,
			HttpServletRequest httpServletRequest) throws Exception {
		if(bindingResult.hasErrors()) {
			return "formAnnonce";
		}
		if(!(file.isEmpty())) { an.setPhoto(file.getOriginalFilename());}
		Optional<TypeLogement> typeLogement = typeLogementRepository.findById(idType);
		Optional<Quartier> quartier = quartierRepository.findById(idQuartier);
		an.setTypeLogement(typeLogement.get());
		an.setQuartier(quartier.get());
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext=(SecurityContext) 
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username=securityContext.getAuthentication().getName();
		User user = userRepository.findById(username).get();
		an.setUser(user);
		annonceRepository.save(an);
		if(!(file.isEmpty())) {
			an.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+an.getId()));
		}

		return "redirect:home";
		
	}
	
	@RequestMapping(value="/mesAnnonces")
	public String mesAnnonces(Model model, HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext=(SecurityContext) 
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username=securityContext.getAuthentication().getName();
		List<Annonce> mesAnnonces = annonceRepository.findByUser_username(username);
		model.addAttribute("mesAnnonces", mesAnnonces);
		return "mesAnnonces";
	}
	
	@RequestMapping(value="/getPhoto", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws Exception {
		File f = new File(imageDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/supprimer")
	public String supprimer(Long id) {
		annonceRepository.deleteById(id);
		return "redirect:mesAnnonces";
	}
	
	@RequestMapping(value="/edit")
	public String edit(Long id,Model model) {
		Annonce an = annonceRepository.getOne(id);
		model.addAttribute("annonce", an);
		return "editAnnonce";
	}
	
	@RequestMapping(value="/monProfil")
	public String monProfil(Model model, HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext=(SecurityContext) 
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username=securityContext.getAuthentication().getName();
		User monProfil = userService.findByUsername(username);
		model.addAttribute("monProfil", monProfil);
		return "profilAnnonceur";
	}
}
