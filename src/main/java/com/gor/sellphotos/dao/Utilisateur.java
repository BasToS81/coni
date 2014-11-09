package com.gor.sellphotos.dao;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Utilisateur {
	
    public enum TypeUtilisateur {
        ELEVE,
        RESPONSABLE,
        ADMINISTRATEUR
    }
    
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Basic
	private String identifiant;
	
	@Basic
	private String codeAcces;
	
	@Enumerated(EnumType.STRING)
	private TypeUtilisateur typeUtilisateur;

	   
    @Basic
    private String nom;


    
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
	 * @return the codeAcces
	 */
	public String getCodeAcces() {
		return codeAcces;
	}

	/**
	 * @param codeAcces the codeAcces to set
	 */
	public void setCodeAcces(String codeAcces) {
		this.codeAcces = codeAcces;
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
     * @return the typeUtilisateur
     */
    public TypeUtilisateur getTypeUtilisateur() {
        return typeUtilisateur;
    }

    
    /**
     * @param typeUtilisateur the typeUtilisateur to set
     */
    public void setTypeUtilisateur(TypeUtilisateur typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
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

    @Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", identifiant=" + identifiant + ", code d'acces=" + codeAcces + "]";
	}
	
}
