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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dao.Classe;
import com.gor.sellphotos.dao.Ecole;
import com.gor.sellphotos.dao.Eleve;
import com.gor.sellphotos.dto.ClasseSyntheseDTO;
import com.gor.sellphotos.dto.EcoleDTO;
import com.gor.sellphotos.dto.EcoleSyntheseDTO;
import com.gor.sellphotos.dto.EleveDTO;
import com.gor.sellphotos.repository.ClasseRepository;
import com.gor.sellphotos.repository.CommandeEleveRepository;
import com.gor.sellphotos.repository.EcoleRepository;
import com.gor.sellphotos.repository.EleveRepository;
import com.gor.sellphotos.security.SecuritySessionData;
import com.gor.sellphotos.security.UPAWithSessionDataToken;
import com.gor.sellphotos.utils.DateUtils;
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
    private ClasseRepository classeRepository;

    @Autowired
    private CommandeEleveRepository commandeEleveRepository;

    @RequestMapping("/ws/ecole/loadDescription")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public EcoleDTO getEcoleDescription(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();

        LOGGER.debug("loading ecole description");

        Long idEcole = sessionData.getIdentifiantEcole();

        EcoleDTO ecoleDTO = null;
        Ecole ecole = null;

        if (idEcole == null) {
            String identifiant = sessionData.getIdentifiantUtilisateur();
            LOGGER.debug("Session identifiant= {}", identifiant);

            ecole = ecoleRepository.findByIdentifiantUtilisateurResponsable(identifiant);
            if (ecole == null) {
                ecole = ecoleRepository.findByIdentifiantUtilisateurEleve(identifiant);
            }
        }
        else {
            ecole = ecoleRepository.findById(idEcole);
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

    @RequestMapping("/ws/ecole/loadData")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public EcoleSyntheseDTO getSynthese(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();

        LOGGER.debug("loading ecole");
        Long idEcole = sessionData.getIdentifiantEcole();

        if (idEcole == null) {
            String identifiant = sessionData.getIdentifiantUtilisateur();
            LOGGER.debug("Session identifiant= {}", identifiant);

            EcoleDTO ecoleDTO = null;
            Ecole ecole = ecoleRepository.findByIdentifiantUtilisateurResponsable(identifiant);
            if (ecole == null) {
                ecole = ecoleRepository.findByIdentifiantUtilisateurEleve(identifiant);
            }
            if (ecole != null) {

                // Ajout des éléments de sécurité
                sessionData.setIdentifiantEcole(ecole.getId());
                sessionData.setIdentifiantChiffreEcole(ecole.getIdentifiantChiffre());

                idEcole = ecole.getId();
            }

        }

        EcoleSyntheseDTO ecoleSyntheseDTO = new EcoleSyntheseDTO();
        ecoleSyntheseDTO.setEtatActivation(getActivation(authentication));

        List<ClasseSyntheseDTO> classesSynthese = new ArrayList<ClasseSyntheseDTO>();

        // Vérification de l'état de l'activation des élèves
        List<Classe> classes = classeRepository.findByIdEcoleOrderByNom(idEcole);

        List<Object[]> syntheseNbCommandesClasse = commandeEleveRepository.countNbCommandesParClasseByIDEcole(idEcole);
        List<Object[]> syntheseMontantTotalCommandesClasse = commandeEleveRepository.sumMontantTotalParClasseByIDEcole(idEcole);
        List<Object[]> syntheseMontantsAPayerCommandesClasse = commandeEleveRepository.sumMontantRestantAPayerParClasseByIDEcole(idEcole);

        for (Classe classe : classes) {

            ClasseSyntheseDTO classeSyntheseDTO = MapperUtils.convert(classe, ClasseSyntheseDTO.class);

            classeSyntheseDTO.setNbEleves(classe.getEleves().size());

            for (int i = 0; i < syntheseNbCommandesClasse.size(); i++) {
                // Parcours de la liste avec suppression des éléments de celle ci
                if (classe.getId() == syntheseNbCommandesClasse.get(i)[0]) {
                    classeSyntheseDTO.setNbCommandes(((Long) syntheseNbCommandesClasse.get(i)[1]).intValue());
                    syntheseNbCommandesClasse.remove(i);
                    break;
                }
            }
            for (int i = 0; i < syntheseMontantTotalCommandesClasse.size(); i++) {
                // Parcours de la liste avec suppression des éléments de celle ci
                if (classe.getId() == syntheseMontantTotalCommandesClasse.get(i)[0]) {
                    classeSyntheseDTO.setMontantTotalParentHT((double) syntheseMontantTotalCommandesClasse.get(i)[1]);
                    classeSyntheseDTO.setMontantTotalEcoleHT((double) syntheseMontantTotalCommandesClasse.get(i)[2]);
                    classeSyntheseDTO.setMontantTotalParentTTC((double) syntheseMontantTotalCommandesClasse.get(i)[3]);
                    classeSyntheseDTO.setMontantTotalEcoleTTC((double) syntheseMontantTotalCommandesClasse.get(i)[4]);
                    syntheseMontantTotalCommandesClasse.remove(i);
                    break;
                }
            }
            for (int i = 0; i < syntheseMontantsAPayerCommandesClasse.size(); i++) {
                // Parcours de la liste avec suppression des éléments de celle ci
                if (classe.getId() == syntheseMontantsAPayerCommandesClasse.get(i)[0]) {
                    classeSyntheseDTO.setMontantRestantAPayerParentHT((double) syntheseMontantsAPayerCommandesClasse.get(i)[1]);
                    classeSyntheseDTO.setMontantRestantAPayerEcoleHT((double) syntheseMontantsAPayerCommandesClasse.get(i)[2]);
                    classeSyntheseDTO.setMontantRestantAPayerParentTTC((double) syntheseMontantsAPayerCommandesClasse.get(i)[3]);
                    classeSyntheseDTO.setMontantRestantAPayerEcoleTTC((double) syntheseMontantsAPayerCommandesClasse.get(i)[4]);
                    syntheseMontantsAPayerCommandesClasse.remove(i);
                    break;
                }
            }
            classesSynthese.add(classeSyntheseDTO);
        }

        ecoleSyntheseDTO.setClasses(classesSynthese);

        return ecoleSyntheseDTO;
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

    @RequestMapping("/ws/ecole/classe/getSynthese")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<EleveDTO> getSyntheseEleveFromClasse(@RequestParam("idClasse") Long idClasse, Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        Long idEcole = sessionData.getIdentifiantEcole();
        LOGGER.debug("loading synthese eleve de la classe {}", idClasse);

        List<EleveDTO> elevesDTO = new ArrayList<EleveDTO>();

        List<Eleve> eleves = null;

        List<Object[]> syntheseNbCommandesEleve = null;
        List<Object[]> syntheseMontantTotalCommandesEleve = null;
        List<Object[]> syntheseMontantsAPayerCommandesEleve = null;

        // Si l'id de la classe = 0 alors on prend tous les élèves de l'école
        if (idClasse == 0) {

            eleves = eleveRepository.findByIdEcoleOrderByNom(idEcole);

            syntheseNbCommandesEleve = commandeEleveRepository.countNbCommandesParEleveByIDEcole(idEcole);
            syntheseMontantTotalCommandesEleve = commandeEleveRepository.sumMontantTotalParEleveByIDEcole(idEcole);
            syntheseMontantsAPayerCommandesEleve = commandeEleveRepository.sumMontantRestantAPayerParEleveByIDEcole(idEcole);
        }
        else {

            eleves = eleveRepository.findByIdEcoleAndIdClasseOrderByNom(idEcole, idClasse);

            syntheseNbCommandesEleve = commandeEleveRepository.countNbCommandesParEleveByIDEcoleAndIDClasse(idEcole, idClasse);
            syntheseMontantTotalCommandesEleve = commandeEleveRepository.sumMontantTotalParEleveByIDEcoleAndIDClasse(idEcole, idClasse);
            syntheseMontantsAPayerCommandesEleve =
                            commandeEleveRepository.sumMontantRestantAPayerParEleveByIDEcoleAndIDClasse(idEcole, idClasse);
        }

        for (Eleve eleve : eleves) {

            EleveDTO eleveDTO = MapperUtils.convert(eleve, EleveDTO.class);

            eleveDTO.setNomClasse(eleve.getClasse().getNom());
            eleveDTO.setNewNom(eleveDTO.getNom());
            eleveDTO.setDateLimiteAccesFromDate(eleve.getDateLimiteAcces());
            eleveDTO.setNewDateLimiteAcces(eleveDTO.getDateLimiteAcces());

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

    @RequestMapping("/ws/ecole/classe/eleve/edition")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void updateEleve(@RequestBody EleveDTO eleveDTO, Authentication authentication) {
        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantEleve = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        // TODO : Vérifier que l'élève fait bien partie de l'école
        // Mise à jour de l'élève : nom, mot de passe, date limite d'accès

        if (eleveDTO.getIdentifiant() != null) {
            // Si l'élève est défini
            Eleve eleve = eleveRepository.findByIdentifiant(eleveDTO.getIdentifiant());

            if (eleve != null) {

                // L'elève existe

                eleve.setNom(eleveDTO.getNewNom());
                if (eleveDTO.getNewMotDePasse() != null && eleveDTO.getNewMotDePasse().length() != 0) {
                    eleve.setCodeAcces(eleveDTO.getNewMotDePasse());
                }
                eleve.setDateLimiteAcces(DateUtils.parseDate(eleveDTO.getNewDateLimiteAcces()));

                eleveRepository.save(eleve);
            }

        }

    }

}
