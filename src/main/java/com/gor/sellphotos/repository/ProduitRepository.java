package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.Produit;
import com.gor.sellphotos.dao.Responsable;
import com.gor.sellphotos.dao.Utilisateur;

public interface ProduitRepository extends CrudRepository<Produit, Long> {
	
	public Produit findByIdentifiant(String identifiant);

}
