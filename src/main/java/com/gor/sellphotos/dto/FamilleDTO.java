package com.gor.sellphotos.dto;

import java.util.List;


public class FamilleDTO  {
	
	private List<EleveDTO> eleves;
	
	private String nomEcole;
	
	private String identifiantChiffreEcole;
	
    private String cheminAccesImageGroupe;

    
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

    
    /**
     * @return the nomEcole
     */
    public String getNomEcole() {
        return nomEcole;
    }

    
    /**
     * @param nomEcole the nomEcole to set
     */
    public void setNomEcole(String nomEcole) {
        this.nomEcole = nomEcole;
    }

    
    /**
     * @return the identifiantChiffreEcole
     */
    public String getIdentifiantChiffreEcole() {
        return identifiantChiffreEcole;
    }

    
    /**
     * @param identifiantChiffreEcole the identifiantChiffreEcole to set
     */
    public void setIdentifiantChiffreEcole(String identifiantChiffreEcole) {
        this.identifiantChiffreEcole = identifiantChiffreEcole;
    }

    
    /**
     * @return the cheminAccesImageGroupe
     */
    public String getCheminAccesImageGroupe() {
        return cheminAccesImageGroupe;
    }

    
    /**
     * @param cheminAccesImageGroupe the cheminAccesImageGroupe to set
     */
    public void setCheminAccesImageGroupe(String cheminAccesImageGroupe) {
        this.cheminAccesImageGroupe = cheminAccesImageGroupe;
    }

	

	
}
