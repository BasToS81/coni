package com.gor.sellphotos.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dao.CommandeEleve;
import com.gor.sellphotos.dao.CommandeFamille;
import com.gor.sellphotos.dao.CommandeFamille.StatutCommandeFamille;
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
import com.gor.sellphotos.security.SecuritySessionData;
import com.gor.sellphotos.security.UPAWithSessionDataToken;
import com.gor.sellphotos.utils.DateUtils;
import com.gor.sellphotos.utils.MapperUtils;

/**
 *
 */
@Controller
public class FamilleCommandesController extends AbstractRestHandler {

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

    @RequestMapping("/ws/famille/commande/getList")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<CommandeFamilleSyntheseDTO> getCommandesFamille(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();

        LOGGER.debug("loading commandes famille {}", identifiantEleve);

        List<CommandeFamilleSyntheseDTO> commandesDTO = new ArrayList<CommandeFamilleSyntheseDTO>();

        List<CommandeFamille> commandes = commandeFamilleRepository.findCmdDeTouteLaFamilleByIdEleveOrderByIdentifiantDesc(identifiantEleve);
        for (CommandeFamille commandeFamille : commandes) {

            CommandeFamilleSyntheseDTO cmdDTO = null;

            cmdDTO = MapperUtils.convert(commandeFamille, CommandeFamilleSyntheseDTO.class);
            /*
             * cmd.setDateCommande(DateUtils.formatDateTime(commandeFamille.getDateCommande()));
             * cmd.setDateLivraison(DateUtils.formatDateTime(commandeFamille.getDateLivraison()));
             * cmd.setDateValidation(DateUtils.formatDateTime(commandeFamille.getDateValidation()));
             * cmd.setIdentifiant(commandeFamille.getIdentifiant());
             * cmd.setMontant(commandeFamille.getMontant());
             * cmd.setMoyenPayement(commandeFamille.getMoyenPayement());
             * cmd.setStatut(commandeFamille.getStatut().name());
             */

            commandesDTO.add(cmdDTO);
        }

        LOGGER.debug("commande famille {}", commandes);
        return commandesDTO;
    }

    @RequestMapping("/famille/commande/create")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTO createCommandeFamille(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        LOGGER.debug("creation de la commande {}", identifiantEcole);

        CommandeFamilleDTO commandeFamille = new CommandeFamilleDTO();

        // Création de la Commande Famille
        CommandeFamille cmd = new CommandeFamille();
        Famille famille = familleRepository.findByIdentifiantUtilisateur(identifiantEleve);
        famille.addCommande(cmd);

        commandeFamilleRepository.save(cmd);
        familleRepository.save(famille);

        LOGGER.debug("Famille après sauvegarde : ", famille);

        commandeFamille.setIdentifiant(cmd.getIdentifiant());

        // Préaparation des commandes possibles par élèves

        ModeleEtTarif modele = modeleEtTarifRepository.findByIdEcole(identifiantEcole);

        List<Produit> listeDesProduitsDuModele = null;
        listeDesProduitsDuModele = produitRepository.findByModele(modele.getId());

        for (Eleve eleve : famille.getEleves()) {
            CommandeEleveDTO commandeEleveDTO = new CommandeEleveDTO();

            List<CommandeProduitDTO> commandesProduit = new ArrayList<CommandeProduitDTO>();

            commandeEleveDTO.setIdentifiantEleve(eleve.getIdentifiant());

            for (Produit produit : listeDesProduitsDuModele) {
                CommandeProduitDTO cmdProdDTO = new CommandeProduitDTO();
                ProduitDTO produitDTO = new ProduitDTO();

                LOGGER.debug("Produit dans la nouvelle commande : " + produit.toString());

                produitDTO = MapperUtils.convert(produit, ProduitDTO.class);

                cmdProdDTO.setMontant(0);
                cmdProdDTO.setProduit(produitDTO);
                cmdProdDTO.setQuantite(0);

                commandesProduit.add(cmdProdDTO);
            }

            commandeEleveDTO.setProduitsCommandes(commandesProduit);
            commandeFamille.addCommandeEleve(commandeEleveDTO);
        }

        return commandeFamille;

    }

    @RequestMapping("/ws/famille/commande/loadOrCreate")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTO getOrCreateCommande(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        CommandeFamilleDTO resultat = null;

        LOGGER.debug("loading commande for update ");

        /* TODO : S'assurer que c'est bien la commande de l'élève */

        // récupérer la commande modifiable.
        Famille fam = familleRepository.findByIdentifiantUtilisateur(identifiantEleve);
        CommandeFamille cmdFamille = fam.getCommandeEnCours();

        if (cmdFamille == null) {
            // Pas de commande pour cette famille en cours.
            // création de celle ci
            resultat = createCommandeFamille(authentication);
        }
        else {
            LOGGER.debug("commande famille : {}", cmdFamille.getIdentifiant());
            // récupération de la commnade existante
            resultat = getCommandeFamilleEtProduits(identifiantEcole, cmdFamille);
        }

        // update des informations de la session
        sessionData.setIdentifiantCommmandeEnCours(resultat.getIdentifiant());

        return resultat;
    }

    private CommandeFamilleDTO getCommandeFamilleEtProduits(Long identifiantEcole, CommandeFamille cmdFamille) {
        CommandeFamilleDTO commandeFamilleDTO = new CommandeFamilleDTO();

        commandeFamilleDTO = MapperUtils.convert(cmdFamille, CommandeFamilleDTO.class);

        for (Eleve elev : cmdFamille.getFamille().getEleves()) {

            CommandeEleve cmd = new CommandeEleve();
            // récupéré éventuellement la commandeeleveDTO déjà présente dans commandeFamilleDTO
            CommandeEleveDTO commandeEleveDTO = new CommandeEleveDTO();

            for (CommandeEleve cmdElev : cmdFamille.getCommandesEleve()) {
                if (cmdElev.getEleve().getId() == elev.getId()) {
                    cmd = cmdElev;
                    break;
                }
            }

            for (CommandeEleveDTO cmdEleveDTO : commandeFamilleDTO.getCommandesEleve()) {
                if (cmdEleveDTO.getIdentifiantEleve().compareTo(elev.getIdentifiant()) == 0) {
                    commandeEleveDTO = cmdEleveDTO;
                    break;
                }
            }

            commandeEleveDTO = MapperUtils.convert(cmd, CommandeEleveDTO.class);
            commandeEleveDTO.setIdentifiantEleve(elev.getIdentifiant());

            List<CommandeProduitDTO> commandesProduitDTO = new ArrayList<CommandeProduitDTO>();

            ModeleEtTarif modele = modeleEtTarifRepository.findByIdEcole(identifiantEcole);

            List<Produit> listeDesProduitsDuModele = null;

            listeDesProduitsDuModele = produitRepository.findByModele(modele.getId());

            /* TODO : améliorer l'algorithme en copiant les produits commandés et en retirant ceux trouvés de la liste pour diminuer le nombre de recherche. */

            for (Produit produit : listeDesProduitsDuModele) {

                CommandeProduitDTO cmdProdDTO = new CommandeProduitDTO();
                ProduitDTO prodDTO = new ProduitDTO();

                prodDTO = MapperUtils.convert(produit, ProduitDTO.class);

                cmdProdDTO.setProduit(prodDTO);
                cmdProdDTO.setMontant(0);
                cmdProdDTO.setQuantite(0);

                // Pour récupérer le commandeProduit, il faut le trouver dans la commande
                for (CommandeProduit prodOfCommande : cmd.getProduitsCommandes()) {

                    // vérification si c'est le même produit
                    if (prodOfCommande.getProduit().getId().equals(produit.getId())) {
                        // C'est le même produit on initialise avec les valeurs de la commande
                        cmdProdDTO.setMontant(prodOfCommande.getMontant());
                        cmdProdDTO.setQuantite(prodOfCommande.getQuantite());

                        break;
                    }

                }
                commandesProduitDTO.add(cmdProdDTO);

            }

            commandeEleveDTO.setProduitsCommandes(commandesProduitDTO);
            commandeFamilleDTO.addCommandeEleve(commandeEleveDTO);
        }

        LOGGER.debug("fin loading commande");

        return commandeFamilleDTO;
    }

    @RequestMapping("/ws/famille/commande/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTO getCommandeFamille(@RequestParam("identifiant") Long identifiantCommande, Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        CommandeFamille cmdFamilleEnBase = commandeFamilleRepository.findByIdentifiant(identifiantCommande);

        LOGGER.debug("loading commande {}", identifiantCommande);

        /* TODO : S'assurer que c'est bien la commande de l'élève */
        CommandeFamille cmdFamille = commandeFamilleRepository.findByIdentifiant(identifiantCommande);

        return getCommandeFamilleDTO(cmdFamille);

    }

    @RequestMapping("/ws/famille/commande/getEnCours")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTO getCommandeFamilleEnCours(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();
        Long identifiantCommandeEnCours = sessionData.getIdentifiantCommmandeEnCours();

        LOGGER.debug("loading commande {}", identifiantCommandeEnCours);

        /* TODO : S'assurer que c'est bien la commande de l'élève */
        CommandeFamille cmdFamilleEnBase = commandeFamilleRepository.findByIdentifiant(identifiantCommandeEnCours);

        return getCommandeFamilleDTO(cmdFamilleEnBase);

    }

    private CommandeFamilleDTO getCommandeFamilleDTO(CommandeFamille cmdFamille) {
        CommandeFamilleDTO commandeFamilleDTO = new CommandeFamilleDTO();

        commandeFamilleDTO.setDateCommande(cmdFamille.getDateCommande());
        commandeFamilleDTO.setDateLivraison(cmdFamille.getDateLivraison());
        commandeFamilleDTO.setDateValidation(cmdFamille.getDateValidation());
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

            /* TODO : améliorer l'algorithme en copiant les produits commandés et en retirant ceux trouvés de la liste pour diminuer le nombre de recherche. */

            for (CommandeProduit cmdProduitEnBase : cmd.getProduitsCommandes()) {

                Produit produit = cmdProduitEnBase.getProduit();

                CommandeProduitDTO cmdProd = new CommandeProduitDTO();
                ProduitDTO prod = new ProduitDTO();

                prod.setId(produit.getId());
                prod.setIdentifiant(produit.getIdentifiant());
                prod.setDesignation(produit.getDesignation());
                prod.setOrdre(produit.getOrdre());
                prod.setPrix_ecole_ttc(produit.getPrix_ecole_ttc());
                prod.setPrix_parent_ttc(produit.getPrix_parent_ttc());
                cmdProd.setProduit(prod);

                cmdProd.setMontant(cmdProduitEnBase.getMontant());
                cmdProd.setQuantite(cmdProduitEnBase.getQuantite());

                commandesProduit.add(cmdProd);

            }

            commandeEleveDTO.setProduitsCommandes(commandesProduit);
            commandeFamilleDTO.addCommandeEleve(commandeEleveDTO);
        }

        LOGGER.debug("fin loading commande");

        return commandeFamilleDTO;
    }

    @RequestMapping("/ws/famille/commande/save/produits")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTO saveCommandeFamille(
                    Authentication authentication,
                    @RequestBody CommandeFamilleDTO commandeFamille) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();
        Long identifiantCommandeEnCours = sessionData.getIdentifiantCommmandeEnCours();

        LOGGER.debug("saving commande en cours : {}", identifiantCommandeEnCours);

        CommandeFamille cmdFamilleEnBase = commandeFamilleRepository.findByIdentifiant(identifiantCommandeEnCours);

        return saveCommande(authentication, commandeFamille, identifiantEcole, cmdFamilleEnBase);
    }

    @RequestMapping("/ws/famille/commande/save/modepaiement")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTO saveCommandeFamilleModePaiement(
                    Authentication authentication,
                    @RequestBody CommandeFamilleDTO commandeFamille) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();
        Long identifiantCommandeEnCours = sessionData.getIdentifiantCommmandeEnCours();

        LOGGER.debug("saving Mode Paiement commande en cours : {}", identifiantCommandeEnCours);

        CommandeFamille cmdFamilleEnBase = commandeFamilleRepository.findByIdentifiant(identifiantCommandeEnCours);

        if (commandeFamille.getMoyenPayement() != null) {
            cmdFamilleEnBase.setMoyenPayement(commandeFamille.getMoyenPayement());
        }

        return getCommandeFamilleEtProduits(identifiantEcole, cmdFamilleEnBase);
    }

    @RequestMapping("/ws/famille/commande/validate")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTO validateCommandeFamille(
                    Authentication authentication,
                    @RequestBody CommandeFamilleDTO commandeFamille) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();
        Long identifiantCommandeEnCours = sessionData.getIdentifiantCommmandeEnCours();

        LOGGER.debug("validate commande en cours{}", identifiantCommandeEnCours);

        CommandeFamille cmdFamilleEnBase = commandeFamilleRepository.findByIdentifiant(identifiantCommandeEnCours);

        cmdFamilleEnBase.setDateValidation(DateUtils.getCurrentDate());
        if ("INTERNET".compareTo(cmdFamilleEnBase.getMoyenPayement()) != 0) {
            cmdFamilleEnBase.setStatut(StatutCommandeFamille.EN_ATTENTE_VALID_RESPONSABLE);
        }
        else {
            cmdFamilleEnBase.setStatut(StatutCommandeFamille.EN_ATTENTE_PAYEMENT);
        }

        // sauvegarde de la commande
        commandeFamilleRepository.save(cmdFamilleEnBase);

        LOGGER.debug("statut de la commande:{}", cmdFamilleEnBase.getStatut());

        LOGGER.debug("fin validate commande en cours");

        return getCommandeFamilleDTO(cmdFamilleEnBase);
    }

    private CommandeFamilleDTO saveCommande(Authentication authentication, CommandeFamilleDTO nouvelleCommandeFamille,
                    Long identifiantEcole, CommandeFamille cmdFamilleEnBase) {

        List<CommandeEleveDTO> commandesEleve = nouvelleCommandeFamille.getCommandesEleve();

        // Prise en compte des modifications des produits commandés
        // Suppression des anciens
        if (null != cmdFamilleEnBase.getCommandesEleve()) {
            for (CommandeEleve cmd : cmdFamilleEnBase.getCommandesEleve()) {
                for (CommandeProduit cmdProd : cmd.getProduitsCommandes()) {
                    commandeProduitRepository.delete(cmdProd);
                }
                commandeEleveRepository.delete(cmd);
            }
        }

        cmdFamilleEnBase.setCommandesEleve(new ArrayList<CommandeEleve>());
        cmdFamilleEnBase.setMontant(0);

        LOGGER.debug(" nb commandes {}", commandesEleve.size());

        for (CommandeEleveDTO cmdDTO : commandesEleve) {

            LOGGER.debug(" -save : commande identifiant : {}", cmdDTO.getId());

            // création des nouvelles données
            CommandeEleve cmd = new CommandeEleve();
            cmd.setEleve(eleveRepository.findByIdentifiant(cmdDTO.getIdentifiantEleve()));
            cmd.setFamille(cmdFamilleEnBase.getFamille());
            cmd.setCommandeFamille(cmdFamilleEnBase);

            ModeleEtTarif modele = modeleEtTarifRepository.findByIdEcole(identifiantEcole);
            List<Produit> listeDesProduitsDuModele = produitRepository.findByModele(modele.getId());

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

            if (cmd.getMontant() > 0) {
                commandeEleveRepository.save(cmd);
                cmdFamilleEnBase.addCommandeEleve(cmd);
            }

        }

        commandeFamilleRepository.save(cmdFamilleEnBase);

        return getCommandeFamilleEtProduits(identifiantEcole, cmdFamilleEnBase);
    }

    @RequestMapping("/ws/famille/commande/del")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<CommandeFamilleSyntheseDTO> deleteCommandeFamille(@RequestParam("identifiant") Long identifiantCommande, Authentication authentication) {
        LOGGER.debug("deleting commande {}", identifiantCommande);

        // TODO: Check if commande appartient bien à l'utilisateur OK

        CommandeFamille cmdFamille = commandeFamilleRepository.findByIdentifiant(identifiantCommande);
        for (CommandeEleve cmd : cmdFamille.getCommandesEleve()) {
            for (CommandeProduit cmdProd : cmd.getProduitsCommandes()) {
                commandeProduitRepository.delete(cmdProd);
            }
            commandeEleveRepository.delete(cmd);
        }
        commandeFamilleRepository.delete(cmdFamille);

        LOGGER.debug(" end deleting commande {}", cmdFamille);
        return getCommandesFamille(authentication);
    }
}
