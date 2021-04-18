package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Annonce;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

}
