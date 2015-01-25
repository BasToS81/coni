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
    private Long identifiant;

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
    private double montantParentHT;

    @Basic
    private double montantEcoleHT;

    @Basic
    private double montantParentTTC;

    @Basic
    private double montantEcoleTTC;

    @Basic
    private Date dateLivraison;

    public CommandeFamille() {
        commandesEleve = new ArrayList<CommandeEleve>();
        dateCommande = DateUtils.getCurrentDate();
        statut = StatutCommandeFamille.EN_COURS;
    }

    /**
     * @return the identifiant
     */
    public Long getIdentifiant() {
        return identifiant;
    }

    /**
     * @param identifiant the identifiant to set
     */
    public void setIdentifiant(Long identifiant) {
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
        this.montantParentHT += commandeEleve.getMontantParentHT();
        this.montantEcoleHT += commandeEleve.getMontantEcoleHT();
        this.montantParentTTC += commandeEleve.getMontantParentTTC();
        this.montantEcoleTTC += commandeEleve.getMontantEcoleTTC();
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
     * @return the montantHT
     */
    public double getMontantParentHT() {
        return montantParentHT;
    }

    /**
     * @param montantHT the montantHT to set
     */
    public void setMontantParentHT(double montantParentHT) {
        this.montantParentHT = montantParentHT;
    }

    /**
     * @return the montantTTC
     */
    public double getMontantEcoleHT() {
        return montantEcoleHT;
    }

    /**
     * @param montantTTC the montantTTC to set
     */
    public void setMontantEcoleHT(double montantEcoleHT) {
        this.montantEcoleHT = montantEcoleHT;
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

    /**
     * @return the montantParentTTC
     */
    public double getMontantParentTTC() {
        return montantParentTTC;
    }

    /**
     * @param montantParentTTC the montantParentTTC to set
     */
    public void setMontantParentTTC(double montantParentTTC) {
        this.montantParentTTC = montantParentTTC;
    }

    /**
     * @return the montantEcoleTTC
     */
    public double getMontantEcoleTTC() {
        return montantEcoleTTC;
    }

    /**
     * @param montantEcoleTTC the montantEcoleTTC to set
     */
    public void setMontantEcoleTTC(double montantEcoleTTC) {
        this.montantEcoleTTC = montantEcoleTTC;
    }

    @Override
    public String toString() {
        return "Utilisateur [identifiant=" + identifiant + ", dateCommande=" + dateCommande + ", statut=" + statut + ", montantParentHT=" + montantParentHT
                        + ", montantEcoleHT=" + montantEcoleHT + "]";
    }

}
