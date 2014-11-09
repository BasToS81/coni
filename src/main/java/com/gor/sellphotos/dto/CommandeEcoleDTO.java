package com.gor.sellphotos.dto;

import java.util.Date;
import java.util.List;


public class CommandeEcoleDTO {
	
      
	private Long id;

	private String identifiant;
	
	private EcoleDTO ecole;
	
	private Date dateCommande;

	private String statut;
	
	private Date dateValidation;
	
	private List<CommandeEleveDTO> commandesEleves;
	
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
    public EcoleDTO getEcole() {
        return ecole;
    }

    
    /**
     * @param ecole the ecole to set
     */
    public void setEcole(EcoleDTO ecole) {
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
     * @return the commandesEleves
     */
    public List<CommandeEleveDTO> getCommandesEleves() {
        return commandesEleves;
    }

    
    /**
     * @param commandesEleves the commandesEleves to set
     */
    public void setCommandesEleves(List<CommandeEleveDTO> commandesEleves) {
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

	
	
}
