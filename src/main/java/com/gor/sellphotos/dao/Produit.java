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
    private String reference;

    @Basic
    private String label;

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
        return "Produit [id=" + id + ", designation=" + designation + ", reference=" + reference + ", PrixParentHT=" + prixParentHT + ", PrixEcoleHT="
                        + prixEcoleHT + ", ordre=" + ordre
                        + "]";
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

}
