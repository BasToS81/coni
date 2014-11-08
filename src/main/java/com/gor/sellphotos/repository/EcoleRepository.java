package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.Ecole;
import com.gor.sellphotos.dao.Utilisateur;

public interface EcoleRepository extends CrudRepository<Ecole, Long> {
	
	public Ecole findByNumeroEcole(String numeroEcole);

}
