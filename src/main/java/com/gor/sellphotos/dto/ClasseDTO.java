package com.gor.sellphotos.dto;

import java.util.List;


public class ClasseDTO {

    private String identifiant_chiffre;
    
	private String nom;
	
	private EcoleDTO ecole;
	
	private List<EleveDTO> eleves;

    
    /**
     * @return the identifiant_chiffre
     */
    public String getIdentifiant_chiffre() {
        return identifiant_chiffre;
    }

    
    /**
     * @param identifiant_chiffre the identifiant_chiffre to set
     */
    public void setIdentifiant_chiffre(String identifiant_chiffre) {
        this.identifiant_chiffre = identifiant_chiffre;
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

    
    /**
     * @return the eleves
     */
    public List<EleveDTO> getEleves() {
        return eleves;
    }

    
    /**
     * @param eleves the eleves to set
     */
    public void setEleves(List<EleveDTO> eleves) {
        this.eleves = eleves;
    }


	
}
