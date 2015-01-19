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
import com.gor.sellphotos.dao.Tva;
import com.gor.sellphotos.dto.EleveDTO;
import com.gor.sellphotos.dto.ProduitDTO;
import com.gor.sellphotos.dto.eleve.CommandeEleveDTOEleve;
import com.gor.sellphotos.dto.eleve.CommandeFamilleDTOEleve;
import com.gor.sellphotos.dto.eleve.CommandeFamilleSyntheseDTOEleve;
import com.gor.sellphotos.dto.eleve.CommandeProduitDTOEleve;
import com.gor.sellphotos.repository.CommandeEleveRepository;
import com.gor.sellphotos.repository.CommandeFamilleRepository;
import com.gor.sellphotos.repository.CommandeProduitRepository;
import com.gor.sellphotos.repository.EleveRepository;
import com.gor.sellphotos.repository.FamilleRepository;
import com.gor.sellphotos.repository.ModeleEtTarifRepository;
import com.gor.sellphotos.repository.ProduitRepository;
import com.gor.sellphotos.repository.TvaRepository;
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

    @Autowired
    private TvaRepository tvaRepository;

    @RequestMapping("/ws/famille/commande/getList")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<CommandeFamilleSyntheseDTOEleve> getCommandesFamille(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();

        LOGGER.debug("loading commandes famille {}", identifiantEleve);

        List<CommandeFamilleSyntheseDTOEleve> commandesDTO = new ArrayList<CommandeFamilleSyntheseDTOEleve>();

        List<CommandeFamille> commandes = commandeFamilleRepository.findCmdDeTouteLaFamilleByIdEleveOrderByIdentifiantDesc(identifiantEleve);
        for (CommandeFamille commandeFamille : commandes) {

            CommandeFamilleSyntheseDTOEleve cmdDTO = null;

            cmdDTO = MapperUtils.convert(commandeFamille, CommandeFamilleSyntheseDTOEleve.class);
            Tva tva = null;
            if (commandeFamille.getDateValidation() != null) {
                tva = tvaRepository.findByDateDeDemande(commandeFamille.getDateValidation(), commandeFamille.getDateValidation());
            }
            else {
                tva = tvaRepository.findByDateCourante();
            }
            if (tva == null) {
                cmdDTO.setTva(0);
            }
            else {
                cmdDTO.setTva(tva.getTva());
            }

            commandesDTO.add(cmdDTO);
        }

        LOGGER.debug("commande famille {}", commandes);
        return commandesDTO;
    }

    @RequestMapping("/famille/commande/create")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTOEleve createCommandeFamille(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        LOGGER.debug("creation de la commande {}", identifiantEcole);

        CommandeFamilleDTOEleve commandeFamilleDTO = new CommandeFamilleDTOEleve();

        Tva tva = tvaRepository.findByDateCourante();
        if (tva == null) {
            commandeFamilleDTO.setTva(0);
        }
        else {
            commandeFamilleDTO.setTva(tva.getTva());
        }

        // Création de la Commande Famille
        CommandeFamille cmd = new CommandeFamille();
        Famille famille = familleRepository.findByIdentifiantUtilisateur(identifiantEleve);
        famille.addCommande(cmd);

        commandeFamilleRepository.save(cmd);
        familleRepository.save(famille);

        LOGGER.debug("Famille après sauvegarde : ", famille);

        commandeFamilleDTO.setIdentifiant(cmd.getIdentifiant());

        // Préaparation des commandes possibles par élèves

        ModeleEtTarif modele = modeleEtTarifRepository.findByIdEcole(identifiantEcole);

        List<Produit> listeDesProduitsDuModele = null;
        listeDesProduitsDuModele = produitRepository.findByModele(modele.getId());

        for (Eleve eleve : famille.getEleves()) {
            CommandeEleveDTOEleve commandeEleveDTO = new CommandeEleveDTOEleve();

            List<CommandeProduitDTOEleve> commandesProduit = new ArrayList<CommandeProduitDTOEleve>();

            EleveDTO eleveDTO = MapperUtils.convert(eleve, EleveDTO.class);
            commandeEleveDTO.setEleve(eleveDTO);

            for (Produit produit : listeDesProduitsDuModele) {
                CommandeProduitDTOEleve cmdProdDTO = new CommandeProduitDTOEleve();
                ProduitDTO produitDTO = new ProduitDTO();

                LOGGER.debug("Produit dans la nouvelle commande : " + produit.toString());

                produitDTO = MapperUtils.convert(produit, ProduitDTO.class);

                cmdProdDTO.setMontantParentHT(0);
                cmdProdDTO.setMontantEcoleHT(0);
                cmdProdDTO.setProduit(produitDTO);
                cmdProdDTO.setQuantite(0);

                commandesProduit.add(cmdProdDTO);
            }

            commandeEleveDTO.setProduitsCommandes(commandesProduit);
            commandeFamilleDTO.addCommandeEleve(commandeEleveDTO);
        }

        return commandeFamilleDTO;

    }

    @RequestMapping("/ws/famille/commande/loadOrCreate")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTOEleve getOrCreateCommande(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        CommandeFamilleDTOEleve resultat = null;

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

    private CommandeFamilleDTOEleve getCommandeFamilleEtProduits(Long identifiantEcole, CommandeFamille cmdFamille) {
        CommandeFamilleDTOEleve commandeFamilleDTO = new CommandeFamilleDTOEleve();

        commandeFamilleDTO = MapperUtils.convert(cmdFamille, CommandeFamilleDTOEleve.class);

        Tva tva = tvaRepository.findByDateCourante();
        if (tva == null) {
            commandeFamilleDTO.setTva(0);
        }
        else {
            commandeFamilleDTO.setTva(tva.getTva());
        }

        for (Eleve elev : cmdFamille.getFamille().getEleves()) {

            CommandeEleve cmd = new CommandeEleve();
            // récupéré éventuellement la commandeeleveDTO déjà présente dans commandeFamilleDTO
            CommandeEleveDTOEleve commandeEleveDTO = null;

            for (CommandeEleve cmdElev : cmdFamille.getCommandesEleve()) {
                if (cmdElev.getEleve().getId() == elev.getId()) {
                    cmd = cmdElev;
                    break;
                }
            }

            boolean commandeEleveDTOTrouveDansCommandeFamille = false;
            for (CommandeEleveDTOEleve cmdEleveDTO : commandeFamilleDTO.getCommandesEleve()) {
                if (cmdEleveDTO.getEleve().getId() == elev.getId()) {
                    commandeEleveDTO = cmdEleveDTO;
                    commandeEleveDTOTrouveDansCommandeFamille = true;
                    LOGGER.debug("commandeEleveDTOTrouveDansCommandeFamille");
                    break;
                }
            }
            // Si on a pas trouve de commande déjà présente dans la commande Famille alors on génère une nouvelle
            if (!commandeEleveDTOTrouveDansCommandeFamille)
                commandeEleveDTO = MapperUtils.convert(cmd, CommandeEleveDTOEleve.class);

            List<CommandeProduitDTOEleve> commandesProduitDTO = new ArrayList<CommandeProduitDTOEleve>();

            ModeleEtTarif modele = modeleEtTarifRepository.findByIdEcole(identifiantEcole);

            List<Produit> listeDesProduitsDuModele = null;

            listeDesProduitsDuModele = produitRepository.findByModele(modele.getId());

            /* TODO : améliorer l'algorithme en copiant les produits commandés et en retirant ceux trouvés de la liste pour diminuer le nombre de recherche. */

            for (Produit produit : listeDesProduitsDuModele) {

                CommandeProduitDTOEleve cmdProdDTO = new CommandeProduitDTOEleve();
                ProduitDTO prodDTO = new ProduitDTO();

                prodDTO = MapperUtils.convert(produit, ProduitDTO.class);

                cmdProdDTO.setProduit(prodDTO);
                cmdProdDTO.setMontantParentHT(0);
                cmdProdDTO.setMontantEcoleHT(0);
                cmdProdDTO.setQuantite(0);

                // Pour récupérer le commandeProduit, il faut le trouver dans la commande
                for (CommandeProduit prodOfCommande : cmd.getProduitsCommandes()) {

                    // vérification si c'est le même produit
                    if (prodOfCommande.getProduit().getId().equals(produit.getId())) {
                        // C'est le même produit on initialise avec les valeurs de la commande
                        cmdProdDTO = MapperUtils.convert(prodOfCommande, CommandeProduitDTOEleve.class);
                        break;
                    }

                }

                commandesProduitDTO.add(cmdProdDTO);

            }

            commandeEleveDTO.setProduitsCommandes(commandesProduitDTO);

            // si la commande est nouvelle alors on l'ajout sinon non
            if (!commandeEleveDTOTrouveDansCommandeFamille)
                commandeFamilleDTO.addCommandeEleve(commandeEleveDTO);
        }

        LOGGER.debug("fin loading commande");

        return commandeFamilleDTO;
    }

    @RequestMapping("/ws/famille/commande/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTOEleve getCommandeFamille(@RequestParam("identifiant") Long identifiantCommande, Authentication authentication) {

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
    public CommandeFamilleDTOEleve getCommandeFamilleEnCours(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();
        Long identifiantCommandeEnCours = sessionData.getIdentifiantCommmandeEnCours();

        LOGGER.debug("loading commande {}", identifiantCommandeEnCours);

        /* TODO : S'assurer que c'est bien la commande de l'élève */
        CommandeFamille cmdFamilleEnBase = commandeFamilleRepository.findByIdentifiant(identifiantCommandeEnCours);

        return getCommandeFamilleDTO(cmdFamilleEnBase);

    }

    private CommandeFamilleDTOEleve getCommandeFamilleDTO(CommandeFamille cmdFamille) {
        CommandeFamilleDTOEleve commandeFamilleDTO = new CommandeFamilleDTOEleve();

        commandeFamilleDTO = MapperUtils.convert(cmdFamille, CommandeFamilleDTOEleve.class);

        Tva tva = null;
        if (cmdFamille.getDateValidation() != null) {
            tva = tvaRepository.findByDateDeDemande(cmdFamille.getDateValidation(), cmdFamille.getDateValidation());
        }
        else {
            tva = tvaRepository.findByDateCourante();
        }
        if (tva == null) {
            commandeFamilleDTO.setTva(0);
        }
        else {
            commandeFamilleDTO.setTva(tva.getTva());
        }

        LOGGER.debug("fin loading commande");

        return commandeFamilleDTO;
    }

    @RequestMapping("/ws/famille/commande/save/produits")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeFamilleDTOEleve saveCommandeFamille(
                    Authentication authentication,
                    @RequestBody CommandeFamilleDTOEleve commandeFamille) {

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
    public CommandeFamilleDTOEleve saveCommandeFamilleModePaiement(
                    Authentication authentication,
                    @RequestBody CommandeFamilleDTOEleve commandeFamille) {

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
    public CommandeFamilleDTOEleve validateCommandeFamille(
                    Authentication authentication,
                    @RequestBody CommandeFamilleDTOEleve commandeFamille) {

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

    private CommandeFamilleDTOEleve saveCommande(Authentication authentication, CommandeFamilleDTOEleve nouvelleCommandeFamille,
                    Long identifiantEcole, CommandeFamille cmdFamilleEnBase) {

        List<CommandeEleveDTOEleve> commandesEleve = nouvelleCommandeFamille.getCommandesEleve();

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
        cmdFamilleEnBase.setMontantParentHT(0);
        cmdFamilleEnBase.setMontantEcoleHT(0);

        LOGGER.debug(" nb commandes {}", commandesEleve.size());

        for (CommandeEleveDTOEleve cmdDTO : commandesEleve) {

            LOGGER.debug(" -save : commande identifiant : {}", cmdDTO.getId());

            // création des nouvelles données
            CommandeEleve cmd = new CommandeEleve();
            cmd.setEleve(eleveRepository.findByIdentifiant(cmdDTO.getEleve().getIdentifiant()));
            cmd.setFamille(cmdFamilleEnBase.getFamille());
            cmd.setCommandeFamille(cmdFamilleEnBase);

            ModeleEtTarif modele = modeleEtTarifRepository.findByIdEcole(identifiantEcole);
            List<Produit> listeDesProduitsDuModele = produitRepository.findByModele(modele.getId());

            /* TODO : améliorer l'algorithme en copiant les produits commandés et en retirant ceux trouvés de la liste pour diminuer le nombre de recherche. */

            for (Produit produit : listeDesProduitsDuModele) {

                // Sauvegarde des nouveaux produits + enrichissement en sortie avec les modeles existants
                for (CommandeProduitDTOEleve cmdProdDTO : cmdDTO.getProduitsCommandes()) {
                    // vérification si c'est le même produit
                    if (cmdProdDTO.getProduit().getId().equals(produit.getId())) {
                        if (cmdProdDTO.getQuantite() != 0) {

                            CommandeProduit newCmdProd = new CommandeProduit();
                            newCmdProd.setMontantParentHT(cmdProdDTO.getMontantParentHT());
                            newCmdProd.setMontantEcoleHT(cmdProdDTO.getMontantEcoleHT());
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

            if (cmd.getMontantParentHT() > 0) {
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
    public List<CommandeFamilleSyntheseDTOEleve> deleteCommandeFamille(@RequestParam("identifiant") Long identifiantCommande, Authentication authentication) {
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
