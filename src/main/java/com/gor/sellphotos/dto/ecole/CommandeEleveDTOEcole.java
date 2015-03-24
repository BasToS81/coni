package com.gor.sellphotos.dto.ecole;

import java.util.ArrayList;
import java.util.List;

import com.gor.sellphotos.dto.EleveDTO;

public class CommandeEleveDTOEcole {

    private Long id;

    private List<CommandeProduitDTOEcole> produitsCommandes;

    private double montantParentHT;

    private double montantEcoleHT;

    private double montantParentTTC;

    private double montantEcoleTTC;

    private double newMontantParentHT;

    private double newMontantEcoleHT;

    private double newMontantParentTTC;

    private double newMontantEcoleTTC;

    private EleveDTO eleve;

    public CommandeEleveDTOEcole() {
        produitsCommandes = new ArrayList<CommandeProduitDTOEcole>();
    }

    /**
     * @return the produitsCommandes
     */
    public List<CommandeProduitDTOEcole> getProduitsCommandes() {
        return produitsCommandes;
    }

    /**
     * @param produitsCommandes the produitsCommandes to set
     */
    public void setProduitsCommandes(List<CommandeProduitDTOEcole> produitsCommandes) {
        this.produitsCommandes = produitsCommandes;
    }

    /**
     * @param produitsCommandes the produitsCommandes to set
     */
    public void addProduitsCommandes(CommandeProduitDTOEcole produitCommande) {
        this.produitsCommandes.add(produitCommande);
    }

    /**
     * @return the montant
     */
    public double getMontantParentHT() {
        return montantParentHT;
    }

    /**
     * @param montant the montant to set
     */
    public void setMontantParentHT(double montantParentHT) {
        this.montantParentHT = montantParentHT;
    }

    /**
     * @param montant the montant to set
     */
    public void addMontantParentHT(double montantParentHT) {
        this.montantParentHT += montantParentHT;
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
     * @param montantEcoleHT the montantEcoleHT to set
     */
    public void addMontantEcoleHT(double montantEcoleHT) {
        this.montantEcoleHT += montantEcoleHT;
    }

    /**
     * @return the identifiantEleve
     */
    public EleveDTO getEleve() {
        return eleve;
    }

    /**
     * @param identifiantEleve the identifiantEleve to set
     */
    public void setEleve(EleveDTO eleve) {
        this.eleve = eleve;
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
     * @param montantParentTTC the montantParentTTC to set
     */
    public void addMontantParentTTC(double montantParentTTC) {
        this.montantParentTTC += montantParentTTC;
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
     * @param montantEcoleTTC the montantEcoleTTC to set
     */
    public void addMontantEcoleTTC(double montantEcoleTTC) {
        this.montantEcoleTTC += montantEcoleTTC;
    }

    /**
     * @return the newMontantParentHT
     */
    public double getNewMontantParentHT() {
        return newMontantParentHT;
    }

    /**
     * @param newMontantParentHT the newMontantParentHT to set
     */
    public void setNewMontantParentHT(double newMontantParentHT) {
        this.newMontantParentHT = newMontantParentHT;
    }

    /**
     * @return the newMontantEcoleHT
     */
    public double getNewMontantEcoleHT() {
        return newMontantEcoleHT;
    }

    /**
     * @param newMontantEcoleHT the newMontantEcoleHT to set
     */
    public void setNewMontantEcoleHT(double newMontantEcoleHT) {
        this.newMontantEcoleHT = newMontantEcoleHT;
    }

    /**
     * @return the newMontantParentTTC
     */
    public double getNewMontantParentTTC() {
        return newMontantParentTTC;
    }

    /**
     * @param newMontantParentTTC the newMontantParentTTC to set
     */
    public void setNewMontantParentTTC(double newMontantParentTTC) {
        this.newMontantParentTTC = newMontantParentTTC;
    }

    /**
     * @return the newMontantEcoleTTC
     */
    public double getNewMontantEcoleTTC() {
        return newMontantEcoleTTC;
    }

    /**
     * @param newMontantEcoleTTC the newMontantEcoleTTC to set
     */
    public void setNewMontantEcoleTTC(double newMontantEcoleTTC) {
        this.newMontantEcoleTTC = newMontantEcoleTTC;
    }

}
