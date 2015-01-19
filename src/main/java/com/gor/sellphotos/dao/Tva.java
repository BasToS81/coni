package com.gor.sellphotos.dao;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private double tva;

    @Basic
    private Date dateDebutValidite;

    @Basic
    private Date dateFinValidite;

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
     * @return the tva
     */
    public double getTva() {
        return tva;
    }

    /**
     * @param tva the tva to set
     */
    public void setTva(double tva) {
        this.tva = tva;
    }

    /**
     * @return the dateDebutValidite
     */
    public Date getDateDebutValidite() {
        return dateDebutValidite;
    }

    /**
     * @param dateDebutValidite the dateDebutValidite to set
     */
    public void setDateDebutValidite(Date dateDebutValidite) {
        this.dateDebutValidite = dateDebutValidite;
    }

    /**
     * @return the dateFinValidite
     */
    public Date getDateFinValidite() {
        return dateFinValidite;
    }

    /**
     * @param dateFinValidite the dateFinValidite to set
     */
    public void setDateFinValidite(Date dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

}
