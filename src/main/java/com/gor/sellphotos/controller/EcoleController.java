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

import com.gor.sellphotos.dao.Ecole;
import com.gor.sellphotos.dao.Eleve;
import com.gor.sellphotos.dto.EcoleDTO;
import com.gor.sellphotos.dto.EleveDTO;
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

    @RequestMapping("/ws/ecole/eleve/getSynthese")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<EleveDTO> getSyntheseEleve(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();

        LOGGER.debug("loading ecole");
        Long idEcole = sessionData.getIdentifiantEcole();

        List<EleveDTO> elevesDTO = new ArrayList<EleveDTO>();

        // Vérification de l'état de l'activation des élèves
        List<Eleve> eleves = eleveRepository.findByIdEcoleOrderByNom(idEcole);

        List<Object[]> syntheseNbCommandesEleve = commandeEleveRepository.countNbCommandesParEleveByIDEcole(idEcole);
        List<Object[]> syntheseMontantTotalCommandesEleve = commandeEleveRepository.sumMontantTotalParEleveByIDEcole(idEcole);
        List<Object[]> syntheseMontantsAPayerCommandesEleve = commandeEleveRepository.sumMontantRestantAPayerParEleveByIDEcole(idEcole);

        for (Eleve eleve : eleves) {

            EleveDTO eleveDTO = MapperUtils.convert(eleve, EleveDTO.class);

            eleveDTO.setNomClasse(eleve.getClasse().getNom());

            for (int i = 0; i < syntheseNbCommandesEleve.size(); i++) {
                // Parcours de la liste avec suppression des éléments de celle ci
                if (eleve.getId() == syntheseNbCommandesEleve.get(i)[0]) {
                    eleveDTO.setNbCommandes(((Long) syntheseNbCommandesEleve.get(i)[1]).intValue());
                    syntheseNbCommandesEleve.remove(i);
                    break;
                }
            }
            for (int i = 0; i < syntheseMontantTotalCommandesEleve.size(); i++) {
                // Parcours de la liste avec suppression des éléments de celle ci
                if (eleve.getId() == syntheseMontantTotalCommandesEleve.get(i)[0]) {
                    eleveDTO.setMontantTotalParentHT((double) syntheseMontantTotalCommandesEleve.get(i)[1]);
                    eleveDTO.setMontantTotalEcoleHT((double) syntheseMontantTotalCommandesEleve.get(i)[2]);
                    eleveDTO.setMontantTotalParentTTC((double) syntheseMontantTotalCommandesEleve.get(i)[3]);
                    eleveDTO.setMontantTotalEcoleTTC((double) syntheseMontantTotalCommandesEleve.get(i)[4]);
                    syntheseMontantTotalCommandesEleve.remove(i);
                    break;
                }
            }
            for (int i = 0; i < syntheseMontantsAPayerCommandesEleve.size(); i++) {
                // Parcours de la liste avec suppression des éléments de celle ci
                if (eleve.getId() == syntheseMontantsAPayerCommandesEleve.get(i)[0]) {
                    eleveDTO.setMontantRestantAPayerParentHT((double) syntheseMontantsAPayerCommandesEleve.get(i)[1]);
                    eleveDTO.setMontantRestantAPayerEcoleHT((double) syntheseMontantsAPayerCommandesEleve.get(i)[2]);
                    eleveDTO.setMontantRestantAPayerParentTTC((double) syntheseMontantsAPayerCommandesEleve.get(i)[3]);
                    eleveDTO.setMontantRestantAPayerEcoleTTC((double) syntheseMontantsAPayerCommandesEleve.get(i)[4]);
                    syntheseMontantsAPayerCommandesEleve.remove(i);
                    break;
                }
            }
            elevesDTO.add(eleveDTO);
        }

        return elevesDTO;
    }
}
