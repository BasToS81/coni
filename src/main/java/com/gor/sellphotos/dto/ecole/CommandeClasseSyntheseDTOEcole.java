package com.gor.sellphotos.dto.ecole;

import java.util.ArrayList;
import java.util.List;

import com.gor.sellphotos.dto.ModeleEtTarifDTO;

public class CommandeClasseSyntheseDTOEcole {

    private String nom;

    private List<CommandeEleveSyntheseDTOEcole> commandeEleveSynthese;

    private ModeleEtTarifDTO modeleEtTarif;

    private double tva;

    public CommandeClasseSyntheseDTOEcole() {
        commandeEleveSynthese = new ArrayList<CommandeEleveSyntheseDTOEcole>();
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
    public List<CommandeEleveSyntheseDTOEcole> getCommandeEleveSynthese() {
        return commandeEleveSynthese;
    }

    /**
     * @param commandeEleveSyntheseDTO the commandeEleveSyntheseDTO to set
     */
    public void setCommandeEleveSynthese(List<CommandeEleveSyntheseDTOEcole> commandeEleveSynthese) {
        this.commandeEleveSynthese = commandeEleveSynthese;
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
    public void addCommandeEleveSynthese(CommandeEleveSyntheseDTOEcole commandeEleveSynthese) {
        this.commandeEleveSynthese.add(commandeEleveSynthese);
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
