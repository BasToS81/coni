package com.gor.sellphotos.dto.ecole;

import java.util.ArrayList;
import java.util.List;

import com.gor.sellphotos.dto.ModeleEtTarifDTO;

public class CommandeClasseSyntheseDTOEcole {

    private String nom;

    private List<CommandeEleveDTOEcole> commandeEleve;

    private double montantTotalParentHT;

    private double montantTotalEcoleHT;

    private double montantTotalParentTTC;

    private double montantTotalEcoleTTC;

    private double newMontantTotalParentHT;

    private double newMontantTotalEcoleHT;

    private double newMontantTotalParentTTC;

    private double newMontantTotalEcoleTTC;

    private ModeleEtTarifDTO modeleEtTarif;

    public CommandeClasseSyntheseDTOEcole() {
        commandeEleve = new ArrayList<CommandeEleveDTOEcole>();
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the commandeEleveSyntheseDTO
     */
    public List<CommandeEleveDTOEcole> getCommandeEleve() {
        return commandeEleve;
    }

    /**
     * @param commandeEleveSyntheseDTO the commandeEleveSyntheseDTO to set
     */
    public void setCommandeEleve(List<CommandeEleveDTOEcole> commandeEleve) {
        this.commandeEleve = commandeEleve;
    }

    /**
     * @return the modeleEtTarifDTO
     */
    public ModeleEtTarifDTO getModeleEtTarif() {
        return modeleEtTarif;
    }

    /**
     * @param modeleEtTarifDTO the modeleEtTarifDTO to set
     */
    public void setModeleEtTarif(ModeleEtTarifDTO modeleEtTarif) {
        this.modeleEtTarif = modeleEtTarif;
    }

    /**
     * @param commandeEleveSyntheseDTO the commandeEleveSyntheseDTO to set
     */
    public void addCommandeEleveSynthese(CommandeEleveDTOEcole commandeEleve) {
        this.commandeEleve.add(commandeEleve);

        this.montantTotalParentHT += commandeEleve.getMontantParentHT();

        this.montantTotalEcoleHT += commandeEleve.getMontantEcoleHT();

        this.montantTotalParentTTC += commandeEleve.getMontantParentTTC();

        this.montantTotalEcoleTTC += commandeEleve.getMontantEcoleTTC();

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
     * @return the newMontantTotalParentHT
     */
    public double getNewMontantTotalParentHT() {
        return newMontantTotalParentHT;
    }

    /**
     * @param newMontantTotalParentHT the newMontantTotalParentHT to set
     */
    public void setNewMontantTotalParentHT(double newMontantTotalParentHT) {
        this.newMontantTotalParentHT = newMontantTotalParentHT;
    }

    /**
     * @return the newMontantTotalEcoleHT
     */
    public double getNewMontantTotalEcoleHT() {
        return newMontantTotalEcoleHT;
    }

    /**
     * @param newMontantTotalEcoleHT the newMontantTotalEcoleHT to set
     */
    public void setNewMontantTotalEcoleHT(double newMontantTotalEcoleHT) {
        this.newMontantTotalEcoleHT = newMontantTotalEcoleHT;
    }

    /**
     * @return the newMontantTotalParentTTC
     */
    public double getNewMontantTotalParentTTC() {
        return newMontantTotalParentTTC;
    }

    /**
     * @param newMontantTotalParentTTC the newMontantTotalParentTTC to set
     */
    public void setNewMontantTotalParentTTC(double newMontantTotalParentTTC) {
        this.newMontantTotalParentTTC = newMontantTotalParentTTC;
    }

    /**
     * @return the newMontantTotalEcoleTTC
     */
    public double getNewMontantTotalEcoleTTC() {
        return newMontantTotalEcoleTTC;
    }

    /**
     * @param newMontantTotalEcoleTTC the newMontantTotalEcoleTTC to set
     */
    public void setNewMontantTotalEcoleTTC(double newMontantTotalEcoleTTC) {
        this.newMontantTotalEcoleTTC = newMontantTotalEcoleTTC;
    }

}
