package com.gor.sellphotos.dao;

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

@Entity
public class CommandeEcole {
	
    public enum StatutCommandeEcole {
        EN_COURS,
        EN_LIVRAISON,
        LIVREE,
        ABANDONNEE
    }
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	private String identifiant;
	
	@ManyToOne
	private Ecole ecole;
	
	@Basic
	private Date dateCommande;

	@Enumerated(EnumType.STRING)
	private StatutCommandeEcole statut;
	
	@Basic
	private Date dateValidation;
	
	@OneToMany(mappedBy ="commandeEcole")
	private List<CommandeEleve> commandesEleves;
	
	@Basic
	private Date dateLivraison;

	
	
	
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
     * @return the statut
     */
    public StatutCommandeEcole getStatut() {
        return statut;
    }



    
    /**
     * @param statut the statut to set
     */
    public void setStatut(StatutCommandeEcole statut) {
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
     * @return the commandesEleves
     */
    public List<CommandeEleve> getCommandesEleves() {
        return commandesEleves;
    }



    
    /**
     * @param commandesEleves the commandesEleves to set
     */
    public void setCommandesEleves(List<CommandeEleve> commandesEleves) {
        this.commandesEleves = commandesEleves;
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
		return "Utilisateur [id=" + id + ", identifiant=" + identifiant + ", dateCommande=" + dateCommande + ", statut=" + statut + "]";
	}
	
}