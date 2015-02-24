package com.gor.sellphotos.dto;

public class ClasseSyntheseDTO {

    private Long id;

    private String identifiantChiffre;

    private String nom;

    private int nbEleves;

    private int nbCommandes;

    private double montantTotalEcoleTTC;

    private double montantTotalEcoleHT;

    private double montantTotalParentTTC;

    private double montantTotalParentHT;

    private double montantRestantAPayerEcoleTTC;

    private double montantRestantAPayerEcoleHT;

    private double montantRestantAPayerParentTTC;

    private double montantRestantAPayerParentHT;

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nomClasse) {
        this.nom = nomClasse;
    }

    public int getNbEleves() {
        return nbEleves;
    }

    public void setNbEleves(int nbEleves) {
        this.nbEleves = nbEleves;
    }

    public int getNbCommandes() {
        return nbCommandes;
    }

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

    public String getIdentifiantChiffre() {
        return identifiantChiffre;
    }

    public void setIdentifiantChiffre(String identifiantChiffre) {
        this.identifiantChiffre = identifiantChiffre;
    }

    @Override
    public String toString() {
        return "EcoleSyntheseDTO [identifiantChiffre=" + identifiantChiffre + ", nom=" + nom + ", nbEleves=" + nbEleves + ", nbCommandes="
                        + nbCommandes + "]";
    }

}
