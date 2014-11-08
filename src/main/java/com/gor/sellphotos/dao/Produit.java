package com.gor.sellphotos.dao;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	private String identifiant;
	
	@Basic
	private String designation;
	
	@Basic
	private double prix_parent_ttc;
	
	@Basic
	private double prix_ecole_ttc;
	
	@Basic
	private int ordre;
	
	@Basic
	private String typeProduit;

	

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
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}



	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}



	/**
	 * @return the prix_parent_ttc
	 */
	public double getPrix_parent_ttc() {
		return prix_parent_ttc;
	}



	/**
	 * @param prix_parent_ttc the prix_parent_ttc to set
	 */
	public void setPrix_parent_ttc(double prix_parent_ttc) {
		this.prix_parent_ttc = prix_parent_ttc;
	}



	/**
	 * @return the prix_ecole_ttc
	 */
	public double getPrix_ecole_ttc() {
		return prix_ecole_ttc;
	}



	/**
	 * @param prix_ecole_ttc the prix_ecole_ttc to set
	 */
	public void setPrix_ecole_ttc(double prix_ecole_ttc) {
		this.prix_ecole_ttc = prix_ecole_ttc;
	}



	/**
	 * @return the order
	 */
	public int getOrdre() {
		return ordre;
	}



	/**
	 * @param order the order to set
	 */
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}



	/**
	 * @return the typeProduit
	 */
	public String getTypeProduit() {
		return typeProduit;
	}



	/**
	 * @param typeProduit the typeProduit to set
	 */
	public void setTypeProduit(String typeProduit) {
		this.typeProduit = typeProduit;
	}



	@Override
	public String toString() {
		return "Produit [id=" + id + ", designation=" + designation + ", identifiant=" + identifiant + ", typeProduit=" + typeProduit + ", ordre=" + ordre + "]";
	}
	
}
