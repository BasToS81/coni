package com.gor.sellphotos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CommandeEleve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "commandeEleve", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommandeProduit> produitsCommandes;

    @Basic
    private double montantParentHT;

    @Basic
    private double montantEcoleHT;

    @Basic
    private double montantParentTTC;

    @Basic
    private double montantEcoleTTC;

    @ManyToOne
    private Eleve eleve;

    @ManyToOne
    private Famille famille;

    @ManyToOne
    private CommandeFamille commandeFamille;

    public CommandeEleve() {
        produitsCommandes = new ArrayList<CommandeProduit>();

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
     * @return the produitsCommandes
     */

    public List<CommandeProduit> getProduitsCommandes() {
        return produitsCommandes;
    }

    /**
     * @param produitsCommandes the produitsCommandes to set
     */
    public void setProduitsCommandes(List<CommandeProduit> produitsCommandes) {
        this.produitsCommandes.clear();
        this.produitsCommandes.addAll(produitsCommandes);
    }

    public void addProduitCommande(CommandeProduit produitCommande) {
        if (produitCommande != null) {
            this.produitsCommandes.add(produitCommande);
            produitCommande.setCommandeEleve(this);
            this.montantParentHT += produitCommande.getMontantParentHT();
            this.montantEcoleHT += produitCommande.getMontantEcoleHT();
            this.montantParentTTC += produitCommande.getMontantParentTTC();
            this.montantEcoleTTC += produitCommande.getMontantEcoleTTC();
        }
    }

    /**
     * @return the montantTTC
     */
    public double getMontantParentHT() {
        return montantParentHT;
    }

    /**
     * @param montantTTC the montantTTC to set
     */
    public void setMontantParentHT(double montantParentHT) {
        this.montantParentHT = montantParentHT;
    }

    /**
     * @return the montantHT
     */
    public double getMontantEcoleHT() {
        return montantEcoleHT;
    }

    /**
     * @param montantHT the montantHT to set
     */
    public void setMontantEcoleHT(double montantEcoleHT) {
        this.montantEcoleHT = montantEcoleHT;
    }

    /**
     * @return the eleve
     */
    public Eleve getEleve() {
        return eleve;
    }

    /**
     * @param eleve the eleve to set
     */
    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
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
        this.famille = famille;
    }

    /**
     * @return the commandeFamille
     */
    public CommandeFamille getCommandeFamille() {
        return commandeFamille;
    }

    /**
     * @param commandeFamille the commandeFamille to set
     */
    public void setCommandeFamille(CommandeFamille commandeFamille) {
        this.commandeFamille = commandeFamille;
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
        return "Utilisateur [id=" + id + ", montantParentHT=" + montantParentHT + ", montantEcoleHT=" + montantEcoleHT + "]";
    }

}
