package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Dossier {
	@Id
	@GeneratedValue
	private long id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateEntree;
	private String etablissement;
	private String garantNom;
	private String garantPrenom;
	private String garantRelation;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDateEntree() {
		return dateEntree;
	}
	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}
	public String getEtablissement() {
		return etablissement;
	}
	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}
	public String getGarantNom() {
		return garantNom;
	}
	public void setGarantNom(String garantNom) {
		this.garantNom = garantNom;
	}
	public String getGarantPrenom() {
		return garantPrenom;
	}
	public void setGarantPrenom(String garantPrenom) {
		this.garantPrenom = garantPrenom;
	}
	public String getGarantRelation() {
		return garantRelation;
	}
	public void setGarantRelation(String garantRelation) {
		this.garantRelation = garantRelation;
	}
	public Dossier(Date dateEntree, String etablissement, String garantNom, String garantPrenom,
			String garantRelation) {
		super();
		this.dateEntree = dateEntree;
		this.etablissement = etablissement;
		this.garantNom = garantNom;
		this.garantPrenom = garantPrenom;
		this.garantRelation = garantRelation;
	}
	
	public Dossier() {
		
	}
	
}
