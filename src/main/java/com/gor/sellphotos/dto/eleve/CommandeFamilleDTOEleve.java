package com.gor.sellphotos.dto.eleve;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommandeFamilleDTOEleve {

    private Long identifiant;

    private Date dateCommande;

    private String moyenPayement;

    private String statut;

    private Date dateValidation;

    private double montantParentHT;

    private double montantParentTTC;

    private Date dateLivraison;

    private double tva;

    private List<CommandeEleveDTOEleve> commandesEleve;

    public CommandeFamilleDTOEleve() {
        commandesEleve = new ArrayList<CommandeEleveDTOEleve>();
    }

    /**
     * @return the identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Long identifiant) {
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
     * @return the montantParentHT
     */
    public double getMontantParentHT() {
        return montantParentHT;
    }

    /**
     * @param montantParentHT the montantParentHT to set
     */
    public void setMontantParentHT(double montantParentHT) {
        this.montantParentHT = montantParentHT;
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
     * @return the commandesEleve
     */
    public List<CommandeEleveDTOEleve> getCommandesEleve() {
        return commandesEleve;
    }

    /**
     * @param commandesEleve the commandesEleve to set
     */
    public void setCommandesEleve(List<CommandeEleveDTOEleve> commandesEleve) {
        this.commandesEleve = commandesEleve;
    }

    /**
     * @param commandeEleve the commandeEleve to add
     */
    public void addCommandeEleve(CommandeEleveDTOEleve commandeEleve) {
        this.commandesEleve.add(commandeEleve);
    }

    /**
     * @return the tva
     */
    public double getTva() {
        return tva;
    }

    /**
     * @param tva the tva to set
     */
    public void setTva(double tva) {
        this.tva = tva;
    }

    /**
     * @return the montantParentTTC
     */
    public double getMontantParentTTC() {
        return montantParentTTC;
    }

    /**
     * @param montantParentTTC the montantParentTTC to set
     */
    public void setMontantParentTTC(double montantParentTTC) {
        this.montantParentTTC = montantParentTTC;
    }

    @Override
    public String toString() {
        return "Utilisateur [identifiant=" + identifiant + ", dateCommande=" + dateCommande + ", statut=" + statut + ", montantParentHT=" + montantParentHT
                        + "]";
    }

}
