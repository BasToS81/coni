package com.gor.sellphotos.dao;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Classe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Basic
	private String nom;
	
	@Basic
	private Ecole ecole;
	
	@Basic
	private Eleve[] eleves;


	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", identifiant=" + identifiant + ", code d'acces=" + codeAcces + "]";
	}
	
}
