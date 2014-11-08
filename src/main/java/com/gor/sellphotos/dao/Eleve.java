package com.gor.sellphotos.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Eleve {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	private String identifiant_chiffre;
	
	@Basic
	private Famille famille;
	
	@Basic
	private Classe classe;
	
	@Basic
	private CommandeEleve commandeEnCours;
	
	@Basic
	private List<CommandeEleve> commandesEnAttenteValidationPayement;
	
	@Basic
	private List<CommandeEleve> commandesEnAttenteValidationEcole;
	
	@Basic
	private List<CommandeEleve> commandesEnLivraison;
	
	@Basic
	private List<CommandeEleve> commandesLivrees;

	@Basic
	private Date dateLimiteAcces;

	
	
	
	
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
    public String getIdentifiant_chiffre() {
        return identifiant_chiffre;
    }




    
    /**
     * @param identifiant_chiffre the identifiant_chiffre to set
     */
    public void setIdentifiant_chiffre(String identifiant_chiffre) {
        this.identifiant_chiffre = identifiant_chiffre;
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




    @Override
	public String toString() {
		return "Eleve [id=" + id + ", identifiant_chiffre=" + identifiant_chiffre + ", nbCommandeEnAttenteValidPayement=" + commandesEnAttenteValidationPayement.size()+ ", nbCommandeEnAttenteValidEcole=" + commandesEnAttenteValidationEcole.size() + ", nbCommandeEnLivraison=" + commandesEnLivraison.size()+ ", nbCommandeLivrees=" + commandesLivrees.size() + "]";
	}
	
}
