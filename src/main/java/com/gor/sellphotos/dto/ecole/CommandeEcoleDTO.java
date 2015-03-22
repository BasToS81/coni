package com.gor.sellphotos.dto.ecole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gor.sellphotos.dto.EcoleDTO;
import com.gor.sellphotos.utils.DateUtils;

public class CommandeEcoleDTO {

    private Long id;

    private String identifiant;

    private EcoleDTO ecole;

    private String dateCommande;

    private String statut;

    private String dateValidation;

    private List<CommandeFamilleDTOEcole> commandesFamilles;

    private String dateLivraison;

    private double montantParentHT;

    private double montantEcoleHT;

    private double montantParentTTC;

    private double montantEcoleTTC;

    public CommandeEcoleDTO() {
        commandesFamilles = new ArrayList<CommandeFamilleDTOEcole>();
    }

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
     * @return the dateCommande
     */
    public String getDateCommande() {
        return dateCommande;
    }

    /**
     * @param dateCommande the dateCommande to set
     */
    public void setDateCommandeFromDate(Date dateCommande) {
        this.dateCommande = DateUtils.formatDate(dateCommande);
    }

    /**
     * @param dateCommande the dateCommande to set
     */
    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
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
    public String getDateValidation() {
        return dateValidation;
    }

    /**
     * @param dateValidation the dateValidation to set
     */
    public void setDateValidationFromDate(Date dateValidation) {
        this.dateValidation = DateUtils.formatDate(dateValidation);
    }

    /**
     * @param dateValidation the dateValidation to set
     */
    public void setDateValidation(String dateValidation) {
        this.dateValidation = dateValidation;
    }

    /**
     * @return the commandesEleves
     */
    public List<CommandeFamilleDTOEcole> getCommandesFamilles() {
        return commandesFamilles;
    }

    /**
     * @param commandesEleves the commandesEleves to set
     */
    public void setCommandesFamilles(List<CommandeFamilleDTOEcole> commandesFamilles) {
        this.commandesFamilles = commandesFamilles;
    }

    /**
     * @param commandesEleves the commandesEleves to set
     */
    public void addCommandeFamille(CommandeFamilleDTOEcole commandeFamille) {
        this.commandesFamilles.add(commandeFamille);
    }

    /**
     * @return the dateLivraison
     */
    public String getDateLivraison() {
        return dateLivraison;
    }

    /**
     * @param dateLivraison the dateLivraison to set
     */
    public void setDateLivraisonFromDate(Date dateLivraison) {
        this.dateLivraison = DateUtils.formatDate(dateLivraison);
    }

    /**
     * @param dateLivraison the dateLivraison to set
     */
    public void setDateLivraison(String dateLivraison) {
        this.dateLivraison = dateLivraison;
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

}
