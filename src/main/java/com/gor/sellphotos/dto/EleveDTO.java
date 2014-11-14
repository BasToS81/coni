package com.gor.sellphotos.dto;

import java.util.Date;

public class EleveDTO {

    private String identifiantChiffre;

    private String nomClasse;

    private String nomEleve;

    private String cheminAccesImageEleve;

    private String cheminAccesImageGroupe;

    private Date dateLimiteAcces;

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
