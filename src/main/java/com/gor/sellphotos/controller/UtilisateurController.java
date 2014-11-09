package com.gor.sellphotos.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dao.Utilisateur;
import com.gor.sellphotos.repository.UtilisateurRepository;

/**
 *
 */
@Controller
public class UtilisateurController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurController.class);

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@RequestMapping("/rest/utilisateur")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	/**
	 * Retourne le type de l'utilisateur
	 * @param identifiant
	 * @param codeAcces
	 * @return
	 */
	public String getUtilisateur(@RequestParam("identifiant") String identifiant, @RequestParam("codeAcces") String codeAcces) {
		LOGGER.debug("loading user {}", identifiant);
		Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(identifiant);
		
		LOGGER.debug("utilisateur {}", utilisateur);
		return utilisateur.getTypeUtilisateur().getClass().getName();
	}

}