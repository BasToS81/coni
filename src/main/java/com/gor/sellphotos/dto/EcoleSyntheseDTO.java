package com.gor.sellphotos.dto;

import java.util.List;

public class EcoleSyntheseDTO {

    private String identifiantChiffre;

    private List<ClasseSyntheseDTO> classes;

    private int etatActivation;

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
     * @return the classes
     */
    public List<ClasseSyntheseDTO> getClasses() {
        return classes;
    }

    /**
     * @param classes the classes to set
     */
    public void setClasses(List<ClasseSyntheseDTO> classes) {
        this.classes = classes;
    }

    /**
     * @return the etatActivation
     */
    public int getEtatActivation() {
        return etatActivation;
    }

    /**
     * @param etatActivation the etatActivation to set
     */
    public void setEtatActivation(int etatActivation) {
        this.etatActivation = etatActivation;
    }

}
