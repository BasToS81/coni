package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.CommandeEcole;

public interface CommandeEcoleRepository extends CrudRepository<CommandeEcole, Long> {
	
	public CommandeEcole findByIdentifiant(String identifiant);
	
	public List<CommandeEcole> findByStatut(String statut);
	

}
