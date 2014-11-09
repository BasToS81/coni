package com.gor.sellphotos.dao;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Famille extends Utilisateur {


	@OneToMany(mappedBy = "famille")
	private List<Eleve> eleves;
	
	@OneToOne
	private Utilisateur utilisateur;
	
    @ManyToOne
    private Ecole ecole;
    
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
		return "Famille [id=" + getId() + ", nbEleves=" + eleves.size() + "]";
	}
	
}
