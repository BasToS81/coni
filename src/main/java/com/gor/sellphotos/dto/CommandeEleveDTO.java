package com.gor.sellphotos.dto;

import java.util.Date;
import java.util.List;

public class CommandeEleveDTO {

    private Long id;

    private String identifiant;

    private Date dateCommande;

    private String moyenPayement;

    private String statut;

    private Date dateValidation;

    private List<CommandeProduitDTO> produitsCommandes;

    private CommandeEcoleDTO commandeEcole;

    private double montant;

    private Date dateLivraison;

    private String identifiantEleve;

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
     * @return the commandeEcole
     */
    public CommandeEcoleDTO getCommandeEcole() {
        return commandeEcole;
    }

    /**
     * @param commandeEcole the commandeEcole to set
     */
    public void setCommandeEcole(CommandeEcoleDTO commandeEcole) {
        this.commandeEcole = commandeEcole;
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

}
