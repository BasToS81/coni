package com.gor.sellphotos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String identifiantChiffre;

    @Basic
    private String nom;

    @ManyToOne
    private Ecole ecole;

    @OneToMany(mappedBy = "classe")
    private List<Eleve> eleves;

    public Classe() {
        eleves = new ArrayList<Eleve>();
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
     * @return the identifiant_chiffre
     */
    public String getIdentifiantChiffre() {
        return identifiantChiffre;
    }

    /**
     * @param identifiant_chiffre the identifiant_chiffre to set
     */
    public void setIdentifiantChiffre(String identifiantChiffre) {
        this.identifiantChiffre = identifiantChiffre;
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
     * @return the eleves
     */
    public List<Eleve> getEleves() {
        return eleves;
    }

    /**
     * @param eleves the eleves to set
     */
    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
    }

    /**
     * @param eleves the eleves to add
     */
    public void addEleve(Eleve eleve) {
        eleves.add(eleve);
    }

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", nom=" + nom + ", identifiant_chiffre=" + identifiantChiffre + ", nbEleves=" + eleves.size() + "]";
    }

}
