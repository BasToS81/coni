package com.gor.sellphotos.dao;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ecole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	private String identifiant_securise;
		
	@Basic
	private String numeroEcole;
	
	@Basic
	private String saison;
	
	@Basic
	private String nomEtablissement;
	
	@Basic
	private String adresseEtablissement;
	
	@Basic
	private String codePostalEtablissement;
	
	@Basic
	private String villeEtablissement;

	@Basic
	private String nomResponsablePrincipal;
	
	@Basic
	private Responsable[] responsables;
	
	@Basic
	private Classe[] classes;
	
	@Basic
	private ModeleEtTarif modeleEtTarif;

	@Basic
	private CommandeEcole commandeEnCours;
	
	@Basic
	private CommandeEcole[] commandeEnLivraison;
	
	@Basic
	private CommandeEcole[] commandeLivrees;
	
	@Basic
	private Date dateLimiteDesCommandesEleves;
	
	@Basic
	private Date dateLimiteDesCommandesEcoles;

	@Basic
	private Date dateLimiteAcces;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * @return the codeAcces
	 */
	public String getCodeAcces() {
		return codeAcces;
	}

	/**
	 * @param codeAcces the codeAcces to set
	 */
	public void setCodeAcces(String codeAcces) {
		this.codeAcces = codeAcces;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", identifiant=" + identifiant + ", code d'acces=" + codeAcces + "]";
	}
	
}
