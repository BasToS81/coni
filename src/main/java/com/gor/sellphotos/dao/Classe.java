package com.gor.sellphotos.dao;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Classe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


    @Basic
    private String identifiant_chiffre;
    
    
	@Basic
	private String nom;
	
	@ManyToOne
	private Ecole ecole;
	
	@OneToMany(mappedBy = "classe")
	private List<Eleve> eleves;


	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", identifiant_chiffre=" + identifiant_chiffre + ", nbEleves=" + eleves.size() + "]";
	}
	
}
