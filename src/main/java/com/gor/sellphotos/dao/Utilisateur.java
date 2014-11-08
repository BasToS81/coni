package com.gor.sellphotos.dao;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Utilisateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	private String identifiant;
	
	@Basic
	private String codeAcces;
	
	@Basic
	private String nom;


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
