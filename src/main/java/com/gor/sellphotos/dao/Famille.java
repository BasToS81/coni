package com.gor.sellphotos.dao;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Famille {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "famille")
	private List<Eleve> eleves;
	
	@OneToOne
	private Utilisateur utilisateur;
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the eleves
	 */
	public List<Eleve> getEleves() {
		return eleves;
	}




	/**
	 * @param eleves the eleves to set
	 */
	public void setEleves(List<Eleve> eleves) {
		this.eleves = eleves;
	}




	
    /**
     * @return the utilisateur
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }




    
    /**
     * @param utilisateur the utilisateur to set
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }




    @Override
	public String toString() {
		return "Famille [id=" + id + ", nbEleves=" + eleves.size() + "]";
	}
	
}
