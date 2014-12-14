package com.gor.sellphotos.dao;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Responsable extends Utilisateur {

    @ManyToOne
    private Ecole ecole;

    public Responsable() {
        super();
        setRole(Utilisateur.TypeUtilisateur.RESPONSABLE);
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
        return "Responsable [id=" + getId() + "]";
    }

}
