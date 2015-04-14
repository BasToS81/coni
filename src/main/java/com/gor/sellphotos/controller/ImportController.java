package com.gor.sellphotos.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dao.Classe;
import com.gor.sellphotos.dao.Ecole;
import com.gor.sellphotos.dao.Eleve;
import com.gor.sellphotos.dao.Famille;
import com.gor.sellphotos.dao.ModeleEtTarif;
import com.gor.sellphotos.dao.Produit;
import com.gor.sellphotos.dao.Responsable;
import com.gor.sellphotos.dao.Tva;
import com.gor.sellphotos.dto.ImportDTO;
import com.gor.sellphotos.repository.ClasseRepository;
import com.gor.sellphotos.repository.EcoleRepository;
import com.gor.sellphotos.repository.EleveRepository;
import com.gor.sellphotos.repository.FamilleRepository;
import com.gor.sellphotos.repository.ModeleEtTarifRepository;
import com.gor.sellphotos.repository.ProduitRepository;
import com.gor.sellphotos.repository.ResponsableRepository;
import com.gor.sellphotos.repository.TvaRepository;
import com.gor.sellphotos.utils.DateUtils;
import com.gor.sellphotos.utils.FileUtils;
import com.gor.sellphotos.utils.SecuriteUtils;

/**
 *
 */
@Controller
public class ImportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportController.class);

    // Dossier contenant les photos des écoles
    private static final File dossierData = new File("./src/main/webapp/data/");

    // Dossier d'import (fichiers zip)
    private static final File dossierImport = new File("./src/test/resources/import/");

    @Autowired
    private EcoleRepository ecoleRepository;

    @Autowired
    private ResponsableRepository responsableRepository;

    @Autowired
    private ModeleEtTarifRepository modeleEtTarifRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private FamilleRepository familleRepository;

    @Autowired
    private EleveRepository eleveRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private TvaRepository tvaRepository;

    @RequestMapping("/public/rest/import")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ImportDTO importDonnees() {
        LOGGER.debug("import donnees");
        String resultatImport = "Non OK";

        /* TODO mettre ce chemin en configuration */

        resultatImport = "OK";

        if (dossierImport.exists()) {
            try {
                LOGGER.debug("dossier import existant");

                // Pour chaque fichier Zip trouvé
                FilenameFilter zipFileFilter = new FilenameFilter() {

                    public boolean accept(File file, String name) {
                        return name.toLowerCase().endsWith(".zip");
                    }
                };
                for (File zipImportFile : dossierImport.listFiles(zipFileFilter)) {

                    LOGGER.debug("Zip file a traiter : {}", zipImportFile);

                    // Unzip du fichier dans un dossierTemporaire
                    FileUtils.unzip(zipImportFile.getAbsolutePath(), dossierImport.getAbsolutePath(), "");

                    // Chargement des données du dossier de l'école temporaire
                    File dossierEcoleAImporter = new File(dossierImport, zipImportFile.getName().replace(".zip", ""));
                    if (dossierEcoleAImporter.exists()) {
                        chargeConfigurationEcole(dossierEcoleAImporter);
                    }

                    // Suppression du dossier de l'école temporaire
                    FileUtils.deleteDirectory(dossierEcoleAImporter);
                }

            }
            catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                resultatImport = "KO";
            }
        }
        else {
            LOGGER.debug("dossier import non existant");

            resultatImport = "KO : dossier inexistant";
        }

        LOGGER.debug("fin d'import de données : {}", resultatImport);

        Tva tva = new Tva();
        tva.setTva(20.0);
        tva.setDateDebutValidite(DateUtils.parseDate("01/01/2014"));
        tvaRepository.save(tva);

        return new ImportDTO(resultatImport);
    }

    private void chargeConfigurationEcole(File dossierEcole) throws Exception {
        LOGGER.debug("dossier ecole : {}", dossierEcole.getName());

        // On charge la configuration de l'école
        Properties ecolePropertie = new Properties();
        FileInputStream inEcoleProperties = new FileInputStream(dossierEcole.getPath() + File.separatorChar + "ecole_donnees.properties");
        ecolePropertie.load(inEcoleProperties);
        inEcoleProperties.close();

        Ecole ecole = new Ecole();
        ecole.setNumeroEcole(ecolePropertie.getProperty("numeroEcole"));
        ecole.setReferenceTechnique(ecolePropertie.getProperty("referenceTechnique"));
        ecole.setSaison(ecolePropertie.getProperty("saison"));
        ecole.setNomEtablissement(ecolePropertie.getProperty("nomEtablissement"));
        ecole.setAdresseEtablissement(ecolePropertie.getProperty("adresseEtablissement"));
        ecole.setCodePostalEtablissement(ecolePropertie.getProperty("codePostalEtablissement"));
        ecole.setVilleEtablissement(ecolePropertie.getProperty("villeEtablissement"));
        ecole.setNomResponsablePrincipal(ecolePropertie.getProperty("nomResponsablePrincipal"));
        ecole.setDateLimiteDesCommandesEleves(DateUtils.parseDate(ecolePropertie.getProperty("dateLimiteDesCommandesEleves")));
        ecole.setDateLimiteDesCommandesEcoles(DateUtils.parseDate(ecolePropertie.getProperty("dateLimiteDesCommandesEcoles")));
        ecole.setDateLimiteAccesEcole(DateUtils.parseDate(ecolePropertie.getProperty("dateLimiteAccesEcole")));
        ecole.setDateLimiteAccesEleves(DateUtils.parseDate(ecolePropertie.getProperty("dateLimiteAccesEleves")));

        ecole.setIdentifiantChiffre(SecuriteUtils.crypterTexte(ecole.getNumeroEcole() + "-" + ecole.getSaison()));

        ecoleRepository.save(ecole);

        LOGGER.debug("sauvegarde ecole");

        // Création du dossier dans les data du site
        File dossierDataEcole = new File(dossierData, ecole.getIdentifiantChiffre());
        dossierDataEcole.mkdir();

        LOGGER.debug("Création du dossier école {} ", dossierDataEcole);

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
                    resp.setEcole(ecole);
                    resp.setDateLimiteAcces(ecole.getDateLimiteAccesEcole());
                    responsableRepository.save(resp);
                    ecole.addResponsables(resp);
                    // sauvegarde du responsable
                }
                else {
                    LOGGER.error("Le responsable de l'école {} n'est pas correctement renseigné : {}", ecole.getNumeroEcole(), listResponsables[i]);
                }
            }
        }
        /* FIN RESPONSABLES */

        /* Modele et Tarif */
        ModeleEtTarif mt = new ModeleEtTarif();

        mt.setNomReference(ecolePropertie.getProperty("Modele.nomReference"));

        LOGGER.debug("Modele : {}", mt.getNomReference());

        int nbProduits = Integer.parseInt(ecolePropertie.getProperty("Modele.nbProduits"));
        LOGGER.debug("Ajout de {} produits à réaliser", nbProduits);
        for (int i = 1; i < nbProduits + 1; i++) {
            Produit produit = new Produit();
            produit.setReference(ecolePropertie.getProperty("Modele.produit_" + i + ".reference"));
            produit.setLabel(ecolePropertie.getProperty("Modele.produit_" + i + ".label"));
            produit.setDesignation(ecolePropertie.getProperty("Modele.produit_" + i + ".designation"));
            LOGGER.debug(ecolePropertie.getProperty("Modele.produit_" + i + ".prix_parent_ht"));
            produit.setPrixParentHT(Double.parseDouble(ecolePropertie.getProperty("Modele.produit_" + i + ".prix_parent_ht")));
            produit.setPrixEcoleHT(Double.parseDouble(ecolePropertie.getProperty("Modele.produit_" + i + ".prix_ecole_ht")));
            produit.setOrdre(i);
            produitRepository.save(produit);
            mt.addProduit(produit);
            LOGGER.debug("Ajout produit principal : " + produit.getDesignation());
        }

        modeleEtTarifRepository.save(mt);
        ecole.setModeleEtTarif(mt);

        // Chargement des données des classes de l'école
        FilenameFilter classeDossierFilter = new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return name.toLowerCase().startsWith("classe_");
            }
        };

        for (File dossierClasse : dossierEcole.listFiles(classeDossierFilter)) {
            chargeConfigurationClasse(dossierClasse, ecole, dossierDataEcole);
        }

        ecoleRepository.save(ecole);

    }

    private void chargeConfigurationClasse(File dossierClasse, Ecole ecole, File dossierDataEcole) throws Exception {
        LOGGER.debug("dossier classe : " + dossierClasse.getName());

        // On charge la configuration de l'école
        Properties classePropertie = new Properties();
        FileInputStream inClasseProperties = new FileInputStream(dossierClasse.getPath() + File.separatorChar + "classe_donnees.properties");
        classePropertie.load(inClasseProperties);
        inClasseProperties.close();

        Classe classe = new Classe();
        classe.setNom(classePropertie.getProperty("nom"));

        classe.setIdentifiantChiffre(SecuriteUtils.crypterTexte(ecole.getNumeroEcole() + "-" + classe.getNom()));

        classeRepository.save(classe);

        // Création du dossier data de la classe
        File dossierDataClasse = new File(dossierDataEcole, classe.getIdentifiantChiffre());
        dossierDataClasse.mkdir();

        LOGGER.debug("Création du dossier classe {} ", dossierDataClasse);

        // Copie du groupe de la classe
        File fichierGroupeClasse = new File(dossierClasse, "classe_" + classe.getNom() + ".jpg");
        File dossierDataGroupeClasse = new File(dossierDataClasse, classe.getIdentifiantChiffre() + ".jpg");
        Files.copy(fichierGroupeClasse.toPath(), dossierDataGroupeClasse.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        LOGGER.debug("Copie de la photo de groupe :{} vers {} ", fichierGroupeClasse, dossierDataGroupeClasse);

        int nbEleves = Integer.parseInt(classePropertie.getProperty("nbEleves"));
        LOGGER.debug("nbEleves = {}", nbEleves);
        for (int i = 1; i <= nbEleves; i++) {
            String identifiant = classePropertie.getProperty("eleve_" + i + ".identifiant");
            String identifiantFamille = classePropertie.getProperty("eleve_" + i + ".identifiantsFraterie");

            Eleve eleve = new Eleve();
            eleve.setIdentifiant(identifiant);
            eleve.setCodeAcces(classePropertie.getProperty("eleve_" + i + ".codeAcces"));
            eleve.setNom(classePropertie.getProperty("eleve_" + i + ".nom"));
            if (eleve.getNom() == null || eleve.getNom().length() == 0) {
                eleve.setNom(eleve.getIdentifiant());
            }
            eleve.setDateLimiteAcces(ecole.getDateLimiteAccesEleves());

            // TODO générer l'identifiant chiffre
            eleve.setIdentifiantChiffre(SecuriteUtils.crypterTexte(ecole.getNumeroEcole() + "-" + classe.getNom() + "-" + eleve.getIdentifiant()));

            LOGGER.debug("Ajout eleve ", identifiant);

            Famille famille = null;
            if (!StringUtils.isEmpty(identifiantFamille)) {
                famille = familleRepository.findByIdentifiantsFraterie(identifiantFamille);
            }
            if (famille == null) {
                famille = new Famille();
                famille.setIdentifiantsFraterie(identifiantFamille);
                famille.setEcole(ecole);
                familleRepository.save(famille);
            }
            else {
                LOGGER.debug("Famille found : ", famille);
            }

            eleve.setFamille(famille);

            eleve.setClasse(classe);

            eleveRepository.save(eleve);

            // Copie fichier avec renommage
            File dossierPhotoEleve = new File(dossierClasse, eleve.getIdentifiant() + ".jpg");
            File dossierDataPhotoEleve = new File(dossierDataClasse, eleve.getIdentifiantChiffre() + ".jpg");
            Files.copy(dossierPhotoEleve.toPath(), dossierDataPhotoEleve.toPath(),
                            java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            LOGGER.debug("Copie de la photo :{} vers {} ", dossierPhotoEleve, dossierDataPhotoEleve);

        }

        classe.setEcole(ecole);
        classeRepository.save(classe);
    }

}
