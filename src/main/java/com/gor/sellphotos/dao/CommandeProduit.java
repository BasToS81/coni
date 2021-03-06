package com.gor.sellphotos.dao;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.gor.sellphotos.utils.FinanceUtils;

@Entity
public class CommandeProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CommandeEleve commandeEleve;

    @ManyToOne
    private Produit produit;

    @Basic
    private int quantite;

    @Basic
    private double montantParentHT;

    @Basic
    private double montantEcoleHT;

    @Basic
    private double montantParentTTC;

    @Basic
    private double montantEcoleTTC;

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
     * @return the commandeEleve
     */
    public CommandeEleve getCommandeEleve() {
        return commandeEleve;
    }

    /**
     * @param commandeEleve the commandeEleve to set
     */
    public void setCommandeEleve(CommandeEleve commandeEleve) {
        this.commandeEleve = commandeEleve;
    }

    /**
     * @return the produit
     */
    public Produit getProduit() {
        return produit;
    }

    /**
     * @param produit the produit to set
     */
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    /**
     * @return the quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * @param quantite the quantite to set
     */
    public void setQuantite(int quantite, double tva) {
        this.quantite = quantite;

        if (this.produit != null) {

            this.montantEcoleHT = produit.getPrixEcoleHT() * this.quantite;
            this.montantParentHT = produit.getPrixParentHT() * this.quantite;
            this.montantEcoleTTC = FinanceUtils.getTTCFromHT(this.montantEcoleHT, tva);
            this.montantParentTTC = FinanceUtils.getTTCFromHT(this.montantParentHT, tva);
        }

    }

    /**
     * @return the montantHT
     */
    public double getMontantParentHT() {
        return montantParentHT;
    }

    /**
     * @param montantHT the montantHT to set
     */
    public void setMontantParentHT(double montantParentHT) {
        this.montantParentHT = montantParentHT;
    }

    /**
     * @return the montantHT
     */
    public double getMontantEcoleHT() {
        return montantEcoleHT;
    }

    /**
     * @param montantHT the montantHT to set
     */
    public void setMontantEcoleHT(double montantEcoleHT) {
        this.montantEcoleHT = montantEcoleHT;
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

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", quantite=" + quantite + ", montantParentHT=" + montantParentHT + ", montantEcoleHT=" + montantEcoleHT + "]";
    }

}
