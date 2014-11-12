package com.gor.sellphotos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dao.FamilleDto;
import com.gor.sellphotos.dao.Utilisateur;
import com.gor.sellphotos.repository.UtilisateurRepository;

/**
 *
 */
@Controller
public class UtilisateurController extends AbstractRestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurController.class);

	@Autowired
	private UtilisateurRepository utilisateurRepository;

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
        String nom = "";

        try {
            Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(identifiant);

            LOGGER.debug("utilisateur {}", utilisateur);

            if (utilisateur != null) {
                LOGGER.debug("type utilisateur {}", utilisateur.getTypeUtilisateur());
                nom = utilisateur.getTypeUtilisateur().name();
                LOGGER.debug("nom type utilisateur {}", nom);
            }

            LOGGER.debug("utilisateur {}", utilisateur);
        }
        catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        return nom;
    }

}