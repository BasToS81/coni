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
import com.gor.sellphotos.repository.UserRepository;

/**
 *
 */
@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/rest/utilisateur")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Utilisateur getUtilisateur(@RequestParam("nom") String nom) {
		LOGGER.debug("loading user {}", nom);
		Utilisateur utilisateur = null;
		List<Utilisateur> utilisateurs = userRepository.findByName(nom);
		if (!CollectionUtils.isEmpty(utilisateurs)) {
			LOGGER.debug("Création de l'utilisateur {}", nom);
			utilisateur = new Utilisateur();
			utilisateur.setNom(nom);
			utilisateur.setEcole("Ecole de " + nom);
			userRepository.save(utilisateur);
		} else {
			utilisateur = utilisateurs.get(0);
		}
		LOGGER.debug("utilisateurs {}", utilisateur);
		return utilisateur;
	}

}