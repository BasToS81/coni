package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.Utilisateur;

public interface ResponsableRepository extends CrudRepository<Utilisateur, Long> {
	
	public List<Utilisateur> findByName(String name);

}
