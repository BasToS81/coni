package com.gor.sellphotos.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gor.sellphotos.dao.CommandeFamille.StatutCommandeFamille;

@Entity
public class Famille {

    private static final Logger LOGGER = LoggerFactory.getLogger(Famille.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifiantsFraterie;

    @OneToMany(mappedBy = "famille")
    private List<Eleve> eleves;

    @ManyToOne
    private Ecole ecole;

    @OneToMany(mappedBy = "famille")
    private List<CommandeFamille> commandes;

    public Famille() {
        eleves = new ArrayList<Eleve>();
        commandes = new ArrayList<CommandeFamille>();
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

    /**
     * @return the commandes
     */
    public List<CommandeFamille> getCommandes() {
        return commandes;
    }

    /**
     * @param commandes the commandes to set
     */
    public void setCommandes(List<CommandeFamille> commandes) {
        this.commandes = commandes;
    }

    /**
     * @param commandes the commandes to add
     */
    public void addCommande(CommandeFamille commande) {
        commande.setFamille(this);
        this.commandes.add(commande);
    }

    /**
     * @return the commandeEnCours
     */
    public CommandeFamille getCommandeEnCours() {
        LOGGER.debug("Nb de commandes : {}", commandes.size());
        List<CommandeFamille> listCommande = commandes.stream().filter(p -> p.getStatut().equals(StatutCommandeFamille.EN_COURS)).collect(Collectors.toList());
        LOGGER.debug("Nb de commandes en cours : {}", listCommande.size());
        if (listCommande.size() == 1) {
            return listCommande.get(0);
        }
        return null;
    }

    /**
     * @return the commandesEnAttenteValidationPayement
     */
    public List<CommandeFamille> getCommandesEnAttenteValidationPayement() {
        return commandes.stream().filter(p -> p.getStatut() == StatutCommandeFamille.EN_ATTENTE_PAYEMENT).collect(Collectors.toList());
    }

    /**
     * @return the commandesEnAttenteValidationEcole
     */
    public List<CommandeFamille> getCommandesEnAttenteValidationEcole() {
        return commandes.stream().filter(p -> p.getStatut() == StatutCommandeFamille.EN_ATTENTE_VALID_RESPONSABLE).collect(Collectors.toList());
    }

    /**
     * @return the commandesEnLivraison
     */
    public List<CommandeFamille> getCommandesEnLivraison() {
        return commandes.stream().filter(p -> p.getStatut() == StatutCommandeFamille.EN_LIVRAISON).collect(Collectors.toList());
    }

    /**
     * @return the commandesLivrees
     */
    public List<CommandeFamille> getCommandesLivrees() {
        return commandes.stream().filter(p -> p.getStatut() == StatutCommandeFamille.LIVREE).collect(Collectors.toList());
    }

    public String getIdentifiantsFraterie() {
        return identifiantsFraterie;
    }

    public void setIdentifiantsFraterie(String identifiantsFraterie) {
        this.identifiantsFraterie = identifiantsFraterie;
    }

    @Override
    public String toString() {
        return "Famille [id=" + getId() + ", nbEleves=" + eleves + ", nbCommandes=" + commandes.size() + "]";
    }

}
