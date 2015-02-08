package com.gor.sellphotos.dto.ecole;

import com.gor.sellphotos.dto.ProduitDTO;

public class CommandeProduitDTOEcole {

    private ProduitDTO produit;

    private int quantite;

    private double montantParentHT;

    private double montantEcoleHT;

    private double montantParentTTC;

    private double montantEcoleTTC;

    /**
     * @return the produit
     */
    public ProduitDTO getProduit() {
        return produit;
    }

    /**
     * @param produit the produit to set
     */
    public void setProduit(ProduitDTO produit) {
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
     * @return the montantParentHT
     */
    public double getMontantParentHT() {
        return montantParentHT;
    }

    /**
     * @param montantParentHT the montantParentHT to set
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

}
