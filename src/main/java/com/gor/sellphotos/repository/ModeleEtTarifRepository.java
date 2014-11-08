package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.ModeleEtTarif;
import com.gor.sellphotos.dao.Utilisateur;

public interface ModeleEtTarifRepository extends CrudRepository<ModeleEtTarif, Long> {
	
	public List<ModeleEtTarif> findByName(String name);

}
