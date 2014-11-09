package com.gor.sellphotos.dto;

import java.util.List;


public class ModeleEtTarifDTO {
	
	private Long id;

	private EcoleDTO ecole;
	
	private List<ProduitDTO> modeleEtTarifPrincipal;
	
	private List<ProduitDTO> modeleEtTarifSupplementaire;

    
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
     * @return the modeleEtTarifPrincipal
     */
    public List<ProduitDTO> getModeleEtTarifPrincipal() {
        return modeleEtTarifPrincipal;
    }

    
    /**
     * @param modeleEtTarifPrincipal the modeleEtTarifPrincipal to set
     */
    public void setModeleEtTarifPrincipal(List<ProduitDTO> modeleEtTarifPrincipal) {
        this.modeleEtTarifPrincipal = modeleEtTarifPrincipal;
    }

    
    /**
     * @return the modeleEtTarifSupplementaire
     */
    public List<ProduitDTO> getModeleEtTarifSupplementaire() {
        return modeleEtTarifSupplementaire;
    }

    
    /**
     * @param modeleEtTarifSupplementaire the modeleEtTarifSupplementaire to set
     */
    public void setModeleEtTarifSupplementaire(List<ProduitDTO> modeleEtTarifSupplementaire) {
        this.modeleEtTarifSupplementaire = modeleEtTarifSupplementaire;
    }


}
