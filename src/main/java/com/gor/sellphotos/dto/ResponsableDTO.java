package com.gor.sellphotos.dto;

import com.gor.sellphotos.dao.Utilisateur;


public class ResponsableDTO  {
	
	private Utilisateur utilisateur;
	
	private EcoleDTO ecole;

    
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
    public EcoleDTO getEcole() {
        return ecole;
    }

    
    /**
     * @param ecole the ecole to set
     */
    public void setEcole(EcoleDTO ecole) {
        this.ecole = ecole;
    }
	
    
}
