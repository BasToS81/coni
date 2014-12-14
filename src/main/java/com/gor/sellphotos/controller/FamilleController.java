package com.gor.sellphotos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dao.Eleve;
import com.gor.sellphotos.dao.Famille;
import com.gor.sellphotos.dto.EleveDTO;
import com.gor.sellphotos.dto.FamilleDTO;
import com.gor.sellphotos.repository.CommandeEleveRepository;
import com.gor.sellphotos.repository.EleveRepository;
import com.gor.sellphotos.repository.FamilleRepository;
import com.gor.sellphotos.security.SessionData;

/**
 *
 */
@Controller
@Scope("request")
public class FamilleController extends AbstractRestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FamilleController.class);

    @Autowired
    private FamilleRepository familleRepository;

    @Autowired
    private EleveRepository eleveRepository;

    @Autowired
    private CommandeEleveRepository commandeEleveRepository;

    @Autowired
    private SessionData sessionData;

    @RequestMapping("/ws/famille/loadData")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public FamilleDTO getFamille(HttpSession session) {
        LOGGER.debug("loading famille");
        String identifiant = sessionData.getSessionsAndIdentifiant().get(session.getId());
        LOGGER.debug("Session id= {}; identifiant= {}", session.getId(), identifiant);
        FamilleDTO familleDTO = null;
        Famille famille = familleRepository.findByIdentifiantUtilisateur(identifiant);
        if (famille != null) {
            familleDTO = new FamilleDTO();
            familleDTO.setNomEcole(famille.getEcole().getNomEtablissement());
            List<EleveDTO> elevesDTO = new ArrayList<EleveDTO>();
            for (Eleve eleve : famille.getEleves()) {
                EleveDTO eleveDTO = new EleveDTO();
                LOGGER.debug("ecole : " + famille.getEcole().toString());
                LOGGER.debug("eleve : " + eleve.toString());
                LOGGER.debug("classe : " + eleve.getClasse().toString());
                eleveDTO.setCheminAccesImageEleve(famille.getEcole().getIdentifiantChiffre() + "/" + eleve.getClasse().getIdentifiantChiffre() + "/"
                                + eleve.getIdentifiantChiffre());

                eleveDTO.setDateLimiteAcces(eleve.getDateLimiteAcces());
                eleveDTO.setIdentifiantChiffre(eleve.getIdentifiantChiffre());
                eleveDTO.setNomClasse(eleve.getClasse().getNom());
                eleveDTO.setNomEleve(eleve.getNom());
                elevesDTO.add(eleveDTO);
            }
            familleDTO.setEleves(elevesDTO);
            familleDTO.setCheminAccesImageGroupe(famille.getEcole().getIdentifiantChiffre());
            familleDTO.setIdentifiantChiffreEcole(famille.getEcole().getIdentifiantChiffre());

        }
        LOGGER.debug("famille {}", famille);
        return familleDTO;
    }
    /*
     * @RequestMapping("/ws/famille/commande/get")
     * @ResponseBody
     * @ResponseStatus(HttpStatus.OK)
     * public List<CommandeEleveDTO> getCommandesFamille(@RequestParam("identifiant") String identifiantEleve) {
     * LOGGER.debug("loading commande famille {}", identifiantEleve);
     * List<CommandeEleveDTO> commandesDTO = new ArrayList<CommandeEleveDTO>();
     * List<CommandeEleve> commandes = commandeEleveRepository.findCmdDeTouteLaFamilleByIdEleve(identifiantEleve);
     * for (CommandeEleve commandeEleve : commandes) {
     * CommandeEleveDTO cmd = new CommandeEleveDTO();
     * cmd.setDateCommande(commandeEleve.getDateCommande());
     * cmd.setDateLivraison(commandeEleve.getDateLivraison());
     * cmd.setDateValidation(commandeEleve.getDateValidation());
     * cmd.setIdentifiant(commandeEleve.getIdentifiant());
     * cmd.setIdentifiantEleve(commandeEleve.getEleve().getIdentifiant());
     * cmd.setMontant(commandeEleve.getMontant());
     * cmd.setMoyenPayement(commandeEleve.getMoyenPayement());
     * cmd.setStatut(commandeEleve.getStatut().name());
     * List<CommandeProduitDTO> cmdProduitsDTO = new ArrayList<CommandeProduitDTO>();
     * for (CommandeProduit cmdProduit : commandeEleve.getProduitsCommandes()) {
     * CommandeProduitDTO cmdPdtDTO = new CommandeProduitDTO();
     * cmdPdtDTO.setMontant(cmdProduit.getMontant());
     * cmdPdtDTO.setQuantite(cmdProduit.getQuantite());
     * ProduitDTO pdtDTO = new ProduitDTO();
     * pdtDTO.setDesignation(cmdProduit.getProduit().getDesignation());
     * pdtDTO.setIdentifiant(cmdProduit.getProduit().getIdentifiant());
     * pdtDTO.setOrdre(cmdProduit.getProduit().getOrdre());
     * pdtDTO.setPrix_ecole_ttc(cmdProduit.getProduit().getPrix_ecole_ttc());
     * pdtDTO.setPrix_parent_ttc(cmdProduit.getProduit().getPrix_parent_ttc());
     * pdtDTO.setTypeProduit(cmdProduit.getProduit().getTypeProduit().name());
     * cmdPdtDTO.setProduit(pdtDTO);
     * cmdProduitsDTO.add(cmdPdtDTO);
     * }
     * cmd.setProduitsCommandes(cmdProduitsDTO);
     * commandesDTO.add(cmd);
     * }
     * LOGGER.debug("commande famille {}", commandes);
     * return commandesDTO;
     * }
     */
}
