package com.gor.sellphotos.dto;

import java.util.ArrayList;
import java.util.List;

import com.gor.sellphotos.dto.eleve.CommandeProduitDTOEleve;

public class ModeleEtCommandeDTO {

    private List<ProduitDTO> produits;

    private List<CommandeProduitDTOEleve> produitsCommandes;

    public ModeleEtCommandeDTO() {
        produits = new ArrayList<ProduitDTO>();
        produitsCommandes = new ArrayList<CommandeProduitDTOEleve>();
    }

    /**
     * @return the modeleEtTarif
     */
    public List<ProduitDTO> getProduits() {
        return produits;
    }

    /**
     * @param modeleEtTarif the modeleEtTarif to set
     */
    public void setProduits(List<ProduitDTO> modeleEtTarif) {
        this.produits = modeleEtTarif;
    }

    /**
     * @param produits the produit to add
     */
    public void addProduit(ProduitDTO produit) {
        this.produits.add(produit);
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
     * @param produitCommande the produitCommande to add
     */
    public void addProduitCommande(CommandeProduitDTOEleve produitCommande) {
        this.produitsCommandes.add(produitCommande);
    }

}
