package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.Eleve;
import com.gor.sellphotos.dao.Utilisateur;

public interface EleveRepository extends CrudRepository<Eleve, Long> {
	
	public List<Eleve> findByName(String name);

}
