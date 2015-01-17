package com.gor.sellphotos.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Eleve extends Utilisateur {

    @Basic
    private String identifiantChiffre;

    @ManyToOne
    private Famille famille;

    @ManyToOne
    private Classe classe;

    @OneToMany(mappedBy = "eleve")
    private List<CommandeEleve> commandes;

    @Basic
    private Date dateLimiteAcces;

    public Eleve() {

        setRole(Utilisateur.TypeUtilisateur.ELEVE);
    }

    /**
     * @return the identifiant_chiffre
     */
    public String getIdentifiantChiffre() {
        /* TODO : Mettre à jour l'identifiant chiffre */
        return "idChiffreEleve_" + getId();
    }

    /**
     * @param identifiantChiffre the identifiantChiffre to set
     */
    public void setIdentifiantChiffre(String identifiantChiffre) {
        this.identifiantChiffre = identifiantChiffre;
    }

    /**
     * @return the famille
     */
    public Famille getFamille() {
        return famille;
    }

    /**
     * @param famille the famille to set
     */
    public void setFamille(Famille famille) {
        if (famille != null) {
            famille.addEleves(this);
            this.famille = famille;
        }
    }

    /**
     * @return the classe
     */
    public Classe getClasse() {
        return classe;
    }

    /**
     * @param classe the classe to set
     */
    public void setClasse(Classe classe) {
        this.classe = classe;
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

    public String toStringComplet() {
        return "Eleve [id=" + getId() + ", identifiant_chiffre=" + identifiantChiffre + "]";
    }

    @Override
    public String toString() {
        return "Eleve [identifiantChiffre=" + identifiantChiffre + ", dateLimiteAcces=" + dateLimiteAcces + ", toString()=" + super.toString() + "]";
    }

    public List<CommandeEleve> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<CommandeEleve> commandes) {
        this.commandes = commandes;
    }

}
