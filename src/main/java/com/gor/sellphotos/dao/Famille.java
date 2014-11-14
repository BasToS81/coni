package com.gor.sellphotos.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Famille {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "famille")
    private List<Eleve> eleves;

    @ManyToOne
    private Ecole ecole;

    public Famille() {
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

    public void addEleves(Eleve eleve) {
        eleves.add(eleve);
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

    @Override
    public String toString() {
        return "Famille [id=" + getId() + ", nbEleves=" + eleves.size() + "]";
    }

}
