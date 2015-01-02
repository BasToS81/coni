package com.gor.sellphotos.dto;

import java.util.List;

public class ModeleEtTarifDTO {

    private Long id;

    private EcoleDTO ecole;

    private List<ProduitDTO> produits;

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
    public List<ProduitDTO> getProduits() {
        return produits;
    }

    /**
     * @param modeleEtTarifPrincipal the modeleEtTarifPrincipal to set
     */
    public void setProduits(List<ProduitDTO> produits) {
        this.produits = produits;
    }

}
