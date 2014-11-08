package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.Classe;
import com.gor.sellphotos.dao.Eleve;
import com.gor.sellphotos.dao.Utilisateur;

public interface ClasseRepository extends CrudRepository<Classe, Long> {
	
	public List<Classe> findByNom(String nom);

}
