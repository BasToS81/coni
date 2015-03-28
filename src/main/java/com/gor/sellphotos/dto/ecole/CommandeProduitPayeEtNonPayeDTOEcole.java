package com.gor.sellphotos.dto.ecole;

public class CommandeProduitPayeEtNonPayeDTOEcole extends CommandeProduitDTOEcole {

    private int quantiteNonPaye;

    private double montantParentHTNonPaye;

    private double montantEcoleHTNonPaye;

    private double montantParentTTCNonPaye;

    private double montantEcoleTTCNonPaye;

    public int getQuantiteNonPaye() {
        return quantiteNonPaye;
    }

    public void setQuantiteNonPaye(int quantiteNonPaye) {
        this.quantiteNonPaye = quantiteNonPaye;
    }

    public void addQuantiteNonPaye(int quantiteNonPaye) {
        this.quantiteNonPaye += quantiteNonPaye;
    }

    public double getMontantParentHTNonPaye() {
        return montantParentHTNonPaye;
    }

    public void setMontantParentHTNonPaye(double montantParentHTNonPaye) {
        this.montantParentHTNonPaye = montantParentHTNonPaye;
    }

    public void addMontantParentHTNonPaye(double montantParentHTNonPaye) {
        this.montantParentHTNonPaye += montantParentHTNonPaye;
    }

    public double getMontantEcoleHTNonPaye() {
        return montantEcoleHTNonPaye;
    }

    public void setMontantEcoleHTNonPaye(double montantEcoleHTNonPaye) {
        this.montantEcoleHTNonPaye = montantEcoleHTNonPaye;
    }

    public void addMontantEcoleHTNonPaye(double montantEcoleHTNonPaye) {
        this.montantEcoleHTNonPaye += montantEcoleHTNonPaye;
    }

    public double getMontantParentTTCNonPaye() {
        return montantParentTTCNonPaye;
    }

    public void setMontantParentTTCNonPaye(double montantParentTTCNonPaye) {
        this.montantParentTTCNonPaye = montantParentTTCNonPaye;
    }

    public void addMontantParentTTCNonPaye(double montantParentTTCNonPaye) {
        this.montantParentTTCNonPaye += montantParentTTCNonPaye;
    }

    public double getMontantEcoleTTCNonPaye() {
        return montantEcoleTTCNonPaye;
    }

    public void setMontantEcoleTTCNonPaye(double montantEcoleTTCNonPaye) {
        this.montantEcoleTTCNonPaye = montantEcoleTTCNonPaye;
    }

    public void addMontantEcoleTTCNonPaye(double montantEcoleTTCNonPaye) {
        this.montantEcoleTTCNonPaye += montantEcoleTTCNonPaye;
    }
}
