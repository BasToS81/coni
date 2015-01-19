package com.gor.sellphotos.dto;

public class ProduitDTO {

    private Long id;

    private String identifiant;

    private String designation;

    private double prixParentHT;

    private double prixEcoleHT;

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
     * @return the prix_ecole_ttc
     */
    public double getPrixEcoleHT() {
        return prixEcoleHT;
    }

    /**
     * @param prix_ecole_ttc the prix_ecole_ttc to set
     */
    public void setPrixEcoleHT(double prixEcoleHT) {
        this.prixEcoleHT = prixEcoleHT;
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

}
