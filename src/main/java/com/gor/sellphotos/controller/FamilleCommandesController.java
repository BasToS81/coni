package com.gor.sellphotos.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dao.CommandeEleve;
import com.gor.sellphotos.dao.CommandeFamille;
import com.gor.sellphotos.dao.CommandeProduit;
import com.gor.sellphotos.dao.Eleve;
import com.gor.sellphotos.dao.Famille;
import com.gor.sellphotos.dao.ModeleEtTarif;
import com.gor.sellphotos.dao.Produit;
import com.gor.sellphotos.dto.CommandeEleveDTO;
import com.gor.sellphotos.dto.CommandeFamilleDTO;
import com.gor.sellphotos.dto.CommandeFamilleSyntheseDTO;
import com.gor.sellphotos.dto.CommandeProduitDTO;
import com.gor.sellphotos.dto.ProduitDTO;
import com.gor.sellphotos.repository.CommandeEleveRepository;
import com.gor.sellphotos.repository.CommandeFamilleRepository;
import com.gor.sellphotos.repository.CommandeProduitRepository;
import com.gor.sellphotos.repository.EleveRepository;
import com.gor.sellphotos.repository.FamilleRepository;
import com.gor.sellphotos.repository.ModeleEtTarifRepository;
import com.gor.sellphotos.repository.ProduitRepository;
import com.gor.sellphotos.utils.DateUtils;

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
    private CommandeFamilleRepository commandeFamilleRepository;

    @Autowired
    private CommandeEleveRepository commandeEleveRepository;

    @Autowired
    private ModeleEtTarifRepository modeleEtTarifRepository;

    @Autowired
    private CommandeProduitRepository commandeProduitRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @RequestMapping("/famille/commande/getList")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<CommandeFamilleSyntheseDTO> getCommandesFamille(@RequestParam("identifiant") String identifiantEleve) {
        LOGGER.debug("loading commandes famille {}", identifiantEleve);
        List<CommandeFamilleSyntheseDTO> commandesDTO = new ArrayList<CommandeFamilleSyntheseDTO>();

        List<CommandeFamille> commandes = commandeFamilleRepository.findCmdDeTouteLaFamilleByIdEleve(identifiantEleve);

        for (CommandeFamille commandeFamille : commandes) {

            CommandeFamilleSyntheseDTO cmd = new CommandeFamilleSyntheseDTO();
            cmd.setDateCommande(commandeFamille.getDateCommande());
            cmd.setDateLivraison(commandeFamille.getDateLivraison());
            cmd.setDateValidation(commandeFamille.getDateValidation());
            cmd.setIdentifiant(commandeFamille.getIdentifiant());
            cmd.setMontant(commandeFamille.getMontant());
            cmd.setMoyenPayement(commandeFamille.getMoyenPayement());
            cmd.setStatut(commandeFamille.getStatut().name());

            commandesDTO.add(cmd);
        }

        LOGGER.debug("commande famille {}", commandes);
        return commandesDTO;
    }

    @RequestMapping("/famille/commande/create")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTO createCommandeFamille(@RequestParam("identifiantEcole") String identifiantEcole,
                    @RequestParam("identifiantEleve") String identifiantEleve) {
        LOGGER.debug("creation de la commande {}", identifiantEcole);

        CommandeFamilleDTO commandeFamille = new CommandeFamilleDTO();

        // Création de la Commande Famille
        CommandeFamille cmd = new CommandeFamille();
        Famille famille = familleRepository.findByIdentifiantUtilisateur(identifiantEleve);
        famille.setCommandeEnCours(cmd);
        cmd.setIdentifiant(DateUtils.formatDateTime(DateUtils.getCurrentDate()));

        commandeFamilleRepository.save(cmd);
        familleRepository.save(famille);

        commandeFamille.setIdentifiant(cmd.getIdentifiant());

        // Préaparation des commandes possibles par élèves

        ModeleEtTarif modele = modeleEtTarifRepository.findByNumeroEcole(identifiantEcole);

        List<Produit> listeDesProduitsDuModele = null;

        for (Eleve eleve : famille.getEleves()) {
            CommandeEleveDTO commandeEleveDTO = new CommandeEleveDTO();

            List<CommandeProduitDTO> commandesProduit = new ArrayList<CommandeProduitDTO>();

            // Pour savoir si on prend les modeles et tarifs principal, on vérifie qu'il n'existe pas déjà une commande
            if (commandeEleveRepository.countByIdEleve(eleve.getIdentifiant()) == 0) {
                listeDesProduitsDuModele = produitRepository.findPrincipalByModele(modele.getId());
                commandeEleveDTO.setTypeCommande(Produit.TypeProduit.PRINCIPALE.name());
            }
            else {
                listeDesProduitsDuModele = produitRepository.findSupplementaireByModele(modele.getId());
                commandeEleveDTO.setTypeCommande(Produit.TypeProduit.SUPPLEMENTAIRE.name());
            }

            commandeEleveDTO.setIdentifiantEleve(eleve.getIdentifiant());

            for (Produit produit : listeDesProduitsDuModele) {
                CommandeProduitDTO cmdProd = new CommandeProduitDTO();
                ProduitDTO prod = new ProduitDTO();

                LOGGER.debug("Produit dans la nouvelle commande : " + produit.toString());
                prod.setId(produit.getId());
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

            commandeEleveDTO.setProduitsCommandes(commandesProduit);
            commandeFamille.addCommandeEleve(commandeEleveDTO);
        }

        return commandeFamille;

    }

    @RequestMapping("/famille/commande/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTO getCommandeFamille(@RequestParam("identifiantCommande") String identifiantCommande,
                    @RequestParam("identifiantEcole") String identifiantEcole) {
        LOGGER.debug("loading commande {}", identifiantCommande);

        CommandeFamilleDTO commandeFamilleDTO = new CommandeFamilleDTO();

        /* TODO : S'assurer que c'est bien la commande de l'élève */
        CommandeFamille cmdFamille = commandeFamilleRepository.findByIdentifiant(identifiantCommande);

        commandeFamilleDTO.setDateCommande(cmdFamille.getDateCommande());
        commandeFamilleDTO.setDateLivraison(cmdFamille.getDateLivraison());
        commandeFamilleDTO.setDateValidation(cmdFamille.getDateValidation());
        commandeFamilleDTO.setId(cmdFamille.getId());
        commandeFamilleDTO.setIdentifiant(cmdFamille.getIdentifiant());
        commandeFamilleDTO.setMontant(cmdFamille.getMontant());
        commandeFamilleDTO.setMoyenPayement(cmdFamille.getMoyenPayement());
        commandeFamilleDTO.setStatut(cmdFamille.getStatut().name());

        for (CommandeEleve cmd : cmdFamille.getCommandesEleve()) {
            CommandeEleveDTO commandeEleveDTO = new CommandeEleveDTO();

            commandeEleveDTO.setId(cmd.getId());
            commandeEleveDTO.setIdentifiantEleve(cmd.getEleve().getIdentifiant());
            commandeEleveDTO.setMontant(cmd.getMontant());

            List<CommandeProduitDTO> commandesProduit = new ArrayList<CommandeProduitDTO>();

            ModeleEtTarif modele = modeleEtTarifRepository.findByNumeroEcole(identifiantEcole);

            List<Produit> listeDesProduitsDuModele = null;

            if (Produit.TypeProduit.PRINCIPALE.equals(cmd.getTypeCommande())) {
                listeDesProduitsDuModele = produitRepository.findPrincipalByModele(modele.getId());
            }
            else {
                listeDesProduitsDuModele = produitRepository.findSupplementaireByModele(modele.getId());
            }

            /* TODO : améliorer l'algorithme en copiant les produits commandés et en retirant ceux trouvés de la liste pour diminuer le nombre de recherche. */

            for (Produit produit : listeDesProduitsDuModele) {

                CommandeProduitDTO cmdProd = new CommandeProduitDTO();
                ProduitDTO prod = new ProduitDTO();

                prod.setId(produit.getId());
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
                        commandesProduit.add(cmdProd);
                        break;
                    }

                }

            }

            commandeEleveDTO.setProduitsCommandes(commandesProduit);
            commandeFamilleDTO.addCommandeEleve(commandeEleveDTO);
        }

        return commandeFamilleDTO;
    }

    @RequestMapping("/famille/commande/save")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTO saveCommandeFamille(@RequestParam("identifiantCommande") String identifiantCommande,
                    @RequestParam("identifiantEcole") String identifiantEcole,
                    @RequestParam("commandesEleve") List<CommandeEleveDTO> commandesEleve) {
        LOGGER.debug("saving commande {}", identifiantCommande);

        CommandeFamille cmdFamille = commandeFamilleRepository.findByIdentifiant(identifiantCommande);

        // Prise en compte des modifications des produits commandés
        // Suppression des anciens
        for (CommandeEleve cmd : cmdFamille.getCommandesEleve()) {
            for (CommandeProduit cmdProd : cmd.getProduitsCommandes()) {
                commandeProduitRepository.delete(cmdProd);
            }
            commandeEleveRepository.delete(cmd);
        }

        cmdFamille.setCommandesEleve(new ArrayList<CommandeEleve>());

        for (CommandeEleveDTO cmdDTO : commandesEleve) {

            // création des nouvelles données
            CommandeEleve cmd = new CommandeEleve();
            cmd.setEleve(eleveRepository.findByIdentifiant(cmdDTO.getIdentifiantEleve()));
            cmd.setFamille(cmdFamille.getFamille());
            cmd.setCommandeFamille(cmdFamille);

            ModeleEtTarif modele = modeleEtTarifRepository.findByNumeroEcole(identifiantEcole);
            List<Produit> listeDesProduitsDuModele = null;

            if (Produit.TypeProduit.PRINCIPALE.name().compareTo(cmdDTO.getTypeCommande()) == 0) {
                listeDesProduitsDuModele = produitRepository.findPrincipalByModele(modele.getId());
                cmd.setTypeCommande(Produit.TypeProduit.PRINCIPALE);
            }
            else {
                listeDesProduitsDuModele = produitRepository.findSupplementaireByModele(modele.getId());
                cmd.setTypeCommande(Produit.TypeProduit.SUPPLEMENTAIRE);
            }

            /* TODO : améliorer l'algorithme en copiant les produits commandés et en retirant ceux trouvés de la liste pour diminuer le nombre de recherche. */

            for (Produit produit : listeDesProduitsDuModele) {

                // Sauvegarde des nouveaux produits + enrichissement en sortie avec les modeles existants
                for (CommandeProduitDTO cmdProdDTO : cmdDTO.getProduitsCommandes()) {
                    // vérification si c'est le même produit
                    if (cmdProdDTO.getProduit().getId().equals(produit.getId())) {
                        if (cmdProdDTO.getQuantite() != 0) {

                            CommandeProduit newCmdProd = new CommandeProduit();
                            newCmdProd.setMontant(cmdProdDTO.getMontant());
                            newCmdProd.setQuantite(cmdProdDTO.getQuantite());
                            newCmdProd.setProduit(produit);
                            newCmdProd.setCommandeEleve(cmd);
                            commandeProduitRepository.save(newCmdProd);
                            cmd.addProduitCommande(newCmdProd);

                            break;
                        }
                    }
                }
            }

            commandeEleveRepository.save(cmd);

            cmdFamille.addCommandeEleve(cmd);

        }

        commandeFamilleRepository.save(cmdFamille);

        return getCommandeFamille(identifiantCommande, identifiantEcole);

    }

    @RequestMapping("/famille/commande/del")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<CommandeFamilleSyntheseDTO> deleteCommandeFamille(@RequestParam("identifiantCommande") String identifiantCommande,
                    @RequestParam("identifiantEleve") String identifiantEleve) {
        LOGGER.debug("deleting commande {}", identifiantCommande);

        CommandeFamille cmdFamille = commandeFamilleRepository.findByIdentifiant(identifiantCommande);
        for (CommandeEleve cmd : cmdFamille.getCommandesEleve()) {
            for (CommandeProduit cmdProd : cmd.getProduitsCommandes()) {
                commandeProduitRepository.delete(cmdProd);
            }
            commandeEleveRepository.delete(cmd);
        }
        commandeFamilleRepository.delete(cmdFamille);

        LOGGER.debug(" end deleting commande {}", cmdFamille);
        return getCommandesFamille(identifiantEleve);
    }
}
