package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.dao.QuartierRepository;
import com.example.demo.dao.TypeLogementRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Quartier;
import com.example.demo.entities.TypeLogement;
import com.example.demo.entities.User;

@SpringBootApplication
public class AppAnnoncesApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AppAnnoncesApplication.class, args);
//		UserRepository userRepository = ctx.getBean(UserRepository.class);
//		User u1 = new User("mahdi", "bouhouch", "mahdi@gmail.com", "123", "0624690069");
//		User u2 = new User("anouar", "bourja", "anouar@gmail.com", "123", "0655548520");
//		userRepository.save(u1);
//		userRepository.save(u2);
//		TypeLogementRepository typeRepo = ctx.getBean(TypeLogementRepository.class);
//		typeRepo.save(new TypeLogement("Studio"));
//		typeRepo.save(new TypeLogement("Chambre"));
//		typeRepo.save(new TypeLogement("Appartement"));
//		QuartierRepository quartRepo = ctx.getBean(QuartierRepository.class);
//		quartRepo.save(new Quartier("Tilila"));
//		quartRepo.save(new Quartier("El Houda"));
//		quartRepo.save(new Quartier("Hay Salam"));
		
	}

}
