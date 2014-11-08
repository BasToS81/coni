package com.gor.sellphotos.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CommandeEleve {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Basic
    private String identifiant;
    
	@Basic
	private Date dateCommande;
	
	@Basic
	private String moyenPayement;
	
	@Basic
	private String statut;
	
	@Basic
	private Date dateValidation;
	
	@Basic
	private List<CommandeProduit> produitsCommandes;
	
	@Basic
	private CommandeEcole commandeEcole;
	
	@Basic
	private double montant;

	@Basic
	private Date dateLivraison;
	
	@Basic
	private Eleve eleve;
	
	
	

	
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
    public String getStatut() {
        return statut;
    }




    
    /**
     * @param statut the statut to set
     */
    public void setStatut(String statut) {
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
    public List<CommandeProduit> getProduitsCommandes() {
        return produitsCommandes;
    }




    
    /**
     * @param produitsCommandes the produitsCommandes to set
     */
    public void setProduitsCommandes(List<CommandeProduit> produitsCommandes) {
        this.produitsCommandes = produitsCommandes;
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




    @Override
	public String toString() {
		return "Utilisateur [id=" + id + ", dateCommande=" + dateCommande + ", statut=" + statut + ", montant=" + montant + "]";
	}
	
}
