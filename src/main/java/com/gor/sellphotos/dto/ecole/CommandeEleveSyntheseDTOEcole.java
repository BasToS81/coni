package com.gor.sellphotos.dto.ecole;

import java.util.ArrayList;
import java.util.List;

import com.gor.sellphotos.dto.EleveDTO;

public class CommandeEleveSyntheseDTOEcole {

    private List<CommandeProduitDTOEcole> produitsCommandes;

    private double montantTotalParentHT;

    private double montantTotalEcoleHT;

    private double montantTotalParentTTC;

    private double montantTotalEcoleTTC;

    private double montantRestantAPayerParentTTC;

    private double montantRestantAPayerEcoleTTC;

    private double montantRestantAPayerParentHT;

    private double montantRestantAPayerEcoleHT;

    private EleveDTO eleve;

    private double tva;

    public CommandeEleveSyntheseDTOEcole() {
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
    public double getMontantTotalParentHT() {
        return montantTotalParentHT;
    }

    /**
     * @param montant the montant to set
     */
    public void setMontantTotalParentHT(double montantTotalParentHT) {
        this.montantTotalParentHT = montantTotalParentHT;
    }

    /**
     * @return the montantEcoleHT
     */
    public double getMontantTotalEcoleHT() {
        return montantTotalEcoleHT;
    }

    /**
     * @param montantEcoleHT the montantEcoleHT to set
     */
    public void setMontantTotalEcoleHT(double montantTotalEcoleHT) {
        this.montantTotalEcoleHT = montantTotalEcoleHT;
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

}
