package com.gor.sellphotos.dto.ecole;

import java.util.ArrayList;
import java.util.List;

import com.gor.sellphotos.dto.EleveDTO;

public class CommandeElevePayeEtNonPayeDTOEcole {

    private Long id;

    private List<CommandeProduitPayeEtNonPayeDTOEcole> produitsCommandes;

    private double montantParentHT;

    private double montantEcoleHT;

    private double montantParentTTC;

    private double montantEcoleTTC;

    private double montantParentHTNonPaye;

    private double montantEcoleHTNonPaye;

    private double montantParentTTCNonPaye;

    private double montantEcoleTTCNonPaye;

    private EleveDTO eleve;

    public CommandeElevePayeEtNonPayeDTOEcole() {
        produitsCommandes = new ArrayList<CommandeProduitPayeEtNonPayeDTOEcole>();
    }

    public double getMontantParentHTNonPaye() {
        return montantParentHTNonPaye;
    }

    public void setMontantParentHTNonPaye(double montantParentHTNonPaye) {
        this.montantParentHTNonPaye = montantParentHTNonPaye;
    }

    public double getMontantEcoleHTNonPaye() {
        return montantEcoleHTNonPaye;
    }

    public void setMontantEcoleHTNonPaye(double montantEcoleHTNonPaye) {
        this.montantEcoleHTNonPaye = montantEcoleHTNonPaye;
    }

    public double getMontantParentTTCNonPaye() {
        return montantParentTTCNonPaye;
    }

    public void setMontantParentTTCNonPaye(double montantParentTTCNonPaye) {
        this.montantParentTTCNonPaye = montantParentTTCNonPaye;
    }

    public double getMontantEcoleTTCNonPaye() {
        return montantEcoleTTCNonPaye;
    }

    public void setMontantEcoleTTCNonPaye(double montantEcoleTTCNonPaye) {
        this.montantEcoleTTCNonPaye = montantEcoleTTCNonPaye;
    }

    /**
     * @param produitsCommandes the produitsCommandes to set
     */
    public void addProduitsCommandes(CommandeProduitPayeEtNonPayeDTOEcole produitCommande) {
        if (produitCommande != null) {
            this.produitsCommandes.add(produitCommande);
            this.montantEcoleHT += produitCommande.getMontantEcoleHT();
            this.montantParentHT += produitCommande.getMontantParentHT();
            this.montantParentTTC += produitCommande.getMontantParentTTC();
            this.montantEcoleTTC += produitCommande.getMontantEcoleTTC();

            this.montantEcoleHTNonPaye += produitCommande.getMontantEcoleHTNonPaye();
            this.montantParentHTNonPaye += produitCommande.getMontantParentHTNonPaye();
            this.montantParentTTCNonPaye += produitCommande.getMontantParentTTCNonPaye();
            this.montantEcoleTTCNonPaye += produitCommande.getMontantEcoleTTCNonPaye();
        }
    }

    public List<CommandeProduitPayeEtNonPayeDTOEcole> getProduitsCommandes() {
        return this.produitsCommandes;
    }

    public void setProduitsCommandes(List<CommandeProduitPayeEtNonPayeDTOEcole> produitsCommandes) {
        this.produitsCommandes = produitsCommandes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EleveDTO getEleve() {
        return eleve;
    }

    public void setEleve(EleveDTO eleve) {
        this.eleve = eleve;
    }

    public double getMontantParentHT() {
        return montantParentHT;
    }

    public void setMontantParentHT(double montantParentHT) {
        this.montantParentHT = montantParentHT;
    }

    public double getMontantEcoleHT() {
        return montantEcoleHT;
    }

    public void setMontantEcoleHT(double montantEcoleHT) {
        this.montantEcoleHT = montantEcoleHT;
    }

    public double getMontantParentTTC() {
        return montantParentTTC;
    }

    public void setMontantParentTTC(double montantParentTTC) {
        this.montantParentTTC = montantParentTTC;
    }

    public double getMontantEcoleTTC() {
        return montantEcoleTTC;
    }

    public void setMontantEcoleTTC(double montantEcoleTTC) {
        this.montantEcoleTTC = montantEcoleTTC;
    }

}
