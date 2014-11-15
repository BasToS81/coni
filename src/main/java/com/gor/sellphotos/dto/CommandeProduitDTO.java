package com.gor.sellphotos.dto;

public class CommandeProduitDTO {

    private ProduitDTO produit;

    private int quantite;

    private double montant;

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
     * @return the montant
     */
    public double getMontant() {
        return montant;
    }

    /**
     * @param montant the montant to set
     */
    public void setMontant(double montant) {
        this.montant = montant;
    }

}
