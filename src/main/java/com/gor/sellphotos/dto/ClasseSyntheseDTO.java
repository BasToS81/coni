package com.gor.sellphotos.dto;

public class ClasseSyntheseDTO {

    private String identifiantChiffre;

    private String nomClasse;

    private Long nbEleves;

    private Long nbCommandes;

    private Double totalAchat;

    private Double totalVente;

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public Long getNbEleves() {
        return nbEleves;
    }

    public void setNbEleves(Long nbEleves) {
        this.nbEleves = nbEleves;
    }

    public Long getNbCommandes() {
        return nbCommandes;
    }

    public void setNbCommandes(Long nbCommandes) {
        this.nbCommandes = nbCommandes;
    }

    public Double getTotalAchat() {
        return totalAchat;
    }

    public void setTotalAchat(Double totalAchat) {
        this.totalAchat = totalAchat;
    }

    public Double getTotalVente() {
        return totalVente;
    }

    public void setTotalVente(Double totalVente) {
        this.totalVente = totalVente;
    }

    public String getIdentifiantChiffre() {
        return identifiantChiffre;
    }

    public void setIdentifiantChiffre(String identifiantChiffre) {
        this.identifiantChiffre = identifiantChiffre;
    }

    @Override
    public String toString() {
        return "EcoleSyntheseDTO [identifiantChiffre=" + identifiantChiffre + ", nomClasse=" + nomClasse + ", nbEleves=" + nbEleves + ", nbCommandes="
                        + nbCommandes + ", totalAchat=" + totalAchat + ", totalVente=" + totalVente + "]";
    }

}
