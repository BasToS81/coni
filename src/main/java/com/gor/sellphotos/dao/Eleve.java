package com.gor.sellphotos.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Eleve extends Utilisateur {

    @Basic
    private String identifiantChiffre;

    @ManyToOne
    private Famille famille;

    @ManyToOne
    private Classe classe;

    @OneToOne(mappedBy = "eleve")
    private CommandeEleve commandeEnCours;

    @OneToMany(mappedBy = "eleve")
    private List<CommandeEleve> commandesEnAttenteValidationPayement;

    @OneToMany(mappedBy = "eleve")
    private List<CommandeEleve> commandesEnAttenteValidationEcole;

    @OneToMany(mappedBy = "eleve")
    private List<CommandeEleve> commandesEnLivraison;

    @OneToMany(mappedBy = "eleve")
    private List<CommandeEleve> commandesLivrees;

    @Basic
    private Date dateLimiteAcces;

    public Eleve() {

        setTypeUtilisateur(Utilisateur.TypeUtilisateur.ELEVE);
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
     * @return the commandeEnCours
     */
    public CommandeEleve getCommandeEnCours() {
        return commandeEnCours;
    }

    /**
     * @param commandeEnCours the commandeEnCours to set
     */
    public void setCommandeEnCours(CommandeEleve commandeEnCours) {
        this.commandeEnCours = commandeEnCours;
        this.commandeEnCours.setEleve(this);
        this.commandeEnCours.setStatut(CommandeEleve.StatutCommandeEleve.EN_COURS);
    }

    /**
     * @return the commandesEnAttenteValidationPayement
     */
    public List<CommandeEleve> getCommandesEnAttenteValidationPayement() {
        return commandesEnAttenteValidationPayement;
    }

    /**
     * @param commandesEnAttenteValidationPayement the commandesEnAttenteValidationPayement to set
     */
    public void setCommandesEnAttenteValidationPayement(List<CommandeEleve> commandesEnAttenteValidationPayement) {
        this.commandesEnAttenteValidationPayement = commandesEnAttenteValidationPayement;
    }

    /**
     * @return the commandesEnAttenteValidationEcole
     */
    public List<CommandeEleve> getCommandesEnAttenteValidationEcole() {
        return commandesEnAttenteValidationEcole;
    }

    /**
     * @param commandesEnAttenteValidationEcole the commandesEnAttenteValidationEcole to set
     */
    public void setCommandesEnAttenteValidationEcole(List<CommandeEleve> commandesEnAttenteValidationEcole) {
        this.commandesEnAttenteValidationEcole = commandesEnAttenteValidationEcole;
    }

    /**
     * @return the commandesEnLivraison
     */
    public List<CommandeEleve> getCommandesEnLivraison() {
        return commandesEnLivraison;
    }

    /**
     * @param commandesEnLivraison the commandesEnLivraison to set
     */
    public void setCommandesEnLivraison(List<CommandeEleve> commandesEnLivraison) {
        this.commandesEnLivraison = commandesEnLivraison;
    }

    /**
     * @return the commandesLivrees
     */
    public List<CommandeEleve> getCommandesLivrees() {
        return commandesLivrees;
    }

    /**
     * @param commandesLivrees the commandesLivrees to set
     */
    public void setCommandesLivrees(List<CommandeEleve> commandesLivrees) {
        this.commandesLivrees = commandesLivrees;
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
        return "Eleve [id=" + getId() + ", identifiant_chiffre=" + identifiantChiffre + ", nbCommandeEnAttenteValidPayement="
                        + commandesEnAttenteValidationPayement.size() + ", nbCommandeEnAttenteValidEcole=" + commandesEnAttenteValidationEcole.size()
                        + ", nbCommandeEnLivraison=" + commandesEnLivraison.size() + ", nbCommandeLivrees=" + commandesLivrees.size() + "]";
    }

    @Override
    public String toString() {
        return "Eleve [identifiantChiffre=" + identifiantChiffre + ", dateLimiteAcces=" + dateLimiteAcces + ", toString()=" + super.toString() + "]";
    }

}
