package com.gor.sellphotos.dao;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String identifiant;

    @Basic
    private String designation;

    @Basic
    private double prixParentHT;

    @Basic
    private double prixEcoleHT;

    @Basic
    private int ordre;

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
     * @return the identifiant
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the order
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * @param order the order to set
     */
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    /**
     * @return the prixParentHT
     */
    public double getPrixParentHT() {
        return prixParentHT;
    }

    /**
     * @param prixParentHT the prixParentHT to set
     */
    public void setPrixParentHT(double prixParentHT) {
        this.prixParentHT = prixParentHT;
    }

    /**
     * @return the prixEcoleHT
     */
    public double getPrixEcoleHT() {
        return prixEcoleHT;
    }

    /**
     * @param prixEcoleHT the prixEcoleHT to set
     */
    public void setPrixEcoleHT(double prixEcoleHT) {
        this.prixEcoleHT = prixEcoleHT;
    }

    @Override
    public String toString() {
        return "Produit [id=" + id + ", designation=" + designation + ", identifiant=" + identifiant + ", PrixParentHT=" + prixParentHT + ", PrixEcoleHT="
                        + prixEcoleHT + ", ordre=" + ordre
                        + "]";
    }

}
