package com.gor.sellphotos.dao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Responsable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Utilisateur utilisateur;
	
	@ManyToOne
	private Ecole ecole;
	
    @Basic
    private String nom;
	
	
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


    
    
    
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }



    
    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }



    @Override
	public String toString() {
		return "Responsable [id=" + id  + ", nom=" + nom + "]";
	}
	
}
