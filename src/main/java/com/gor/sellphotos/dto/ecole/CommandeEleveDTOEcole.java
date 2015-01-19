package com.gor.sellphotos.dto.ecole;

import java.util.List;

import com.gor.sellphotos.dto.EleveDTO;

public class CommandeEleveDTOEcole {

    private Long id;

    private List<CommandeProduitDTOEcole> produitsCommandes;

    private double montantParentHT;

    private double montantEcoleHT;

    private EleveDTO eleve;

    private String typeCommande;

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

}
