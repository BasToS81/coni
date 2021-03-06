package com.gor.sellphotos.dto.eleve;

import java.util.List;

import com.gor.sellphotos.dto.EleveDTO;

public class CommandeEleveDTOEleve {

    private Long id;

    private List<CommandeProduitDTOEleve> produitsCommandes;

    private double montantParentHT;

    private double montantParentTTC;

    private EleveDTO eleve;

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
     * @return the produitsCommandes
     */
    public List<CommandeProduitDTOEleve> getProduitsCommandes() {
        return produitsCommandes;
    }

    /**
     * @param produitsCommandes the produitsCommandes to set
     */
    public void setProduitsCommandes(List<CommandeProduitDTOEleve> produitsCommandes) {
        this.produitsCommandes = produitsCommandes;
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

}
