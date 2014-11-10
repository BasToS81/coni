package com.gor.sellphotos.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dao.Ecole;
import com.gor.sellphotos.dao.ModeleEtTarif;
import com.gor.sellphotos.dao.Responsable;
import com.gor.sellphotos.repository.EcoleRepository;
import com.gor.sellphotos.repository.ModeleEtTarifRepository;
import com.gor.sellphotos.repository.ResponsableRepository;
import com.gor.sellphotos.utils.DateUtils;

/**
 *
 */
@Controller
public class ImportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportController.class);

    @Autowired
    private EcoleRepository ecoleRepository;

    @Autowired
    private ResponsableRepository responsableRepository;

    @Autowired
    private ModeleEtTarifRepository modeleEtTarifRepository;

    @RequestMapping("/rest/import")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String importDonnees() {
        LOGGER.debug("import donnees");
        String resultatImport = "Non OK";

        /* TODO mettre ce chemin en configuration */
        File dossierImport = new File("./src/test/resources/import/");

        if (dossierImport.exists()) {
            try {
                LOGGER.debug("dossier import existant");

                // Pour chaque dossier trouvé
                for (File directionEcole : dossierImport.listFiles()) {
                    LOGGER.debug("dossier ecole : " + directionEcole.getName());

                    // On charge la configuration de l'école
                    Properties ecolePropertie = new Properties();
                    ecolePropertie.load(new FileInputStream(directionEcole.getPath() + File.separatorChar + "ecole_donnees.properties"));

                    Ecole ecole = new Ecole();
                    ecole.setNumeroEcole(ecolePropertie.getProperty("numeroEcole"));
                    ecole.setSaison(ecolePropertie.getProperty("saison"));
                    ecole.setNomEtablissement(ecolePropertie.getProperty("nomEtablissement"));
                    ecole.setAdresseEtablissement(ecolePropertie.getProperty("adresseEtablissement"));
                    ecole.setCodePostalEtablissement(ecolePropertie.getProperty("codePostalEtablissement"));
                    ecole.setVilleEtablissement(ecolePropertie.getProperty("villeEtablissement"));
                    ecole.setNomResponsablePrincipal(ecolePropertie.getProperty("nomResponsablePrincipal"));
                    ecole.setDateLimiteDesCommandesEleves(DateUtils.parseDate(ecolePropertie.getProperty("dateLimiteDesCommandesEleves")));
                    ecole.setDateLimiteDesCommandesEcoles(DateUtils.parseDate(ecolePropertie.getProperty("dateLimiteDesCommandesEcoles")));
                    ecole.setDateLimiteAcces(DateUtils.parseDate(ecolePropertie.getProperty("dateLimiteAcces")));

                    ecoleRepository.save(ecole);

                    /* RESPONSABLES */
                    String donneesDesResponsables = ecolePropertie.getProperty("identifiantsResponsables");
                    if (donneesDesResponsables.length() > 0) {
                        // Parse des identifiants responsables
                        String[] listResponsables = donneesDesResponsables.split(";");

                        for (int i = 0; i < listResponsables.length; i++) {
                            // Parse des données du responsable
                            String[] listDonneesDuResponsable = listResponsables[i].split(",");
                            if (listDonneesDuResponsable.length == 3) {
                                // Création du responsable et ajout à l'école
                                Responsable resp = new Responsable();
                                resp.setNom(listDonneesDuResponsable[0]);
                                resp.setIdentifiant(listDonneesDuResponsable[1]);
                                resp.setCodeAcces(listDonneesDuResponsable[2]);
                                responsableRepository.save(resp);
                                ecole.addResponsables(resp);
                                // sauvegarde du responsable
                            }
                            else {
                                LOGGER.error("Le responsable de l'école {} n'est pas correctement renseigné : {}", ecole.getNumeroEcole(),
                                                listResponsables[i]);
                            }
                        }
                    }
                    /* FIN RESPONSABLES */

                    /* Modele et Tarif */
                    ModeleEtTarif mt = new ModeleEtTarif();
                    modeleEtTarifRepository.save(mt);
                    ecole.setModeleEtTarif(mt);
                    ecoleRepository.save(ecole);
                }

            }
            catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            resultatImport = "OK";
        }
        else {
            LOGGER.debug("dossier import non existant");
        }

        LOGGER.debug("fin d'import de données");
        return resultatImport;
    }
}
