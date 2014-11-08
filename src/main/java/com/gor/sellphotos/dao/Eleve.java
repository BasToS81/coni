package com.gor.sellphotos.dao;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Eleve {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	private String identifiant_securise;
	
	@Basic
	private Classe classe;
	
	@Basic
	private CommandeEleve commandeEnCours;
	
	@Basic
	private CommandeEleve[] commandeEnAttenteValidationPayement;
	
	@Basic
	private CommandeEleve[] commandeEnAttenteValidationEcole;
	
	@Basic
	private CommandeEleve[] commandeEnLivraison;
	
	@Basic
	private CommandeEleve[] commandeLivrees;

	@Basic
	private Date dateLimiteAcces;

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", identifiant=" + identifiant + ", code d'acces=" + codeAcces + "]";
	}
	
}
