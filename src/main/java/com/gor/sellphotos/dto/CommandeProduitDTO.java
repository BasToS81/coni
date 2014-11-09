package com.gor.sellphotos.dto;



public class CommandeProduitDTO {
	
	private Long id;

	private CommandeEleveDTO commandeEleve;
	
	private ProduitDTO produit;
	
	private int quantite;

	private double montant;

    
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
    public CommandeEleveDTO getCommandeEleve() {
        return commandeEleve;
    }

    
    /**
     * @param commandeEleve the commandeEleve to set
     */
    public void setCommandeEleve(CommandeEleveDTO commandeEleve) {
        this.commandeEleve = commandeEleve;
    }

    
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
