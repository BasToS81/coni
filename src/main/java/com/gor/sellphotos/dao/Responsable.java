package com.gor.sellphotos.dao;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Responsable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	private Utilisateur utilisateur;
	
	
	
	private Ecole ecole;
	
	
	
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


    
    /**
     * @return the ecole
     */
    public Ecole getEcole() {
        return ecole;
    }


    
    /**
     * @param ecole the ecole to set
     */
    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }


    @Override
	public String toString() {
		return "Responsable [id=" + id  + "]";
	}
	
}
