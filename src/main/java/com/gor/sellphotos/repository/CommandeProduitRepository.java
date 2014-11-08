package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.CommandeProduit;
import com.gor.sellphotos.dao.Utilisateur;

public interface CommandeProduitRepository extends CrudRepository<CommandeProduit, Long> {
	
    @Query("select cmdProd from CommandeProduit cmdProd, CommandeEleve cmdEleve, Produit prod where cmdProd.commandeEleve = cmdEleve and cmdProd.produit = prod and cmdEleve.identifiant=? and prod.identifiant=?")
	public List<CommandeProduit> findByCmdEleveIdentifiantAndProduitIdentifiant(String commandeEleveIdentifiant, String produitIdentifiant);

}
