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

import com.gor.sellphotos.dao.Eleve;
import com.gor.sellphotos.dao.Famille;
import com.gor.sellphotos.dto.EleveDTO;
import com.gor.sellphotos.dto.FamilleDTO;
import com.gor.sellphotos.repository.EleveRepository;
import com.gor.sellphotos.repository.FamilleRepository;

/**
 *
 */
@Controller
public class FamilleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FamilleController.class);

    @Autowired
    private FamilleRepository familleRepository;

    @Autowired
    private EleveRepository eleveRepository;

    @RequestMapping("/rest/famille")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public FamilleDTO getFamille(@RequestParam("identifiant") String identifiant) {
        LOGGER.debug("loading user {}", identifiant);
        FamilleDTO familleDTO = null;
        Famille famille = familleRepository.findByIdentifiantUtilisateur(identifiant);
        if (famille != null) {
            familleDTO = new FamilleDTO();
            familleDTO.setNomEcole(famille.getEcole().getNomEtablissement());
            List<EleveDTO> elevesDTO = new ArrayList<EleveDTO>();
            for (Eleve eleve : famille.getEleves()) {
                EleveDTO eleveDTO = new EleveDTO();
                eleveDTO.setCheminAccesImageEleve(famille.getEcole().getIdentifiantChiffre() + "/" + eleve.getClasse().getIdentifiantChiffre() + "/"
                                + eleve.getIdentifiantChiffre());

                eleveDTO.setDateLimiteAcces(eleve.getDateLimiteAcces());
                eleveDTO.setIdentifiantChiffre(eleve.getIdentifiantChiffre());
                eleveDTO.setNomClasse(eleve.getClasse().getNom());
                eleveDTO.setNomEleve(eleve.getUtilisateur().getNom());
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
