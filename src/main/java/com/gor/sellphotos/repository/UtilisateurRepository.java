package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.Utilisateur;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
	
	public Utilisateur findByIdentifiant(String identifiant);

}
