package com.gor.sellphotos.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.repository.UtilisateurRepository;

/**
 *
 */
@Controller
public class ImportController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportController.class);

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@RequestMapping("/rest/import")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String importDonnees() {
		LOGGER.debug("import donnees");
		String resultatImport = "Non OK";
		
		File dossierImport = new File("./import/");
		if(dossierImport.exists()) {
		    
		    LOGGER.debug("dossier import existant");
		    
		    for(File directionEcole : dossierImport.listFiles()) {
		        
		        LOGGER.debug("dossier ecole : " + directionEcole.getName());
		        
		    }
		    
		    resultatImport = "OK";
		} else {
		    LOGGER.debug("dossier import non existant");
		    
		}
		
		
		LOGGER.debug("fin d'import de données");
		return resultatImport;
	}

}