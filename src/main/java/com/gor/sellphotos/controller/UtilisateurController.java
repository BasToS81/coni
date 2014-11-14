package com.gor.sellphotos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dto.EcoleDTO;
import com.gor.sellphotos.dto.FamilleDTO;
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
    public FamilleDTO getFamilleData(@PathVariable String name) {
        FamilleDTO dto = new FamilleDTO();
        dto.setNomEcole("ecole de " + name);
		return dto;
	}

    @RequestMapping("/ws/ecole/loadData/{name}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public EcoleDTO getEcoleData(@PathVariable String name) {
        EcoleDTO dto = new EcoleDTO();
        dto.setNomEtablissement("etablissement " + name);
        return dto;
    }


}