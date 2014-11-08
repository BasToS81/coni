package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.CommandeEleve;
import com.gor.sellphotos.dao.Utilisateur;

public interface CommandeEleveRepository extends CrudRepository<CommandeEleve, Long> {
	
	public List<CommandeEleve> findByIdentifiant(String identifiant);

}
