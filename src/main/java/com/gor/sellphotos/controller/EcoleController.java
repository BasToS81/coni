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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dao.Ecole;
import com.gor.sellphotos.dto.ClasseSyntheseDTO;
import com.gor.sellphotos.dto.EcoleDTO;
import com.gor.sellphotos.dto.EcoleSyntheseDTO;
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
        Ecole ecole = ecoleRepository.findByIdentifiantUtilisateur(identifiant);
        if (ecole != null) {

            ecoleDTO = MapperUtils.convert(ecole, EcoleDTO.class);

            // Ajout des éléments de sécurité
            sessionData.setIdentifiantEcole(ecole.getId());
            sessionData.setIdentifiantChiffreEcole(ecole.getIdentifiantChiffre());

        }
        LOGGER.debug("ecole {}", ecoleDTO);
        return ecoleDTO;
    }

    @RequestMapping("/ws/ecole/loadClasses/{idChClasse}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<ClasseSyntheseDTO> getSyntheseClasse(@PathVariable(value = "idChClasse") String idChClasse, Authentication authentication) {

        LOGGER.debug("loading classe = {}", idChClasse);

        EcoleDTO ecoleDTO = null;
        List<Object[]> results = ecoleRepository.findClasse(idChClasse);

        List<ClasseSyntheseDTO> synthese = new ArrayList<ClasseSyntheseDTO>();
        for (Object[] result : results) {
            ClasseSyntheseDTO classeSyntheseDTO = new ClasseSyntheseDTO();
            classeSyntheseDTO.setNomEleve((String) result[0]);
            classeSyntheseDTO.setNbCommandes((Long) result[1]);
            classeSyntheseDTO.setTotalVente((Double) result[2]);
            classeSyntheseDTO.setTotalAchat((Double) result[3]);

            synthese.add(classeSyntheseDTO);
        }

        return synthese;
    }

    @RequestMapping("/ws/ecole/synthese")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public List<EcoleSyntheseDTO> getSynthese(Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();

        LOGGER.debug("loading ecole");
        Long idEcole = sessionData.getIdentifiantEcole();

        // nom classe : CLASSE.nom where CLASSE.ecole.id = ? group by classe.id
        // nb eleves : count(eleve.id) join ELEVE
        // nb commandes : count(commande.id) join COMMANDE_ELEVE
        // total vente (prix achat) join COMMANDE_PRODUIT
        // total vente (prix vente) join PRODUIT

        List<Object[]> results = ecoleRepository.findSynthese(idEcole);

        List<EcoleSyntheseDTO> synthese = new ArrayList<EcoleSyntheseDTO>();
        for (Object[] result : results) {
            EcoleSyntheseDTO ecoleSyntheseDTO = new EcoleSyntheseDTO();
            int i = 0;
            ecoleSyntheseDTO.setIdentifiantChiffre((String) result[i++]);
            ecoleSyntheseDTO.setNomClasse((String) result[i++]);
            ecoleSyntheseDTO.setNbEleves((Long) result[i++]);
            ecoleSyntheseDTO.setNbCommandes((Long) result[i++]);
            ecoleSyntheseDTO.setTotalVente((Double) result[i++]);
            ecoleSyntheseDTO.setTotalAchat((Double) result[i++]);

            synthese.add(ecoleSyntheseDTO);
        }

        return synthese;
    }
}
