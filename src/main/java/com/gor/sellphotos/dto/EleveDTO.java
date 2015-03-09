package com.gor.sellphotos.dto;

import java.util.Date;

import com.gor.sellphotos.utils.DateUtils;

public class EleveDTO {

    private long id;

    private String identifiant;

    private String identifiantChiffre;

    private String nomClasse;

    private String nom;

    private String cheminAccesImageEleve;

    private String cheminAccesImageGroupe;

    private String dateLimiteAcces;

    private int nbCommandes;

    private double montantTotalEcoleTTC;

    private double montantTotalEcoleHT;

    private double montantTotalParentTTC;

    private double montantTotalParentHT;

    private double montantRestantAPayerEcoleTTC;

    private double montantRestantAPayerEcoleHT;

    private double montantRestantAPayerParentTTC;

    private double montantRestantAPayerParentHT;

    private String newMotDePasse;

    private String newNom;

    private String newDateLimiteAcces;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
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
     * @return the identifiantChiffre
     */
    public String getIdentifiantChiffre() {
        return identifiantChiffre;
    }

    /**
     * @param identifiantChiffre the identifiantChiffre to set
     */
    public void setIdentifiantChiffre(String identifiantChiffre) {
        this.identifiantChiffre = identifiantChiffre;
    }

    /**
     * @return the nomClasse
     */
    public String getNomClasse() {
        return nomClasse;
    }

    /**
     * @param nomClasse the nomClasse to set
     */
    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    /**
     * @return the nomEleve
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nomEleve the nomEleve to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the cheminAccesImageEleve
     */
    public String getCheminAccesImageEleve() {
        return cheminAccesImageEleve;
    }

    /**
     * @param cheminAccesImageEleve the cheminAccesImageEleve to set
     */
    public void setCheminAccesImageEleve(String cheminAccesImageEleve) {
        this.cheminAccesImageEleve = cheminAccesImageEleve;
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

    /**
     * @return the dateLimiteAcces
     */
    public String getDateLimiteAcces() {
        return dateLimiteAcces;
    }

    /**
     * @param dateLimiteAcces the dateLimiteAcces to set
     */
    public void setDateLimiteAccesFromDate(Date dateLimiteAcces) {
        this.dateLimiteAcces = DateUtils.formatDate(dateLimiteAcces);
    }

    /**
     * @param dateLimiteAcces the dateLimiteAcces to set
     */
    public void setDateLimiteAcces(String dateLimiteAcces) {
        this.dateLimiteAcces = dateLimiteAcces;
    }

    /**
     * @return the nbCommandes
     */
    public int getNbCommandes() {
        return nbCommandes;
    }

    /**
     * @param nbCommandes the nbCommandes to set
     */
    public void setNbCommandes(int nbCommandes) {
        this.nbCommandes = nbCommandes;
    }

    /**
     * @return the montantTotalEcoleTTC
     */
    public double getMontantTotalEcoleTTC() {
        return montantTotalEcoleTTC;
    }

    /**
     * @param montantTotalEcoleTTC the montantTotalEcoleTTC to set
     */
    public void setMontantTotalEcoleTTC(double montantTotalEcoleTTC) {
        this.montantTotalEcoleTTC = montantTotalEcoleTTC;
    }

    /**
     * @return the montantTotalEcoleHT
     */
    public double getMontantTotalEcoleHT() {
        return montantTotalEcoleHT;
    }

    /**
     * @param montantTotalEcoleHT the montantTotalEcoleHT to set
     */
    public void setMontantTotalEcoleHT(double montantTotalEcoleHT) {
        this.montantTotalEcoleHT = montantTotalEcoleHT;
    }

    /**
     * @return the montantTotalParentTTC
     */
    public double getMontantTotalParentTTC() {
        return montantTotalParentTTC;
    }

    /**
     * @param montantTotalParentTTC the montantTotalParentTTC to set
     */
    public void setMontantTotalParentTTC(double montantTotalParentTTC) {
        this.montantTotalParentTTC = montantTotalParentTTC;
    }

    /**
     * @return the montantTotalParentHT
     */
    public double getMontantTotalParentHT() {
        return montantTotalParentHT;
    }

    /**
     * @param montantTotalParentHT the montantTotalParentHT to set
     */
    public void setMontantTotalParentHT(double montantTotalParentHT) {
        this.montantTotalParentHT = montantTotalParentHT;
    }

    /**
     * @return the montantRestantAPayerEcoleTTC
     */
    public double getMontantRestantAPayerEcoleTTC() {
        return montantRestantAPayerEcoleTTC;
    }

    /**
     * @param montantRestantAPayerEcoleTTC the montantRestantAPayerEcoleTTC to set
     */
    public void setMontantRestantAPayerEcoleTTC(double montantRestantAPayerEcoleTTC) {
        this.montantRestantAPayerEcoleTTC = montantRestantAPayerEcoleTTC;
    }

    /**
     * @return the montantRestantAPayerEcoleHT
     */
    public double getMontantRestantAPayerEcoleHT() {
        return montantRestantAPayerEcoleHT;
    }

    /**
     * @param montantRestantAPayerEcoleHT the montantRestantAPayerEcoleHT to set
     */
    public void setMontantRestantAPayerEcoleHT(double montantRestantAPayerEcoleHT) {
        this.montantRestantAPayerEcoleHT = montantRestantAPayerEcoleHT;
    }

    /**
     * @return the montantRestantAPayerParentTTC
     */
    public double getMontantRestantAPayerParentTTC() {
        return montantRestantAPayerParentTTC;
    }

    /**
     * @param montantRestantAPayerParentTTC the montantRestantAPayerParentTTC to set
     */
    public void setMontantRestantAPayerParentTTC(double montantRestantAPayerParentTTC) {
        this.montantRestantAPayerParentTTC = montantRestantAPayerParentTTC;
    }

    /**
     * @return the montantRestantAPayerParentHT
     */
    public double getMontantRestantAPayerParentHT() {
        return montantRestantAPayerParentHT;
    }

    /**
     * @param montantRestantAPayerParentHT the montantRestantAPayerParentHT to set
     */
    public void setMontantRestantAPayerParentHT(double montantRestantAPayerParentHT) {
        this.montantRestantAPayerParentHT = montantRestantAPayerParentHT;
    }

    /**
     * @return the newMotDePasse
     */
    public String getNewMotDePasse() {
        return newMotDePasse;
    }

    /**
     * @param newMotDePasse the newMotDePasse to set
     */
    public void setNewMotDePasse(String newMotDePasse) {
        this.newMotDePasse = newMotDePasse;
    }

    /**
     * @return the newNom
     */
    public String getNewNom() {
        return newNom;
    }

    /**
     * @param newNom the newNom to set
     */
    public void setNewNom(String newNom) {
        this.newNom = newNom;
    }

    /**
     * @return the newDateLimiteAcces
     */
    public String getNewDateLimiteAcces() {
        return newDateLimiteAcces;
    }

    /**
     * @param newDateLimiteAcces the newDateLimiteAcces to set
     */
    public void setNewDateLimiteAcces(String newDateLimiteAcces) {
        this.newDateLimiteAcces = newDateLimiteAcces;
    }

    /**
     * @param newDateLimiteAcces the newDateLimiteAcces to set
     */
    public void setNewDateLimiteAccesFromDate(Date newDateLimiteAcces) {
        this.newDateLimiteAcces = DateUtils.formatDate(newDateLimiteAcces);
    }

    @Override
    public String toString() {
        return "Famille [idFamille=" + getIdentifiantChiffre() + ", nomClasse=" + getNomClasse() + ", nomEleve=" + getNom() + "]";
    }

}
