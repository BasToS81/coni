package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.Famille;
import com.gor.sellphotos.dao.Utilisateur;

public interface FamilleRepository extends CrudRepository<Famille, Long> {
	
	public List<Famille> findByName(String name);

}
