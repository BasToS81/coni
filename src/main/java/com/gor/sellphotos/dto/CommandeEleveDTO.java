package com.gor.sellphotos.dto;

import java.util.List;

public class CommandeEleveDTO {

    private Long id;

    private List<CommandeProduitDTO> produitsCommandes;

    private double montant;

    private String identifiantEleve;

    private String typeCommande;

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
     * @return the produitsCommandes
     */
    public List<CommandeProduitDTO> getProduitsCommandes() {
        return produitsCommandes;
    }

    /**
     * @param produitsCommandes the produitsCommandes to set
     */
    public void setProduitsCommandes(List<CommandeProduitDTO> produitsCommandes) {
        this.produitsCommandes = produitsCommandes;
    }

    /**
     * @return the montant
     */
    public double getMontant() {
        return montant;
    }

    /**
     * @param montant the montant to set
     */
    public void setMontant(double montant) {
        this.montant = montant;
    }

    /**
     * @return the identifiantEleve
     */
    public String getIdentifiantEleve() {
        return identifiantEleve;
    }

    /**
     * @param identifiantEleve the identifiantEleve to set
     */
    public void setIdentifiantEleve(String identifiantEleve) {
        this.identifiantEleve = identifiantEleve;
    }

    /**
     * @return the typeCommande
     */
    public String getTypeCommande() {
        return typeCommande;
    }

    /**
     * @param typeCommande the typeCommande to set
     */
    public void setTypeCommande(String typeCommande) {
        this.typeCommande = typeCommande;
    }

}
