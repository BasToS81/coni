package com.gor.sellphotos.dto;

import java.util.Date;


public class EcoleDTO {
	
	
	private Long id;

	private String identifiantChiffre;
		
	private String numeroEcole;
	
	private String saison;
	
	private String nomEtablissement;
	
	private String adresseEtablissement;
	
	private String codePostalEtablissement;
	
	private String villeEtablissement;

	private String nomResponsablePrincipal;
	
	private Date dateLimiteDesCommandesEleves;
	
	private Date dateLimiteDesCommandesEcoles;

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
     * @return the identifiantChiffre
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
	
	
	
}
