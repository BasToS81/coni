package com.gor.sellphotos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
import com.gor.sellphotos.security.SecuritySessionData;
import com.gor.sellphotos.security.UPAWithSessionDataToken;

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

    @RequestMapping("/ws/famille/loadData")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public FamilleDTO getFamille(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();

        LOGGER.debug("loading famille");
        String identifiant = sessionData.getIdentifiantUtilisateur();
        LOGGER.debug("Session identifiant= {}", identifiant);
        FamilleDTO familleDTO = null;
        Famille famille = familleRepository.findByIdentifiantUtilisateur(identifiant);
        if (famille != null) {
            familleDTO = new FamilleDTO();
            familleDTO.setNomEcole(famille.getEcole().getNomEtablissement());

            // Ajout des éléments de sécurité
            sessionData.setIdentifiantEcole(famille.getEcole().getId());
            sessionData.setIdentifiantChiffreEcole(famille.getEcole().getIdentifiantChiffre());

            List<EleveDTO> elevesDTO = new ArrayList<EleveDTO>();
            for (Eleve eleve : famille.getEleves()) {
                EleveDTO eleveDTO = new EleveDTO();
                LOGGER.debug("ecole : " + famille.getEcole().toString());
                LOGGER.debug("eleve : " + eleve.toString());
                LOGGER.debug("classe : " + eleve.getClasse().toString());
                eleveDTO.setCheminAccesImageEleve(famille.getEcole().getIdentifiantChiffre() + "/" + eleve.getClasse().getIdentifiantChiffre() + "/"
                                + eleve.getIdentifiantChiffre());

                LOGGER.debug("chemin acces image eleve : " + eleveDTO.getCheminAccesImageEleve());
                eleveDTO.setCheminAccesImageGroupe(famille.getEcole().getIdentifiantChiffre() + "/" + eleve.getClasse().getIdentifiantChiffre());

                eleveDTO.setDateLimiteAccesFromDate(eleve.getDateLimiteAcces());
                eleveDTO.setIdentifiantChiffre(eleve.getIdentifiantChiffre());
                eleveDTO.setNomClasse(eleve.getClasse().getNom());
                eleveDTO.setNom(eleve.getNom());
                elevesDTO.add(eleveDTO);
            }
            familleDTO.setEleves(elevesDTO);
            familleDTO.setCheminAccesImageGroupe(famille.getEcole().getIdentifiantChiffre());
            familleDTO.setIdentifiantChiffreEcole(famille.getEcole().getIdentifiantChiffre());

        }
        LOGGER.debug("famille {}", famille);
        return familleDTO;
    }

}
