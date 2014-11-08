package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.CommandeEcole;

public interface CommandeEcoleRepository extends CrudRepository<CommandeEcole, Long> {
	
	public List<CommandeEcole> findByIdentifiant(String identifiant);

}
