package com.gor.sellphotos.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dao.CommandeEleve;
import com.gor.sellphotos.dao.CommandeProduit;
import com.gor.sellphotos.dao.Eleve;
import com.gor.sellphotos.dao.ModeleEtTarif;
import com.gor.sellphotos.dao.Produit;
import com.gor.sellphotos.dto.CommandeEleveDTO;
import com.gor.sellphotos.dto.CommandeProduitDTO;
import com.gor.sellphotos.dto.ProduitDTO;
import com.gor.sellphotos.repository.CommandeEleveRepository;
import com.gor.sellphotos.repository.CommandeProduitRepository;
import com.gor.sellphotos.repository.EleveRepository;
import com.gor.sellphotos.repository.FamilleRepository;
import com.gor.sellphotos.repository.ModeleEtTarifRepository;

/**
 *
 */
@Controller
public class FamilleCommandesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FamilleCommandesController.class);

    @Autowired
    private FamilleRepository familleRepository;

    @Autowired
    private EleveRepository eleveRepository;

    @Autowired
    private CommandeEleveRepository commandeEleveRepository;

    @Autowired
    private ModeleEtTarifRepository modeleEtTarifRepository;

    @Autowired
    private CommandeProduitRepository commandeProduitRepository;

    @RequestMapping("/famille/commande/getList")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<CommandeEleveDTO> getCommandesFamille(@RequestParam("identifiant") String identifiantEleve) {
        LOGGER.debug("loading commandes famille {}", identifiantEleve);
        List<CommandeEleveDTO> commandesDTO = new ArrayList<CommandeEleveDTO>();

        List<CommandeEleve> commandes = commandeEleveRepository.findCmdDeTouteLaFamilleByIdEleve(identifiantEleve);

        for (CommandeEleve commandeEleve : commandes) {

            CommandeEleveDTO cmd = new CommandeEleveDTO();
            cmd.setDateCommande(commandeEleve.getDateCommande());
            cmd.setDateLivraison(commandeEleve.getDateLivraison());
            cmd.setDateValidation(commandeEleve.getDateValidation());
            cmd.setIdentifiant(commandeEleve.getIdentifiant());
            cmd.setIdentifiantEleve(commandeEleve.getEleve().getIdentifiant());
            cmd.setMontant(commandeEleve.getMontant());
            cmd.setMoyenPayement(commandeEleve.getMoyenPayement());
            cmd.setStatut(commandeEleve.getStatut().name());

            List<CommandeProduitDTO> cmdProduitsDTO = new ArrayList<CommandeProduitDTO>();

            for (CommandeProduit cmdProduit : commandeEleve.getProduitsCommandes()) {
                CommandeProduitDTO cmdPdtDTO = new CommandeProduitDTO();
                cmdPdtDTO.setMontant(cmdProduit.getMontant());
                cmdPdtDTO.setQuantite(cmdProduit.getQuantite());
                ProduitDTO pdtDTO = new ProduitDTO();
                pdtDTO.setDesignation(cmdProduit.getProduit().getDesignation());
                pdtDTO.setIdentifiant(cmdProduit.getProduit().getIdentifiant());
                pdtDTO.setOrdre(cmdProduit.getProduit().getOrdre());
                pdtDTO.setPrix_ecole_ttc(cmdProduit.getProduit().getPrix_ecole_ttc());
                pdtDTO.setPrix_parent_ttc(cmdProduit.getProduit().getPrix_parent_ttc());
                pdtDTO.setTypeProduit(cmdProduit.getProduit().getTypeProduit().name());

                cmdPdtDTO.setProduit(pdtDTO);

                cmdProduitsDTO.add(cmdPdtDTO);

            }

            cmd.setProduitsCommandes(cmdProduitsDTO);

            commandesDTO.add(cmd);
        }

        LOGGER.debug("commande famille {}", commandes);
        return commandesDTO;
    }

    @RequestMapping("/famille/commande/create")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CommandeEleveDTO createCommandeEleve(@RequestParam("identifiantEcole") String identifiantEcole,
                    @RequestParam("identifiantEleve") String identifiantEleve) {
        LOGGER.debug("creation de la commande {}", identifiantEcole);

        CommandeEleve cmd = new CommandeEleve();

        Eleve eleve = eleveRepository.findByIdentifiantChiffre(identifiantEleve);
        cmd.setEleve(eleve);
        cmd.setStatut(CommandeEleve.StatutCommandeEleve.EN_COURS);

        commandeEleveRepository.save(cmd);

        CommandeEleveDTO commandeEleve = new CommandeEleveDTO();

        commandeEleve.setIdentifiant(cmd.getIdentifiant());

        List<CommandeProduitDTO> commandesProduit = new ArrayList<CommandeProduitDTO>();

        ModeleEtTarif modele = modeleEtTarifRepository.findByNumeroEcole(identifiantEcole);

        List<Produit> listeDesProduitsDuModele = null;

        // Pour savoir si on prend les modeles et tarifs principal, on vérifie qu'il n'existe pas déjà une commande
        if (commandeEleveRepository.countByIdEleve(identifiantEleve) > 0) {
            listeDesProduitsDuModele = modele.getModeleEtTarifPrincipal();
        }
        else {
            listeDesProduitsDuModele = modele.getModeleEtTarifSupplementaire();
        }

        for (Produit produit : listeDesProduitsDuModele) {

            CommandeProduitDTO cmdProd = new CommandeProduitDTO();
            ProduitDTO prod = new ProduitDTO();

            prod.setIdentifiant(produit.getIdentifiant());
            prod.setDesignation(produit.getDesignation());
            prod.setOrdre(produit.getOrdre());
            prod.setPrix_ecole_ttc(produit.getPrix_ecole_ttc());
            prod.setPrix_parent_ttc(produit.getPrix_parent_ttc());

            cmdProd.setMontant(0);
            cmdProd.setProduit(prod);
            cmdProd.setQuantite(0);

            commandesProduit.add(cmdProd);
        }

        commandeEleve.setProduitsCommandes(commandesProduit);

        return commandeEleve;

    }

    @RequestMapping("/famille/commande/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CommandeEleveDTO getCommandeEleve(@RequestParam("identifiantCommande") String identifiantCommande,
                    @RequestParam("identifiantEcole") String identifiantEcole) {
        LOGGER.debug("loading commande {}", identifiantCommande);

        CommandeEleve cmd = commandeEleveRepository.findByIdentifiant(identifiantCommande);

        CommandeEleveDTO commandeEleve = new CommandeEleveDTO();

        commandeEleve.setIdentifiant(cmd.getIdentifiant());

        List<CommandeProduitDTO> commandesProduit = new ArrayList<CommandeProduitDTO>();

        ModeleEtTarif modele = modeleEtTarifRepository.findByNumeroEcole(identifiantEcole);

        List<Produit> listeDesProduitsDuModele = null;

        if (Produit.TypeProduit.PRINCIPALE.equals(cmd.getTypeCommande())) {
            listeDesProduitsDuModele = modele.getModeleEtTarifPrincipal();
        }
        else {
            listeDesProduitsDuModele = modele.getModeleEtTarifSupplementaire();
        }

        /* TODO : améliorer l'algorithme en copiant les produits commandés et en retirant ceux trouvés de la liste pour diminuer le nombre de recherche. */

        for (Produit produit : listeDesProduitsDuModele) {

            CommandeProduitDTO cmdProd = new CommandeProduitDTO();
            ProduitDTO prod = new ProduitDTO();

            prod.setIdentifiant(produit.getIdentifiant());
            prod.setDesignation(produit.getDesignation());
            prod.setOrdre(produit.getOrdre());
            prod.setPrix_ecole_ttc(produit.getPrix_ecole_ttc());
            prod.setPrix_parent_ttc(produit.getPrix_parent_ttc());
            cmdProd.setProduit(prod);

            cmdProd.setMontant(0);
            cmdProd.setQuantite(0);

            // Pour récupérer le commandeProduit, il faut le trouver dans la commande
            for (CommandeProduit prodOfCommande : cmd.getProduitsCommandes()) {

                // vérification si c'est le même produit
                if (prodOfCommande.getProduit().getId().equals(produit.getId())) {
                    // C'est le même produit on initialise avec les valeurs de la commande
                    cmdProd.setMontant(prodOfCommande.getMontant());
                    cmdProd.setQuantite(prodOfCommande.getQuantite());
                    break;
                }

            }

            commandesProduit.add(cmdProd);
        }

        commandeEleve.setProduitsCommandes(commandesProduit);

        return commandeEleve;
    }

    @RequestMapping("/famille/commande/save")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CommandeEleveDTO saveCommandeEleve(@RequestParam("identifiantCommande") String identifiantCommande,
                    @RequestParam("identifiantEcole") String identifiantEcole,
                    @RequestParam("produitsCommandes") List<CommandeProduitDTO> listeDesProduitsCommandes) {
        LOGGER.debug("saving commande {}", identifiantCommande);

        CommandeEleve cmd = commandeEleveRepository.findByIdentifiant(identifiantCommande);

        // Prise en compte des modifications des produits commandés
        // Suppression des anciens
        for (CommandeProduit cmdProd : cmd.getProduitsCommandes()) {
            commandeProduitRepository.delete(cmdProd);
        }
        cmd.setProduitsCommandes(new ArrayList<CommandeProduit>());

        CommandeEleveDTO commandeEleve = new CommandeEleveDTO();
        commandeEleve.setIdentifiant(cmd.getIdentifiant());

        List<CommandeProduitDTO> commandesProduit = new ArrayList<CommandeProduitDTO>();

        ModeleEtTarif modele = modeleEtTarifRepository.findByNumeroEcole(identifiantEcole);
        List<Produit> listeDesProduitsDuModele = null;

        if (Produit.TypeProduit.PRINCIPALE.equals(cmd.getTypeCommande())) {
            listeDesProduitsDuModele = modele.getModeleEtTarifPrincipal();
        }
        else {
            listeDesProduitsDuModele = modele.getModeleEtTarifSupplementaire();
        }

        /* TODO : améliorer l'algorithme en copiant les produits commandés et en retirant ceux trouvés de la liste pour diminuer le nombre de recherche. */

        for (Produit produit : listeDesProduitsDuModele) {

            CommandeProduitDTO cmdProd = new CommandeProduitDTO();
            ProduitDTO prod = new ProduitDTO();

            prod.setIdentifiant(produit.getIdentifiant());
            prod.setDesignation(produit.getDesignation());
            prod.setOrdre(produit.getOrdre());
            prod.setPrix_ecole_ttc(produit.getPrix_ecole_ttc());
            prod.setPrix_parent_ttc(produit.getPrix_parent_ttc());
            cmdProd.setProduit(prod);

            cmdProd.setMontant(0);
            cmdProd.setQuantite(0);

            // Sauvegarde des nouveaux produits + enrichissement en sortie avec les modeles existants
            for (CommandeProduitDTO cmdProdDTO : listeDesProduitsCommandes) {
                // vérification si c'est le même produit
                if (cmdProdDTO.getProduit().getId().equals(produit.getId())) {
                    CommandeProduit newCmdProd = new CommandeProduit();
                    newCmdProd.setMontant(cmdProdDTO.getMontant());
                    newCmdProd.setQuantite(cmdProdDTO.getQuantite());
                    newCmdProd.setProduit(produit);
                    commandeProduitRepository.save(newCmdProd);
                    cmd.addProduitCommande(newCmdProd);

                    // C'est le même produit on initialise avec les valeurs de la commande
                    cmdProd.setMontant(cmdProdDTO.getMontant());
                    cmdProd.setQuantite(cmdProdDTO.getQuantite());
                    break;
                }
            }

            commandesProduit.add(cmdProd);
        }

        commandeEleveRepository.save(cmd);

        commandeEleve.setProduitsCommandes(commandesProduit);

        return commandeEleve;

    }

    @RequestMapping("/famille/commande/del")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<CommandeEleveDTO> getDeleteCommandeEleve(@RequestParam("identifiant") String identifiantCommande) {
        LOGGER.debug("deleting commande {}", identifiantCommande);

        CommandeEleve commande = commandeEleveRepository.findByIdentifiant(identifiantCommande);

        String identifiantEleve = commande.getEleve().getIdentifiant();

        commandeEleveRepository.delete(commande);

        LOGGER.debug(" end deleting commande {}", commande);
        return getCommandesFamille(identifiantEleve);
    }
}
