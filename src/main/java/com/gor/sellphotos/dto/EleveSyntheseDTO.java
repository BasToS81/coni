package com.gor.sellphotos.dto;

public class EleveSyntheseDTO {

    private String nomEleve;

    private Long nbCommandes;

    private Double totalAchat;

    private Double totalVente;

    public String getNomEleve() {
        return nomEleve;
    }

    public void setNomEleve(String nomEleve) {
        this.nomEleve = nomEleve;
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

    @Override
    public String toString() {
        return "ClasseSyntheseDTO [nomEleve=" + nomEleve + ", nbCommandes=" + nbCommandes + ", totalAchat=" + totalAchat + ", totalVente=" + totalVente + "]";
    }

}
