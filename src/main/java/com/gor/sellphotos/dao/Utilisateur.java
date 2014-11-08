package com.gor.sellphotos.dao;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Utilisateur {
	
    public enum TypeUtilisateur {
        ELEVE,
        RESPONSABLE,
        ADMINISTRATEUR
    }
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	private String identifiant;
	
	@Basic
	private String codeAcces;
	
	@Enumerated(EnumType.STRING)
	private TypeUtilisateur typeUtilisateur;



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

    @Override
	public String toString() {
		return "Utilisateur [id=" + id + ", identifiant=" + identifiant + ", code d'acces=" + codeAcces + "]";
	}
	
}
