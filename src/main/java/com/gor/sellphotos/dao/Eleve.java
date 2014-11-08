package com.gor.sellphotos.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Eleve {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	@OneToOne
	private Utilisateur utilisateur;
	
    @Basic
    private String nom;
	
	
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
     * @return the nom
     */
    public String getNom() {
        return nom;
    }


    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the identifiant_chiffre
     */
    public String getIdentifiantChiffre() {
        return identifiantChiffre;
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
        this.famille = famille;
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




    
    /**
     * @return the utilisateur
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }





    
    /**
     * @param utilisateur the utilisateur to set
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }





    @Override
	public String toString() {
		return "Eleve [id=" + id + ", nom=" + nom + ", identifiant_chiffre=" + identifiantChiffre + ", nbCommandeEnAttenteValidPayement=" + commandesEnAttenteValidationPayement.size()+ ", nbCommandeEnAttenteValidEcole=" + commandesEnAttenteValidationEcole.size() + ", nbCommandeEnLivraison=" + commandesEnLivraison.size()+ ", nbCommandeLivrees=" + commandesLivrees.size() + "]";
	}
	
}
