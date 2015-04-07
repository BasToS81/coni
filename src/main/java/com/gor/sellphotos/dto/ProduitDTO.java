package com.gor.sellphotos.dto;

public class ProduitDTO {

    private Long id;

    private String reference;

    private String label;

    private String designation;

    private double prixParentHT;

    private double prixParentTTC;

    private int ordre;

    private String typeProduit;

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
     * @return the prix_parent_ttc
     */
    public double getPrixParentHT() {
        return prixParentHT;
    }

    /**
     * @param prix_parent_ttc the prix_parent_ttc to set
     */
    public void setPrixParentHT(double prixParentHT) {
        this.prixParentHT = prixParentHT;
    }

    /**
     * @return the prix_parent_ttc
     */
    public double getPrixParentTTC() {
        return prixParentTTC;
    }

    /**
     * @param prix_ecole_ttc the prix_ecole_ttc to set
     */
    public void setPrixParentTTC(double prixParentTTC) {
        this.prixParentTTC = prixParentTTC;
    }

    /**
     * @return the ordre
     */
    public int getOrdre() {
        return ordre;
    }

    /**
     * @param ordre the ordre to set
     */
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    /**
     * @return the typeProduit
     */
    public String getTypeProduit() {
        return typeProduit;
    }

    /**
     * @param typeProduit the typeProduit to set
     */
    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
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
