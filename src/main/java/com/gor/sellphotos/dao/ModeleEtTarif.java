package com.gor.sellphotos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ModeleEtTarif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String nomReference;

    @Basic
    private double tva;

    @OneToOne(mappedBy = "modeleEtTarif")
    private Ecole ecole;

    @OneToMany
    private List<Produit> produits;

    public ModeleEtTarif() {
        produits = new ArrayList<Produit>();
    }

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
     * @return the nomReference
     */
    public String getNomReference() {
        return nomReference;
    }

    /**
     * @param nomReference the nomReference to set
     */
    public void setNomReference(String nomReference) {
        this.nomReference = nomReference;
    }

    /**
     * @return the ecole
     */
    public Ecole getEcole() {
        return ecole;
    }

    /**
     * @param ecole the ecole to set
     */
    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

    /**
     * @return the produits
     */
    public List<Produit> getProduits() {
        return produits;
    }

    /**
     * @param produits the produits to set
     */
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    /**
     * @param produit the produit to add
     */
    public void addProduit(Produit produit) {
        this.produits.add(produit);
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

    @Override
    public String toString() {
        return "ModeleEtTarif [id=" + id + ", nbProduits=" + produits.size() + "]";
    }

}
