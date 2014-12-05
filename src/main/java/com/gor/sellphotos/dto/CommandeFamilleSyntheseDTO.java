package com.gor.sellphotos.dto;

import java.util.Date;

public class CommandeFamilleSyntheseDTO {

    private Long id;

    private String identifiant;

    private Date dateCommande;

    private String moyenPayement;

    private String statut;

    private Date dateValidation;

    private double montant;

    private Date dateLivraison;

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
     * @return the identifiant
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * @return the dateCommande
     */
    public Date getDateCommande() {
        return dateCommande;
    }

    /**
     * @param dateCommande the dateCommande to set
     */
    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    /**
     * @return the moyenPayement
     */
    public String getMoyenPayement() {
        return moyenPayement;
    }

    /**
     * @param moyenPayement the moyenPayement to set
     */
    public void setMoyenPayement(String moyenPayement) {
        this.moyenPayement = moyenPayement;
    }

    /**
     * @return the statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     * @param statut the statut to set
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * @return the dateValidation
     */
    public Date getDateValidation() {
        return dateValidation;
    }

    /**
     * @param dateValidation the dateValidation to set
     */
    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
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
     * @return the dateLivraison
     */
    public Date getDateLivraison() {
        return dateLivraison;
    }

    /**
     * @param dateLivraison the dateLivraison to set
     */
    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
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

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", dateCommande=" + dateCommande + ", statut=" + statut + ", montant=" + montant + "]";
    }

}
