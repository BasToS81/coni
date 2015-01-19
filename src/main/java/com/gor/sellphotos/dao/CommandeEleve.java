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
        this.produitsCommandes = produitsCommandes;
    }

    public void addProduitCommande(CommandeProduit produitCommande) {
        this.produitsCommandes.add(produitCommande);
        this.montantParentHT += produitCommande.getMontantParentHT();
        this.montantEcoleHT += produitCommande.getMontantEcoleHT();
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

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", montantParentHT=" + montantParentHT + ", montantEcoleHT=" + montantEcoleHT + "]";
    }

}
