package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Dossier;

public interface DossierRepository extends JpaRepository<Dossier, Long> {

}
