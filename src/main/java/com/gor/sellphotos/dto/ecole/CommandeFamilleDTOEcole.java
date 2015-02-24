package com.gor.sellphotos.dto.ecole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommandeFamilleDTOEcole {

    private Long identifiant;

    private Date dateCommande;

    private String moyenPayement;

    private String statut;

    private Date dateValidation;

    private double montantParentHT;

    private double montantEcoleHT;

    private double montantParentTTC;

    private double montantEcoleTTC;

    private Date dateLivraison;

    private String typeCommande;

    private String nomEleves;

    private List<CommandeEleveDTOEcole> commandesEleve;

    public CommandeFamilleDTOEcole() {
        commandesEleve = new ArrayList<CommandeEleveDTOEcole>();
        nomEleves = "";
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
     * @return the montantEcoleHT
     */
    public double getMontantEcoleHT() {
        return montantEcoleHT;
    }

    /**
     * @param montantEcoleHT the montantEcoleHT to set
     */
    public void setMontantEcoleHT(double montantEcoleHT) {
        this.montantEcoleHT = montantEcoleHT;
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

    /**
     * @return the commandesEleve
     */
    public List<CommandeEleveDTOEcole> getCommandesEleve() {
        return commandesEleve;
    }

    /**
     * @param commandesEleve the commandesEleve to set
     */
    public void setCommandesEleve(List<CommandeEleveDTOEcole> commandesEleve) {
        this.commandesEleve = commandesEleve;
    }

    /**
     * @param commandeEleve the commandeEleve to add
     */
    public void addCommandeEleve(CommandeEleveDTOEcole commandeEleve) {
        this.commandesEleve.add(commandeEleve);
        addNomEleve(commandeEleve.getEleve().getNom());
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

    /**
     * @return the montantEcoleTTC
     */
    public double getMontantEcoleTTC() {
        return montantEcoleTTC;
    }

    /**
     * @param montantEcoleTTC the montantEcoleTTC to set
     */
    public void setMontantEcoleTTC(double montantEcoleTTC) {
        this.montantEcoleTTC = montantEcoleTTC;
    }

    /**
     * @return the nomEleves
     */
    public String getNomEleves() {
        return nomEleves;
    }

    /**
     * @param nomEleves the nomEleves to set
     */
    public void setNomEleves(String nomEleves) {
        this.nomEleves = nomEleves;
    }

    /**
     * @param nomEleves the nomEleves to add
     */
    public void addNomEleve(String nomEleve) {
        if (this.nomEleves.length() == 0) {
            this.nomEleves = nomEleve;
        }
        else
            this.nomEleves = this.nomEleves + "," + nomEleve;
    }

    @Override
    public String toString() {
        return "Utilisateur [identifiant=" + identifiant + ", dateCommande=" + dateCommande + ", statut=" + statut + ", montantParentHT=" + montantParentHT
                        + ", montantEcoleHT=" + montantEcoleHT + "]";
    }

}
