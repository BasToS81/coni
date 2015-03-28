package com.gor.sellphotos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.gor.sellphotos.dao.Classe;
import com.gor.sellphotos.dao.CommandeEcole;
import com.gor.sellphotos.dao.CommandeEcole.StatutCommandeEcole;
import com.gor.sellphotos.dao.CommandeEleve;
import com.gor.sellphotos.dao.CommandeFamille;
import com.gor.sellphotos.dao.CommandeFamille.StatutCommandeFamille;
import com.gor.sellphotos.dao.Eleve;
import com.gor.sellphotos.dao.ModeleEtTarif;
import com.gor.sellphotos.dao.Produit;
import com.gor.sellphotos.dto.EleveDTO;
import com.gor.sellphotos.dto.ModeleEtTarifDTO;
import com.gor.sellphotos.dto.ProduitDTO;
import com.gor.sellphotos.dto.ecole.CommandeClasseSyntheseDTOEcole;
import com.gor.sellphotos.dto.ecole.CommandeEcoleDTO;
import com.gor.sellphotos.dto.ecole.CommandeEleveDTOEcole;
import com.gor.sellphotos.dto.ecole.CommandeFamilleDTOEcole;
import com.gor.sellphotos.dto.ecole.CommandeProduitDTOEcole;
import com.gor.sellphotos.dto.eleve.CommandeEleveSyntheseDTOEleve;
import com.gor.sellphotos.repository.ClasseRepository;
import com.gor.sellphotos.repository.CommandeEcoleRepository;
import com.gor.sellphotos.repository.CommandeEleveRepository;
import com.gor.sellphotos.repository.CommandeFamilleRepository;
import com.gor.sellphotos.repository.CommandeProduitRepository;
import com.gor.sellphotos.repository.EcoleRepository;
import com.gor.sellphotos.repository.EleveRepository;
import com.gor.sellphotos.repository.FamilleRepository;
import com.gor.sellphotos.repository.ModeleEtTarifRepository;
import com.gor.sellphotos.repository.ProduitRepository;
import com.gor.sellphotos.repository.TvaRepository;
import com.gor.sellphotos.security.SecuritySessionData;
import com.gor.sellphotos.security.UPAWithSessionDataToken;
import com.gor.sellphotos.utils.DateUtils;
import com.gor.sellphotos.utils.FinanceUtils;
import com.gor.sellphotos.utils.MapperUtils;

/**
 *
 */
@Controller
public class EcoleCommandesController extends AbstractRestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EcoleCommandesController.class);

    @Autowired
    private FamilleRepository familleRepository;

    @Autowired
    private EleveRepository eleveRepository;

    @Autowired
    private EcoleRepository ecoleRepository;

    @Autowired
    private ClasseRepository classeRepository;

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
    private CommandeEcoleRepository commandeEcoleRepository;

    @Autowired
    private TvaRepository tvaRepository;

    /*
     * ------------------------------------
     * Commande des écoles au photographe
     * ------------------------------------
     */

    @RequestMapping("/ws/ecole/commande/getList")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<CommandeEcoleDTO> getCommandesEcole(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantUtilisateur = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        LOGGER.debug("loading commandes ecoles {}", identifiantEcole);

        // Chargement des commandes de l'école
        List<CommandeEcole> commandesEcole = commandeEcoleRepository.findByIdEcole(identifiantEcole);

        List<CommandeEcoleDTO> commandesEcoleDTO = new ArrayList<CommandeEcoleDTO>();

        for (CommandeEcole cmdEcole : commandesEcole) {
            CommandeEcoleDTO cmdEcoleDTO = MapperUtils.convert(cmdEcole, CommandeEcoleDTO.class);
            commandesEcoleDTO.add(cmdEcoleDTO);
        }

        LOGGER.debug("commandes de l'école {}", commandesEcoleDTO);
        return commandesEcoleDTO;
    }

    @RequestMapping("/ws/ecole/commande/getOrCreate")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeEcoleDTO getOrCreateCommandeEcole(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantUtilisateur = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        CommandeEcole commandeEcoleEnCours = null;

        List<CommandeEcole> commandeEcoleStatutEnCours = commandeEcoleRepository.findByIdEcoleEtStatut(identifiantEcole, StatutCommandeEcole.EN_COURS);

        if (null == commandeEcoleStatutEnCours || commandeEcoleStatutEnCours.size() == 0) {

            // Creation

            LOGGER.debug("create commande de l' ecole {}", identifiantEcole);
            commandeEcoleEnCours = new CommandeEcole();

            commandeEcoleEnCours.setEcole(ecoleRepository.findById(identifiantEcole));
            commandeEcoleEnCours.setStatut(StatutCommandeEcole.EN_COURS);

            commandeEcoleRepository.save(commandeEcoleEnCours);
            sessionData.setIdentifiantCommmandeEnCours(commandeEcoleEnCours.getId());

        }
        else {
            commandeEcoleEnCours = commandeEcoleStatutEnCours.get(0);
        }

        LOGGER.debug("getOrCreate commande ecole {}", sessionData.getIdentifiantCommmandeEnCours());

        CommandeEcoleDTO commandeEcoleDTO = MapperUtils.convert(commandeEcoleEnCours, CommandeEcoleDTO.class);

        LOGGER.debug("getOrCreate commande ecole effectuée {}", sessionData.getIdentifiantCommmandeEnCours());

        return commandeEcoleDTO;
    }

    @RequestMapping("/ws/ecole/commande/validate")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public boolean validateCommandeEcole(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantUtilisateur = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();
        Long identifiantCommandeEcoleEnCours = sessionData.getIdentifiantCommmandeEnCours();

        LOGGER.debug("validation commande ecole {}", identifiantCommandeEcoleEnCours);

        // Chargement des commandes de l'école
        CommandeEcole commandeEcoleEnCours = commandeEcoleRepository.findById(identifiantCommandeEcoleEnCours);

        // TODO : Vérifier que toutes les commandes sont bien payées

        commandeEcoleEnCours.setDateCommande(DateUtils.getCurrentDate());

        // TODO : vérifier à quoi sert date Commande et date Validation

        commandeEcoleEnCours.setStatut(StatutCommandeEcole.COMMANDEE);

        // Recherche des commandes des familles à ajouter à la commande de l'école
        List<CommandeFamille> commandesFamilles = commandeFamilleRepository.findValidateByIdEcole(identifiantEcole);

        for (CommandeFamille commandeFamille : commandesFamilles) {

            commandeFamille.setDateValidation(commandeEcoleEnCours.getDateCommande());
            commandeFamille.setStatut(StatutCommandeFamille.EN_LIVRAISON);

            commandeEcoleEnCours.addCommandesFamille(commandeFamille);
        }

        commandeEcoleRepository.save(commandeEcoleEnCours);

        LOGGER.debug("validation commande ecole effectuée {}", identifiantCommandeEcoleEnCours);

        return true;
    }

    @RequestMapping("/ws/ecole/commande/createAndValidate")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public boolean createAndValidateCommandeEcole(Authentication authentication) {

        LOGGER.debug("création et validation");

        // TODO: voir si on ne simplifie pas cette partie

        getOrCreateCommandeEcole(authentication);
        validateCommandeEcole(authentication);

        LOGGER.debug("création et validation ecole effectuée {}");

        return true;
    }

    @RequestMapping("/ws/ecole/commande/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeEcoleDTO getOrCreateCommandeEcole(@RequestParam("id") Long idCommande, Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantUtilisateur = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        LOGGER.debug("get commande {} de l' ecole {}", idCommande, identifiantEcole);

        CommandeEcoleDTO commandeEcoleDTO = null;

        // TODO: vérifier que la commande fait partie de l'école

        CommandeEcole commandeEcole = commandeEcoleRepository.findById(idCommande);

        if (null != commandeEcole) {

            commandeEcoleDTO = MapperUtils.convert(commandeEcole, CommandeEcoleDTO.class);

            for (CommandeFamille cmdFamille : commandeEcole.getCommandesFamille()) {
                LOGGER.debug(" Ajout des commandes Familles");
                CommandeFamilleDTOEcole cmdFamilleDTO = MapperUtils.convert(cmdFamille, CommandeFamilleDTOEcole.class);

                for (CommandeEleve cmdEleve : cmdFamille.getCommandesEleve()) {
                    cmdFamilleDTO.addNomEleve(cmdEleve.getEleve().getNom());
                }

                commandeEcoleDTO.addCommandeFamille(cmdFamilleDTO);
            }

        }

        LOGGER.debug("get commande ecole effectuée {}", idCommande);

        return commandeEcoleDTO;
    }

    @RequestMapping("/ws/ecole/commandes/classe/eleve/getList")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<CommandeEleveSyntheseDTOEleve> getCommandesFamilleACommander(@RequestParam("identifiant") String identifiantEleve, Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        // TODO : vérifier que l'élève fait partie de l'école

        LOGGER.debug("loading commandes eleves {}", identifiantEleve);

        List<CommandeEleveSyntheseDTOEleve> commandesDTO = new ArrayList<CommandeEleveSyntheseDTOEleve>();

        List<CommandeFamille> commandes = commandeFamilleRepository.findValidateByIdentifiantEleve(identifiantEleve);
        if (commandes != null) {

            for (CommandeFamille commandeFamille : commandes) {

                CommandeEleveSyntheseDTOEleve cmdDTO = null;

                cmdDTO = MapperUtils.convert(commandeFamille, CommandeEleveSyntheseDTOEleve.class);

                double tva = FinanceUtils.getTVAValue(commandeFamille.getDateValidation(), tvaRepository);

                // TODO : regarder pour voir comment gérer la TVA lors de la visualisation des produits

                cmdDTO.setTva(tva);

                commandesDTO.add(cmdDTO);
            }
        }
        LOGGER.debug("commande eleve {}", commandes);
        return commandesDTO;
    }

    @RequestMapping("/ws/ecole/commandes/classe/eleve/save")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void saveCommandesACommander(@RequestBody CommandeClasseSyntheseDTOEcole commandesClasseSyntheseDTO,
                    Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        // TODO : vérifier que les élèves font parti de l'école

        LOGGER.debug("saving new commandes eleves de la classe {}", commandesClasseSyntheseDTO.getNom());

        Map<String, CommandeFamille> listCommandesFamilles = new HashMap<String, CommandeFamille>();

        // On parcourt toutes les commandes et on créer des nouvelles commandes familles pour chaque élève.
        for (CommandeEleveDTOEcole cmdEleveDTO : commandesClasseSyntheseDTO.getCommandeEleve()) {

            CommandeFamille cmdFamille = new CommandeFamille();
            cmdFamille.setDateCommande(DateUtils.getCurrentDate());
            cmdFamille.setMoyenPayement("ESPECE");
            cmdFamille.setStatut();

            // On parcourt les produits commandés
            for (CommandeProduitDTOEcole cmdProduits : cmdEleveDTO.getProduitsCommandes()) {

                if (cmdProduits.getNewQuantite() > 0) {

                    // On vérifie si on a une commande famille existante pour l'élève
                    CommandeFamille cmdFamille = listCommandesFamilles.get(cmdEleveDTO.getEleve().getIdentifiant());
                    if (cmdFamille != null) {

                        CommandeEleve cmdEleve = new CommandeEleve();
                        cmdEleve.setCommandeFamille(cmdFamille);
                        cmdEleve.setEleve(eleve);

                    }

                }

            }

        }

        LOGGER.debug("saving new commandes done");

    }

    /*
     * ------------------------------------
     * Commande des élèves pour l'école
     * ------------------------------------
     */

    @RequestMapping("/ws/ecole/commande/classe/getList")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeClasseSyntheseDTOEcole getCommandesEleveOfClasse(@RequestParam("idClasse") String identifiantChiffreClasse, Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantUtilisateur = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        LOGGER.debug("loading commandes classe {}", identifiantChiffreClasse);

        if (identifiantChiffreClasse.compareTo("0") == 0) {
            List<Classe> classes = classeRepository.findByIdEcoleOrderByNom(identifiantEcole);
            if (classes != null) {
                identifiantChiffreClasse = classes.get(0).getIdentifiantChiffre();
            }
        }

        CommandeClasseSyntheseDTOEcole commandesClasseSyntheseDTO = new CommandeClasseSyntheseDTOEcole();

        ModeleEtTarif modeleEtTarif = modeleEtTarifRepository.findByIdEcole(identifiantEcole);
        commandesClasseSyntheseDTO.setModeleEtTarif(MapperUtils.convert(modeleEtTarif, ModeleEtTarifDTO.class));

        Classe classe = classeRepository.findByIdentifiantChiffre(identifiantChiffreClasse);
        commandesClasseSyntheseDTO.setNom(classe.getNom());

        List<Object[]> commandes = commandeEleveRepository.findSyntheseByIdentifiantChiffreClasse(identifiantChiffreClasse);
        
        List<CommandeEleve> commandesElevesDeLaClasse = commandeEleveRepository.findACommanderByIdChiffreClasse(identifiantChiffreClasse);

        Map<String, CommandeEleveDTOEcole> commandesDTO = new HashMap<String, CommandeEleveDTOEcole>();

        //Parcours des élèves
        for (Eleve eleve : classe.getEleves()) {

            eleve.get
            
            // Récupération de la commande si elle est déjà présente dans la map
            CommandeEleveDTOEcole cmdDTO = commandesDTO.get((String) commandeEleve[1]);
            if (cmdDTO == null) {
                // création de la commande
                cmdDTO = new CommandeEleveDTOEcole();

                Eleve eleve = eleveRepository.findByIdentifiant((String) commandeEleve[1]);
                EleveDTO eleveDTO = MapperUtils.convert(eleve, EleveDTO.class);
                cmdDTO.setEleve(eleveDTO);

                // Détermination si les commandes sont toutes payées
                if (eleve.getCommandesByStatut(StatutCommandeFamille.EN_ATTENTE_PAYEMENT).size() > 0) {
                    eleveDTO.setMontantRestantAPayerEcoleHT(1);
                }

                for (Produit produit : modeleEtTarif.getProduits()) {
                    Long idProduit = (Long) commandeEleve[3];

                    CommandeProduitDTOEcole cmdProduitDTO = new CommandeProduitDTOEcole();
                    CommandeProduitDTOEcole newCmdProduitDTO = new CommandeProduitDTOEcole();
                    ProduitDTO produitDTO = MapperUtils.convert(produit, ProduitDTO.class);
                    cmdProduitDTO.setProduit(produitDTO);
                    newCmdProduitDTO.setProduit(produitDTO);

                    cmdProduitDTO.setQuantite(0);
                    cmdProduitDTO.setMontantParentHT(0);
                    cmdProduitDTO.setMontantEcoleHT(0);
                    cmdProduitDTO.setMontantParentTTC(0);
                    cmdProduitDTO.setMontantEcoleTTC(0);

                    if (idProduit == produit.getId()) {

                        cmdProduitDTO.setQuantite(((Long) commandeEleve[4]).intValue());
                        cmdProduitDTO.setMontantParentHT((double) commandeEleve[5]);
                        cmdProduitDTO.setMontantEcoleHT((double) commandeEleve[6]);
                        cmdProduitDTO.setMontantParentTTC((double) commandeEleve[7]);
                        cmdProduitDTO.setMontantEcoleTTC((double) commandeEleve[8]);

                    }

                    cmdDTO.addMontantEcoleHT(cmdProduitDTO.getMontantEcoleHT());
                    cmdDTO.addMontantParentHT(cmdProduitDTO.getMontantParentHT());
                    cmdDTO.addMontantEcoleTTC(cmdProduitDTO.getMontantEcoleTTC());
                    cmdDTO.addMontantParentTTC(cmdProduitDTO.getMontantParentTTC());

                    cmdDTO.addProduitsCommandes(cmdProduitDTO);
                }

                commandesDTO.put(eleve.getIdentifiant(), cmdDTO);

            }
            else {
                // Mise à jour de la commande

                for (CommandeProduitDTOEcole cmdProduitDTO : cmdDTO.getProduitsCommandes()) {
                    Long idProduit = (Long) commandeEleve[3];
                    if (idProduit == cmdProduitDTO.getProduit().getId()) {

                        cmdProduitDTO.setQuantite(((Long) commandeEleve[4]).intValue());
                        cmdProduitDTO.setMontantParentHT((double) commandeEleve[5]);
                        cmdProduitDTO.setMontantEcoleHT((double) commandeEleve[6]);
                        cmdProduitDTO.setMontantParentTTC((double) commandeEleve[7]);
                        cmdProduitDTO.setMontantEcoleTTC((double) commandeEleve[8]);

                        cmdDTO.addMontantEcoleHT(cmdProduitDTO.getMontantEcoleHT());
                        cmdDTO.addMontantParentHT(cmdProduitDTO.getMontantParentHT());
                        cmdDTO.addMontantEcoleTTC(cmdProduitDTO.getMontantEcoleTTC());
                        cmdDTO.addMontantParentTTC(cmdProduitDTO.getMontantParentTTC());

                        break;
                    }
                }

            }

        }

        // affichage des élèves qui n'ont pas de commande en cours.
        for (Eleve eleveDeLaClasse : classe.getEleves()) {
            CommandeEleveDTOEcole cmdDTO = commandesDTO.get(eleveDeLaClasse.getIdentifiant());

            if (null == cmdDTO) {
                // Pas de commandes en base, on en créée une virtuelle
                cmdDTO = new CommandeEleveDTOEcole();
                EleveDTO eleveDTO = MapperUtils.convert(eleveDeLaClasse, EleveDTO.class);
                cmdDTO.setEleve(eleveDTO);

                for (Produit produit : modeleEtTarif.getProduits()) {
                    CommandeProduitDTOEcole cmdProduitDTO = new CommandeProduitDTOEcole();
                    cmdProduitDTO.setProduit(MapperUtils.convert(produit, ProduitDTO.class));
                    cmdDTO.addProduitsCommandes(cmdProduitDTO);
                }

            }

            commandesClasseSyntheseDTO.addCommandeEleveSynthese(cmdDTO);

        }

        LOGGER.debug("commandes eleve de classe {}", commandesClasseSyntheseDTO);
        return commandesClasseSyntheseDTO;
    }

    @RequestMapping("/ws/ecole/eleve/commande/getList")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<CommandeEleveSyntheseDTOEleve> getCommandesFamille(@RequestParam("identifiant") String identifiantEleve, Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        // TODO : vérifier que l'élève fait partie de l'école

        LOGGER.debug("loading commandes eleves {}", identifiantEleve);

        List<CommandeEleveSyntheseDTOEleve> commandesDTO = new ArrayList<CommandeEleveSyntheseDTOEleve>();

        List<CommandeFamille> commandes = commandeFamilleRepository.findNotEnCoursByIdentifiantEleve(identifiantEleve);
        if (commandes != null) {

            for (CommandeFamille commandeFamille : commandes) {

                CommandeEleveSyntheseDTOEleve cmdDTO = null;

                cmdDTO = MapperUtils.convert(commandeFamille, CommandeEleveSyntheseDTOEleve.class);

                double tva = FinanceUtils.getTVAValue(commandeFamille.getDateValidation(), tvaRepository);

                // TODO : regarder pour voir comment gérer la TVA lors de la visualisation des produits

                cmdDTO.setTva(tva);

                commandesDTO.add(cmdDTO);
            }
        }
        LOGGER.debug("commande eleve {}", commandes);
        return commandesDTO;
    }

    @RequestMapping("/ws/ecole/commande/getListNonPaye")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<CommandeFamilleDTOEcole> getCommandesFamillesNonPayees(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantUtilisateur = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        LOGGER.debug("loading commandes familles non payés de la classe {}", identifiantEcole);

        List<CommandeFamilleDTOEcole> commandesFamillesDTO = new ArrayList<CommandeFamilleDTOEcole>();

        List<CommandeFamille> commandesFamilles = commandeFamilleRepository.findByIdEcoleEtStatutNonPaye(identifiantEcole);

        if (commandesFamilles != null) {
            for (CommandeFamille cmdFamille : commandesFamilles) {

                CommandeFamilleDTOEcole cmdFamilleDTO = MapperUtils.convert(cmdFamille, CommandeFamilleDTOEcole.class);

                for (CommandeEleve cmdEleve : cmdFamille.getCommandesEleve()) {
                    cmdFamilleDTO.addNomEleve(cmdEleve.getEleve().getNom());
                }

                commandesFamillesDTO.add(cmdFamilleDTO);

            }
        }

        return commandesFamillesDTO;

    }

    @RequestMapping("/ws/ecole/commande/validatePaiement")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void validatePaiementCommandeFamille(
                    @RequestParam("identifiant") Long identifiantCommande,
                    Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        // TODO: vérifier que la commande appartient à un élève de l'école

        LOGGER.debug("validate paiement commande {}", identifiantCommande);

        CommandeFamille cmdFamilleEnBase = commandeFamilleRepository.findByIdentifiant(identifiantCommande);

        // On update la commande que si elle est dans le bon statut
        if (cmdFamilleEnBase.getStatut().equals(StatutCommandeFamille.EN_ATTENTE_PAYEMENT)) {

            cmdFamilleEnBase.setDateValidation(DateUtils.getCurrentDate());
            cmdFamilleEnBase.setStatut(StatutCommandeFamille.EN_ATTENTE_VALID_RESPONSABLE);
            // sauvegarde de la commande
            commandeFamilleRepository.save(cmdFamilleEnBase);
        }
        LOGGER.debug("statut de la commande:{}", cmdFamilleEnBase.getStatut());

        LOGGER.debug("fin validate paiement commande");

    }

    @RequestMapping("/ws/ecole/commandes/eleve/getList")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<CommandeFamilleDTOEcole> getCommandesFamille(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        // TODO : vérifier que l'élève fait partie de l'école

        LOGGER.debug("loading commandes eleves");

        List<CommandeFamilleDTOEcole> commandesDTO = new ArrayList<CommandeFamilleDTOEcole>();

        List<CommandeFamille> commandesFamilles = commandeFamilleRepository.findValidateByIdEcole(identifiantEcole);
        if (commandesFamilles != null) {
            for (CommandeFamille cmdFamille : commandesFamilles) {

                CommandeFamilleDTOEcole cmdFamilleDTO = MapperUtils.convert(cmdFamille, CommandeFamilleDTOEcole.class);

                for (CommandeEleve cmdEleve : cmdFamille.getCommandesEleve()) {
                    cmdFamilleDTO.addNomEleve(cmdEleve.getEleve().getNom());
                }

                commandesDTO.add(cmdFamilleDTO);

            }
        }
        LOGGER.debug("commande eleve {}", commandesFamilles);
        return commandesDTO;
    }
}
