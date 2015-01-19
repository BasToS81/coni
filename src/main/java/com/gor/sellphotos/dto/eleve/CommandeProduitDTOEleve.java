package com.gor.sellphotos.dto.eleve;

import com.gor.sellphotos.dto.ProduitDTO;

public class CommandeProduitDTOEleve {

    private ProduitDTO produit;

    private int quantite;

    private double montantParentHT;

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

}
