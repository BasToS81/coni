package com.gor.sellphotos.dto.ecole;

import java.util.ArrayList;
import java.util.List;

import com.gor.sellphotos.dto.ModeleEtTarifDTO;

public class CommandeClasseSyntheseDTOEcole {

    private String nom;

    private List<CommandeElevePayeEtNonPayeDTOEcole> commandeEleve;

    private double montantTotalParentHT;

    private double montantTotalEcoleHT;

    private double montantTotalParentTTC;

    private double montantTotalEcoleTTC;

    private double montantTotalParentHTNonPaye;

    private double montantTotalEcoleHTNonPaye;

    private double montantTotalParentTTCNonPaye;

    private double montantTotalEcoleTTCNonPaye;

    private ModeleEtTarifDTO modeleEtTarif;

    public CommandeClasseSyntheseDTOEcole() {
        commandeEleve = new ArrayList<CommandeElevePayeEtNonPayeDTOEcole>();
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
    public List<CommandeElevePayeEtNonPayeDTOEcole> getCommandeEleve() {
        return commandeEleve;
    }

    /**
     * @param commandeEleveSyntheseDTO the commandeEleveSyntheseDTO to set
     */
    public void setCommandeEleve(List<CommandeElevePayeEtNonPayeDTOEcole> commandeEleve) {
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
    public void addCommandeEleveSynthese(CommandeElevePayeEtNonPayeDTOEcole commandeEleve) {
        this.commandeEleve.add(commandeEleve);

        this.montantTotalParentHT += commandeEleve.getMontantParentHT();
        this.montantTotalEcoleHT += commandeEleve.getMontantEcoleHT();
        this.montantTotalParentTTC += commandeEleve.getMontantParentTTC();
        this.montantTotalEcoleTTC += commandeEleve.getMontantEcoleTTC();

        this.montantTotalParentHTNonPaye += commandeEleve.getMontantParentHTNonPaye();
        this.montantTotalEcoleHTNonPaye += commandeEleve.getMontantEcoleHTNonPaye();
        this.montantTotalParentTTCNonPaye += commandeEleve.getMontantParentTTCNonPaye();
        this.montantTotalEcoleTTCNonPaye += commandeEleve.getMontantEcoleTTCNonPaye();

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

    public double getMontantTotalParentHTNonPaye() {
        return montantTotalParentHTNonPaye;
    }

    public void setMontantTotalParentHTNonPaye(double montantTotalParentHTNonPaye) {
        this.montantTotalParentHTNonPaye = montantTotalParentHTNonPaye;
    }

    public double getMontantTotalEcoleHTNonPaye() {
        return montantTotalEcoleHTNonPaye;
    }

    public void setMontantTotalEcoleHTNonPaye(double montantTotalEcoleHTNonPaye) {
        this.montantTotalEcoleHTNonPaye = montantTotalEcoleHTNonPaye;
    }

    public double getMontantTotalParentTTCNonPaye() {
        return montantTotalParentTTCNonPaye;
    }

    public void setMontantTotalParentTTCNonPaye(double montantTotalParentTTCNonPaye) {
        this.montantTotalParentTTCNonPaye = montantTotalParentTTCNonPaye;
    }

    public double getMontantTotalEcoleTTCNonPaye() {
        return montantTotalEcoleTTCNonPaye;
    }

    public void setMontantTotalEcoleTTCNonPaye(double montantTotalEcoleTTCNonPaye) {
        this.montantTotalEcoleTTCNonPaye = montantTotalEcoleTTCNonPaye;
    }

}
