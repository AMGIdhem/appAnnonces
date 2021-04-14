package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Annonce {
	@Id
	@GeneratedValue
	private Long id;
	private String titre;
	private double prix;
	private String description;
}
