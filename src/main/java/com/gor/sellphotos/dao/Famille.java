package com.gor.sellphotos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Famille {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "famille")
    private List<Eleve> eleves;

    @ManyToOne
    private Ecole ecole;

    @OneToOne(mappedBy = "famille")
    private CommandeFamille commandeEnCours;

    @OneToMany(mappedBy = "famille")
    private List<CommandeFamille> commandesEnAttenteValidationPayement;

    @OneToMany(mappedBy = "famille")
    private List<CommandeFamille> commandesEnAttenteValidationEcole;

    @OneToMany(mappedBy = "famille")
    private List<CommandeFamille> commandesEnLivraison;

    @OneToMany(mappedBy = "famille")
    private List<CommandeFamille> commandesLivrees;

    public Famille() {
        eleves = new ArrayList<Eleve>();
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
     * @return the eleves
     */
    public List<Eleve> getEleves() {
        return eleves;
    }

    /**
     * @param eleves the eleves to set
     */
    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
    }

    public void addEleves(Eleve eleve) {
        eleves.add(eleve);
    }

    /**
     * @return the ecole
     */
    public Ecole getEcole() {
        return ecole;
    }

    /**
     * @param ecole the ecole to set
     */
    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

    /**
     * @return the commandeEnCours
     */
    public CommandeFamille getCommandeEnCours() {
        return commandeEnCours;
    }

    /**
     * @param commandeEnCours the commandeEnCours to set
     */
    public void setCommandeEnCours(CommandeFamille commandeEnCours) {
        this.commandeEnCours = commandeEnCours;
        this.commandeEnCours.setStatut(CommandeFamille.StatutCommandeFamille.EN_COURS);
        this.commandeEnCours.setFamille(this);
    }

    /**
     * @return the commandesEnAttenteValidationPayement
     */
    public List<CommandeFamille> getCommandesEnAttenteValidationPayement() {
        return commandesEnAttenteValidationPayement;
    }

    /**
     * @param commandesEnAttenteValidationPayement the commandesEnAttenteValidationPayement to set
     */
    public void setCommandesEnAttenteValidationPayement(List<CommandeFamille> commandesEnAttenteValidationPayement) {
        this.commandesEnAttenteValidationPayement = commandesEnAttenteValidationPayement;
    }

    /**
     * @return the commandesEnAttenteValidationEcole
     */
    public List<CommandeFamille> getCommandesEnAttenteValidationEcole() {
        return commandesEnAttenteValidationEcole;
    }

    /**
     * @param commandesEnAttenteValidationEcole the commandesEnAttenteValidationEcole to set
     */
    public void setCommandesEnAttenteValidationEcole(List<CommandeFamille> commandesEnAttenteValidationEcole) {
        this.commandesEnAttenteValidationEcole = commandesEnAttenteValidationEcole;
    }

    /**
     * @return the commandesEnLivraison
     */
    public List<CommandeFamille> getCommandesEnLivraison() {
        return commandesEnLivraison;
    }

    /**
     * @param commandesEnLivraison the commandesEnLivraison to set
     */
    public void setCommandesEnLivraison(List<CommandeFamille> commandesEnLivraison) {
        this.commandesEnLivraison = commandesEnLivraison;
    }

    /**
     * @return the commandesLivrees
     */
    public List<CommandeFamille> getCommandesLivrees() {
        return commandesLivrees;
    }

    /**
     * @param commandesLivrees the commandesLivrees to set
     */
    public void setCommandesLivrees(List<CommandeFamille> commandesLivrees) {
        this.commandesLivrees = commandesLivrees;
    }

    @Override
    public String toString() {
        return "Famille [id=" + getId() + ", nbEleves=" + eleves.size() + ", nbCommandeEnAttenteValidPayement="
                        + commandesEnAttenteValidationPayement.size() + ", nbCommandeEnAttenteValidEcole=" + commandesEnAttenteValidationEcole.size()
                        + ", nbCommandeEnLivraison=" + commandesEnLivraison.size() + ", nbCommandeLivrees=" + commandesLivrees.size() + "]";
    }

}
