package com.gor.sellphotos.security;

import java.util.List;

public class SecuritySessionData {

    private class SecuritySessionEleveData {

        private String identifiantEleve;

        private String identifiantChiffreEleve;

        private String identifiantChiffreClasse;

        public SecuritySessionEleveData(String identifiantEleve, String identifiantChiffreEleve, String identifiantChiffreClasse) {
            super();
            this.identifiantEleve = identifiantEleve;
            this.identifiantChiffreEleve = identifiantChiffreEleve;
            this.identifiantChiffreClasse = identifiantChiffreClasse;
        }

        /**
         * @return the identifiantEleve
         */
        public String getIdentifiantEleve() {
            return identifiantEleve;
        }

        /**
         * @param identifiantEleve the identifiantEleve to set
         */
        public void setIdentifiantEleve(String identifiantEleve) {
            this.identifiantEleve = identifiantEleve;
        }

        /**
         * @return the identifiantChiffreEleve
         */
        public String getIdentifiantChiffreEleve() {
            return identifiantChiffreEleve;
        }

        /**
         * @param identifiantChiffreEleve the identifiantChiffreEleve to set
         */
        public void setIdentifiantChiffreEleve(String identifiantChiffreEleve) {
            this.identifiantChiffreEleve = identifiantChiffreEleve;
        }

        /**
         * @return the identifiantChiffreClasse
         */
        public String getIdentifiantChiffreClasse() {
            return identifiantChiffreClasse;
        }

        /**
         * @param identifiantChiffreClasse the identifiantChiffreClasse to set
         */
        public void setIdentifiantChiffreClasse(String identifiantChiffreClasse) {
            this.identifiantChiffreClasse = identifiantChiffreClasse;
        }
    }

    private String identifiantUtilisateur;

    private Long identifiantEcole;

    private String identifiantChiffreEcole;

    private List<SecuritySessionEleveData> eleves;

    private List<String> identifiantCommandes;

    private Long identifiantCommmandeEnCours;

    /**
     * @return the identifiantUtilisateur
     */
    public String getIdentifiantUtilisateur() {
        return identifiantUtilisateur;
    }

    /**
     * @param identifiantUtilisateur the identifiantUtilisateur to set
     */
    public void setIdentifiantUtilisateur(String identifiantUtilisateur) {
        this.identifiantUtilisateur = identifiantUtilisateur;
    }

    /**
     * @return the identifiantCommandes
     */
    public List<String> getIdentifiantCommandes() {
        return identifiantCommandes;
    }

    /**
     * @param identifiantCommandes the identifiantCommandes to set
     */
    public void setIdentifiantCommandes(List<String> identifiantCommandes) {
        this.identifiantCommandes = identifiantCommandes;
    }

    /**
     * @return the identifiantEcole
     */
    public Long getIdentifiantEcole() {
        return identifiantEcole;
    }

    /**
     * @param identifiantEcole the identifiantEcole to set
     */
    public void setIdentifiantEcole(Long identifiantEcole) {
        this.identifiantEcole = identifiantEcole;
    }

    /**
     * @return the identifiantChiffreEcole
     */
    public String getIdentifiantChiffreEcole() {
        return identifiantChiffreEcole;
    }

    /**
     * @param identifiantChiffreEcole the identifiantChiffreEcole to set
     */
    public void setIdentifiantChiffreEcole(String identifiantChiffreEcole) {
        this.identifiantChiffreEcole = identifiantChiffreEcole;
    }

    public void addEleve(String id, String idChiffreEleve, String idChiffreClasse) {
        eleves.add(new SecuritySessionEleveData(id, idChiffreEleve, idChiffreClasse));
    }

    /**
     * @return the identifiantCommmandeEnCours
     */
    public Long getIdentifiantCommmandeEnCours() {
        return identifiantCommmandeEnCours;
    }

    /**
     * @param identifiantCommmandeEnCours the identifiantCommmandeEnCours to set
     */
    public void setIdentifiantCommmandeEnCours(Long identifiantCommmandeEnCours) {
        this.identifiantCommmandeEnCours = identifiantCommmandeEnCours;
    }

}
