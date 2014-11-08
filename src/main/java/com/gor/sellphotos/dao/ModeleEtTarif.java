package com.gor.sellphotos.dao;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ModeleEtTarif {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy="modeleEtTarif")
	private Ecole ecole;
	
	@OneToMany
	private List<Produit> modeleEtTarifPrincipal;
	
	@OneToMany
	private List<Produit> modeleEtTarifSupplementaire;


	

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
	 * @return the modeleEtTarifPrincipal
	 */
	public List<Produit> getModeleEtTarifPrincipal() {
		return modeleEtTarifPrincipal;
	}




	/**
	 * @param modeleEtTarifPrincipal the modeleEtTarifPrincipal to set
	 */
	public void setModeleEtTarifPrincipal(List<Produit> modeleEtTarifPrincipal) {
		this.modeleEtTarifPrincipal = modeleEtTarifPrincipal;
	}




	/**
	 * @return the modeleEtTarifSupplementaire
	 */
	public List<Produit> getModeleEtTarifSupplementaire() {
		return modeleEtTarifSupplementaire;
	}




	/**
	 * @param modeleEtTarifSupplementaire the modeleEtTarifSupplementaire to set
	 */
	public void setModeleEtTarifSupplementaire(
			List<Produit> modeleEtTarifSupplementaire) {
		this.modeleEtTarifSupplementaire = modeleEtTarifSupplementaire;
	}




	@Override
	public String toString() {
		return "ModeleEtTarif [id=" + id + ", nbModeleEtTarifPrincipal=" + modeleEtTarifPrincipal.size() + ", nbModeleEtTarifSupplementaire=" + modeleEtTarifSupplementaire.size() + "]";
	}
	
}
