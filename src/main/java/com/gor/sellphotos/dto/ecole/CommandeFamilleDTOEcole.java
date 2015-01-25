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

    private Date dateLivraison;

    private String typeCommande;

    private double tva;

    private List<CommandeEleveSyntheseDTOEcole> commandesEleve;

    public CommandeFamilleDTOEcole() {
        commandesEleve = new ArrayList<CommandeEleveSyntheseDTOEcole>();
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
    public List<CommandeEleveSyntheseDTOEcole> getCommandesEleve() {
        return commandesEleve;
    }

    /**
     * @param commandesEleve the commandesEleve to set
     */
    public void setCommandesEleve(List<CommandeEleveSyntheseDTOEcole> commandesEleve) {
        this.commandesEleve = commandesEleve;
    }

    /**
     * @param commandeEleve the commandeEleve to add
     */
    public void addCommandeEleve(CommandeEleveSyntheseDTOEcole commandeEleve) {
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

    @Override
    public String toString() {
        return "Utilisateur [identifiant=" + identifiant + ", dateCommande=" + dateCommande + ", statut=" + statut + ", montantParentHT=" + montantParentHT
                        + ", montantEcoleHT=" + montantEcoleHT + "]";
    }

}
