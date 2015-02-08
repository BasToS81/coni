package com.gor.sellphotos.controller;

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

import com.gor.sellphotos.dao.Ecole;
import com.gor.sellphotos.dto.EcoleDTO;
import com.gor.sellphotos.repository.CommandeEleveRepository;
import com.gor.sellphotos.repository.EcoleRepository;
import com.gor.sellphotos.repository.EleveRepository;
import com.gor.sellphotos.security.SecuritySessionData;
import com.gor.sellphotos.security.UPAWithSessionDataToken;
import com.gor.sellphotos.utils.MapperUtils;

/**
 *
 */
@Controller
@Scope("request")
public class EcoleController extends AbstractRestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EcoleController.class);

    @Autowired
    private EcoleRepository ecoleRepository;

    @Autowired
    private EleveRepository eleveRepository;

    @Autowired
    private CommandeEleveRepository commandeEleveRepository;

    @RequestMapping("/ws/ecole/loadData")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public EcoleDTO getEcole(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();

        LOGGER.debug("loading ecole");
        String identifiant = sessionData.getIdentifiantUtilisateur();
        LOGGER.debug("Session identifiant= {}", identifiant);

        EcoleDTO ecoleDTO = null;
        Ecole ecole = ecoleRepository.findByIdentifiantUtilisateurResponsable(identifiant);
        if (ecole == null) {
            ecole = ecoleRepository.findByIdentifiantUtilisateurEleve(identifiant);
        }
        if (ecole != null) {

            LOGGER.debug("ecole {}", ecoleDTO);
            ecoleDTO = MapperUtils.convert(ecole, EcoleDTO.class);

            // Ajout des éléments de sécurité
            sessionData.setIdentifiantEcole(ecole.getId());
            sessionData.setIdentifiantChiffreEcole(ecole.getIdentifiantChiffre());

        }
        LOGGER.debug("ecoleDTO {}", ecoleDTO);
        return ecoleDTO;
    }

    @RequestMapping("/ws/ecole/activation")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public int getActivation(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();

        LOGGER.debug("loading ecole");
        Long idEcole = sessionData.getIdentifiantEcole();

        int resultat = -1;

        // Vérification de l'état de l'activation des élèves
        int nbActive = ecoleRepository.findEtatActivationActive(idEcole);
        int nbDesactive = ecoleRepository.findEtatActivationDesactive(idEcole);

        if (nbActive == 0) {
            if (nbDesactive == 0) {
                // Cas sans élève...
                resultat = -1;
            }
            else {
                // cas tous désactivé
                resultat = 0;
            }
        }
        else {
            if (nbDesactive == 0) {
                // Cas tous active...
                resultat = 1;
            }
            else {
                // cas certains désactivés, certains actifs
                resultat = -1;
            }
        }

        return resultat;
    }
}
