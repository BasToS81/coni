package com.gor.sellphotos.dto;

import java.util.Date;

public class EleveDTO {

    private long id;

    private String identifiant;

    private String identifiantChiffre;

    private String nomClasse;

    private String nomEleve;

    private String cheminAccesImageEleve;

    private String cheminAccesImageGroupe;

    private Date dateLimiteAcces;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
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
     * @return the identifiantChiffre
     */
    public String getIdentifiantChiffre() {
        return identifiantChiffre;
    }

    /**
     * @param identifiantChiffre the identifiantChiffre to set
     */
    public void setIdentifiantChiffre(String identifiantChiffre) {
        this.identifiantChiffre = identifiantChiffre;
    }

    /**
     * @return the nomClasse
     */
    public String getNomClasse() {
        return nomClasse;
    }

    /**
     * @param nomClasse the nomClasse to set
     */
    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    /**
     * @return the nomEleve
     */
    public String getNomEleve() {
        return nomEleve;
    }

    /**
     * @param nomEleve the nomEleve to set
     */
    public void setNomEleve(String nomEleve) {
        this.nomEleve = nomEleve;
    }

    /**
     * @return the cheminAccesImageEleve
     */
    public String getCheminAccesImageEleve() {
        return cheminAccesImageEleve;
    }

    /**
     * @param cheminAccesImageEleve the cheminAccesImageEleve to set
     */
    public void setCheminAccesImageEleve(String cheminAccesImageEleve) {
        this.cheminAccesImageEleve = cheminAccesImageEleve;
    }

    /**
     * @return the cheminAccesImageGroupe
     */
    public String getCheminAccesImageGroupe() {
        return cheminAccesImageGroupe;
    }

    /**
     * @param cheminAccesImageGroupe the cheminAccesImageGroupe to set
     */
    public void setCheminAccesImageGroupe(String cheminAccesImageGroupe) {
        this.cheminAccesImageGroupe = cheminAccesImageGroupe;
    }

    /**
     * @return the dateLimiteAcces
     */
    public Date getDateLimiteAcces() {
        return dateLimiteAcces;
    }

    /**
     * @param dateLimiteAcces the dateLimiteAcces to set
     */
    public void setDateLimiteAcces(Date dateLimiteAcces) {
        this.dateLimiteAcces = dateLimiteAcces;
    }

    @Override
    public String toString() {
        return "Famille [idFamille=" + getIdentifiantChiffre() + ", nomClasse=" + getNomClasse() + ", nomEleve=" + getNomEleve() + "]";
    }

}
