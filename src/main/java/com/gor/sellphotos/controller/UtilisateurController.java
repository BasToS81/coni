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
	private UtilisateurRepository userRepository;

	@RequestMapping("/rest/utilisateur")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Utilisateur getUtilisateur(@RequestParam("identifiant") String identifiant) {
		LOGGER.debug("loading user {}", identifiant);
		Utilisateur utilisateur = userRepository.findByIdentifiant(identifiant);
		
		LOGGER.debug("utilisateurs {}", utilisateur);
		return utilisateur;
	}

}