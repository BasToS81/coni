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
public class Responsable extends Utilisateur {
	

	@OneToOne
	private Utilisateur utilisateur;
	
	@ManyToOne
	private Ecole ecole;
	
    
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
		return "Responsable [id=" + getId()  +  "]";
	}
	
}
