package com.gor.sellphotos.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Ecole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String identifiantChiffre;

    @Basic
    private String numeroEcole;

    @Basic
    private String saison;

    @Basic
    private String nomEtablissement;

    @Basic
    private String adresseEtablissement;

    @Basic
    private String codePostalEtablissement;

    @Basic
    private String villeEtablissement;

    @Basic
    private String nomResponsablePrincipal;

    @OneToMany(mappedBy = "ecole")
    private List<Responsable> responsables;

    @OneToMany(mappedBy = "ecole")
    private List<Classe> classes;

    @OneToOne
    private ModeleEtTarif modeleEtTarif;

    @OneToOne(mappedBy = "ecole")
    private CommandeEcole commandeEnCours;

    @OneToMany(mappedBy = "ecole")
    private List<CommandeEcole> commandeEnLivraison;

    @OneToMany(mappedBy = "ecole")
    private List<CommandeEcole> commandeLivrees;

    @Basic
    private Date dateLimiteDesCommandesEleves;

    @Basic
    private Date dateLimiteDesCommandesEcoles;

    @Basic
    private Date dateLimiteAcces;

    public Ecole() {
        responsables = new ArrayList<Responsable>();
        classes = new ArrayList<Classe>();
        commandeEnLivraison = new ArrayList<CommandeEcole>();
        commandeLivrees = new ArrayList<CommandeEcole>();
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
     * @return the identifiant_chiffre
     */
    public String getIdentifiantChiffre() {
        return identifiantChiffre;
    }

    /**
     * @param identifiant_chiffre the identifiant_chiffre to set
     */
    public void setIdentifiant_chiffre(String identifiantChiffre) {
        this.identifiantChiffre = identifiantChiffre;
    }

    /**
     * @return the numeroEcole
     */
    public String getNumeroEcole() {
        return numeroEcole;
    }

    /**
     * @param numeroEcole the numeroEcole to set
     */
    public void setNumeroEcole(String numeroEcole) {
        this.numeroEcole = numeroEcole;
    }

    /**
     * @return the saison
     */
    public String getSaison() {
        return saison;
    }

    /**
     * @param saison the saison to set
     */
    public void setSaison(String saison) {
        this.saison = saison;
    }

    /**
     * @return the nomEtablissement
     */
    public String getNomEtablissement() {
        return nomEtablissement;
    }

    /**
     * @param nomEtablissement the nomEtablissement to set
     */
    public void setNomEtablissement(String nomEtablissement) {
        this.nomEtablissement = nomEtablissement;
    }

    /**
     * @return the adresseEtablissement
     */
    public String getAdresseEtablissement() {
        return adresseEtablissement;
    }

    /**
     * @param adresseEtablissement the adresseEtablissement to set
     */
    public void setAdresseEtablissement(String adresseEtablissement) {
        this.adresseEtablissement = adresseEtablissement;
    }

    /**
     * @return the codePostalEtablissement
     */
    public String getCodePostalEtablissement() {
        return codePostalEtablissement;
    }

    /**
     * @param codePostalEtablissement the codePostalEtablissement to set
     */
    public void setCodePostalEtablissement(String codePostalEtablissement) {
        this.codePostalEtablissement = codePostalEtablissement;
    }

    /**
     * @return the villeEtablissement
     */
    public String getVilleEtablissement() {
        return villeEtablissement;
    }

    /**
     * @param villeEtablissement the villeEtablissement to set
     */
    public void setVilleEtablissement(String villeEtablissement) {
        this.villeEtablissement = villeEtablissement;
    }

    /**
     * @return the nomResponsablePrincipal
     */
    public String getNomResponsablePrincipal() {
        return nomResponsablePrincipal;
    }

    /**
     * @param nomResponsablePrincipal the nomResponsablePrincipal to set
     */
    public void setNomResponsablePrincipal(String nomResponsablePrincipal) {
        this.nomResponsablePrincipal = nomResponsablePrincipal;
    }

    /**
     * @return the responsables
     */
    public List<Responsable> getResponsables() {
        return responsables;
    }

    /**
     * @param responsables the responsables to set
     */
    public void setResponsables(List<Responsable> responsables) {
        this.responsables = responsables;
    }

    /**
     * @param responsables the responsables to set
     */
    public void addResponsables(Responsable responsable) {
        if (responsable != null) {
            // affectation de l'école
            responsable.setEcole(this);
            this.responsables.add(responsable);
        }
    }

    /**
     * @return the classes
     */
    public List<Classe> getClasses() {
        return classes;
    }

    /**
     * @param classes the classes to set
     */
    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    /**
     * @return the modeleEtTarif
     */
    public ModeleEtTarif getModeleEtTarif() {
        return modeleEtTarif;
    }

    /**
     * @param modeleEtTarif the modeleEtTarif to set
     */
    public void setModeleEtTarif(ModeleEtTarif modeleEtTarif) {
        this.modeleEtTarif = modeleEtTarif;
    }

    /**
     * @return the commandeEnCours
     */
    public CommandeEcole getCommandeEnCours() {
        return commandeEnCours;
    }

    /**
     * @param commandeEnCours the commandeEnCours to set
     */
    public void setCommandeEnCours(CommandeEcole commandeEnCours) {
        this.commandeEnCours = commandeEnCours;
    }

    /**
     * @return the commandeEnLivraison
     */
    public List<CommandeEcole> getCommandeEnLivraison() {
        return commandeEnLivraison;
    }

    /**
     * @param commandeEnLivraison the commandeEnLivraison to set
     */
    public void setCommandeEnLivraison(List<CommandeEcole> commandeEnLivraison) {
        this.commandeEnLivraison = commandeEnLivraison;
    }

    /**
     * @return the commandeLivrees
     */
    public List<CommandeEcole> getCommandeLivrees() {
        return commandeLivrees;
    }

    /**
     * @param commandeLivrees the commandeLivrees to set
     */
    public void setCommandeLivrees(List<CommandeEcole> commandeLivrees) {
        this.commandeLivrees = commandeLivrees;
    }

    /**
     * @return the dateLimiteDesCommandesEleves
     */
    public Date getDateLimiteDesCommandesEleves() {
        return dateLimiteDesCommandesEleves;
    }

    /**
     * @param dateLimiteDesCommandesEleves the dateLimiteDesCommandesEleves to set
     */
    public void setDateLimiteDesCommandesEleves(Date dateLimiteDesCommandesEleves) {
        this.dateLimiteDesCommandesEleves = dateLimiteDesCommandesEleves;
    }

    /**
     * @return the dateLimiteDesCommandesEcoles
     */
    public Date getDateLimiteDesCommandesEcoles() {
        return dateLimiteDesCommandesEcoles;
    }

    /**
     * @param dateLimiteDesCommandesEcoles the dateLimiteDesCommandesEcoles to set
     */
    public void setDateLimiteDesCommandesEcoles(Date dateLimiteDesCommandesEcoles) {
        this.dateLimiteDesCommandesEcoles = dateLimiteDesCommandesEcoles;
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
        return "Utilisateur [id=" + id + ", nom=" + nomEtablissement + ", numeroEcole=" + numeroEcole + ", identifiant_chiffre=" + identifiantChiffre
                        + ", ville=" + villeEtablissement + "]";
    }

}
