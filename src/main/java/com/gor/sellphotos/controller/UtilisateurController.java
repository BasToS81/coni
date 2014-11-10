package com.gor.sellphotos.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dao.Utilisateur;
import com.gor.sellphotos.dao.FamilleDto;
import com.gor.sellphotos.dao.User;
import com.gor.sellphotos.repository.UserRepository;

/**
 *
 */
@Controller
public class UtilisateurController extends AbstractRestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurController.class);

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/ws/famille/loadData/{name}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public FamilleDto getFamilleData(@PathVariable String name) {
		FamilleDto dto = new FamilleDto();
		dto.setSchool("dto");
		return dto;
	}

	@RequestMapping("/rest/utilisateur")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    /**
     * Retourne le type de l'utilisateur
     * @param identifiant
     * @param codeAcces
     * @return
     */
    public String getUtilisateur(@RequestParam("identifiant") String identifiant) {
        LOGGER.debug("loading user {}", identifiant);
        Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(identifiant);

        LOGGER.debug("utilisateur {}", utilisateur);
        String nom = null;
        if (utilisateur != null) {
            LOGGER.debug("type utilisateur {}", utilisateur.getTypeUtilisateur());
            nom = utilisateur.getTypeUtilisateur().name();
        }
        else
            nom = "";
        LOGGER.debug("utilisateur {}", utilisateur);
        return nom;
    }

}