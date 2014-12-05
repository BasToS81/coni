package com.gor.sellphotos.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.gor.sellphotos.utils.DateUtils;

@Entity
public class CommandeFamille {

    public enum StatutCommandeFamille {
        EN_COURS,
        EN_ATTENTE_PAYEMENT,
        EN_ATTENTE_VALID_RESPONSABLE,
        EN_LIVRAISON,
        LIVREE,
        ABANDONNEE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String identifiant;

    @Basic
    private Date dateCommande;

    @Basic
    private String moyenPayement;

    @Enumerated(EnumType.STRING)
    private StatutCommandeFamille statut;

    @Basic
    private Date dateValidation;

    @OneToMany(mappedBy = "commandeFamille")
    private List<CommandeEleve> commandesEleve;

    @ManyToOne
    private CommandeEcole commandeEcole;

    @ManyToOne
    private Famille famille;

    @Basic
    private double montant;

    @Basic
    private Date dateLivraison;

    public CommandeFamille() {
        commandesEleve = new ArrayList<CommandeEleve>();
        dateCommande = DateUtils.getCurrentDate();

    }

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
     * @return the dateCommande
     */
    public Date getDateCommande() {
        return dateCommande;
    }

    /**
     * @param dateCommande the dateCommande to set
     */
    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    /**
     * @return the moyenPayement
     */
    public String getMoyenPayement() {
        return moyenPayement;
    }

    /**
     * @param moyenPayement the moyenPayement to set
     */
    public void setMoyenPayement(String moyenPayement) {
        this.moyenPayement = moyenPayement;
    }

    /**
     * @return the statut
     */
    public StatutCommandeFamille getStatut() {
        return statut;
    }

    /**
     * @param statut the statut to set
     */
    public void setStatut(StatutCommandeFamille statut) {
        this.statut = statut;
    }

    /**
     * @return the dateValidation
     */
    public Date getDateValidation() {
        return dateValidation;
    }

    /**
     * @param dateValidation the dateValidation to set
     */
    public void setDateValidation(Date dateValidation) {
        this.dateValidation = dateValidation;
    }

    /**
     * @return the produitsCommandes
     */

    public List<CommandeEleve> getCommandesEleve() {
        return commandesEleve;
    }

    /**
     * @param produitsCommandes the produitsCommandes to set
     */
    public void setCommandesEleve(List<CommandeEleve> commandesEleve) {
        this.commandesEleve = commandesEleve;
    }

    public void addCommandeEleve(CommandeEleve commandeEleve) {
        this.commandesEleve.add(commandeEleve);
    }

    /**
     * @return the commandeEcole
     */
    public CommandeEcole getCommandeEcole() {
        return commandeEcole;
    }

    /**
     * @param commandeEcole the commandeEcole to set
     */
    public void setCommandeEcole(CommandeEcole commandeEcole) {
        this.commandeEcole = commandeEcole;
    }

    /**
     * @return the famile
     */
    public Famille getFamille() {
        return famille;
    }

    /**
     * @param famile the famile to set
     */
    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    /**
     * @return the montant
     */
    public double getMontant() {
        return montant;
    }

    /**
     * @param montant the montant to set
     */
    public void setMontant(double montant) {
        this.montant = montant;
    }

    /**
     * @return the dateLivraison
     */
    public Date getDateLivraison() {
        return dateLivraison;
    }

    /**
     * @param dateLivraison the dateLivraison to set
     */
    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", dateCommande=" + dateCommande + ", statut=" + statut + ", montant=" + montant + "]";
    }

}
