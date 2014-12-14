package com.gor.sellphotos.dto;

public class UtilisateurDTO {

    private String identifiant;

    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [identifiant=" + identifiant + ", role=" + role + "]";
    }

}
