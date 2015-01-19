package com.gor.sellphotos.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.Arrays;
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
import com.gor.sellphotos.dao.CommandeEleve;
import com.gor.sellphotos.dao.CommandeProduit;
import com.gor.sellphotos.dao.Ecole;
import com.gor.sellphotos.dao.Eleve;
import com.gor.sellphotos.dao.Famille;
import com.gor.sellphotos.dao.ModeleEtTarif;
import com.gor.sellphotos.dao.Produit;
import com.gor.sellphotos.dao.Responsable;
import com.gor.sellphotos.dao.Tva;
import com.gor.sellphotos.dto.ImportDTO;
import com.gor.sellphotos.repository.ClasseRepository;
import com.gor.sellphotos.repository.CommandeEleveRepository;
import com.gor.sellphotos.repository.EcoleRepository;
import com.gor.sellphotos.repository.EleveRepository;
import com.gor.sellphotos.repository.FamilleRepository;
import com.gor.sellphotos.repository.ModeleEtTarifRepository;
import com.gor.sellphotos.repository.ProduitRepository;
import com.gor.sellphotos.repository.ResponsableRepository;
import com.gor.sellphotos.repository.TvaRepository;
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
        File dossierImport = new File("./src/test/resources/import/");

        resultatImport = "OK";

        if (dossierImport.exists()) {
            try {
                LOGGER.debug("dossier import existant");

                // Pour chaque dossier trouvé
                for (File dossierEcole : dossierImport.listFiles()) {
                    chargeConfigurationEcole(dossierEcole);
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

        // generateEcole("SGL", 4, 10);

        Tva tva = new Tva();
        tva.setTva(20.0);
        tva.setDateDebutValidite(DateUtils.parseDate("01/01/2014"));
        tvaRepository.save(tva);

        return new ImportDTO(resultatImport);
    }

    private void chargeConfigurationEcole(File dossierEcole) throws Exception {
        LOGGER.debug("dossier ecole : " + dossierEcole.getName());

        // On charge la configuration de l'école
        Properties ecolePropertie = new Properties();
        ecolePropertie.load(new FileInputStream(dossierEcole.getPath() + File.separatorChar + "ecole_donnees.properties"));

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
                    resp.setEcole(ecole);
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
            produit.setIdentifiant(ecolePropertie.getProperty("Modele.produit_" + i + ".identifiant"));
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
            chargeConfigurationClasse(dossierClasse, ecole);
        }

        ecoleRepository.save(ecole);

    }

    private void chargeConfigurationClasse(File dossierClasse, Ecole ecole) throws Exception {
        LOGGER.debug("dossier classe : " + dossierClasse.getName());

        // On charge la configuration de l'école
        Properties classePropertie = new Properties();
        classePropertie.load(new FileInputStream(dossierClasse.getPath() + File.separatorChar + "classe_donnees.properties"));

        Classe classe = new Classe();
        classe.setNom(classePropertie.getProperty("nom"));

        classeRepository.save(classe);

        int nbEleves = Integer.parseInt(classePropertie.getProperty("nbEleves"));
        LOGGER.debug("nbEleves = {}", nbEleves);
        for (int i = 1; i <= nbEleves; i++) {
            String identifiant = classePropertie.getProperty("eleve_" + i + ".identifiant");
            String identifiantFamille = classePropertie.getProperty("eleve_" + i + ".identifiantsFraterie");

            Eleve eleve = new Eleve();
            eleve.setIdentifiant(identifiant);
            eleve.setCodeAcces(classePropertie.getProperty("eleve_" + i + ".codeAcces"));
            eleve.setNom(classePropertie.getProperty("eleve_" + i + ".nom"));
            eleve.setDateLimiteAcces(ecole.getDateLimiteAcces());

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

        }

        classe.setEcole(ecole);
        classeRepository.save(classe);
    }

    @Autowired
    CommandeEleveRepository commandeEleveRepository;

    protected Produit PRODUIT_PHOTO_INDIVID;

    protected Produit PRODUIT_PHOTOS_GROUPE;

    protected void initProduits() {
        Produit produit = produitRepository.findByIdentifiant("PHOTO_INDIVID");
        if (produit == null) {
            produit = new Produit();
            produit.setDesignation("Photo eleve");
            produit.setIdentifiant("PHOTO_INDIVID");
            produit.setPrixEcoleHT(10.0);
            produit.setPrixParentHT(20.0);
            produit.setOrdre(1);
            produitRepository.save(produit);
        }
        PRODUIT_PHOTO_INDIVID = produit;

        produit = produitRepository.findByIdentifiant("PHOTO_GROUPE");
        if (produit == null) {
            produit = new Produit();
            produit.setDesignation("Photo groupe");
            produit.setIdentifiant("PHOTO_GROUPE");
            produit.setPrixEcoleHT(5.0);
            produit.setPrixParentHT(15.0);
            produit.setOrdre(1);
            produitRepository.save(produit);
        }
        PRODUIT_PHOTOS_GROUPE = produit;

    }

    public Ecole generateEcole(String prefix, int nbClasse, int nbEleve) {

        initProduits();

        Ecole ecole = new Ecole();
        ecoleRepository.save(ecole);

        Responsable resp = new Responsable();
        resp.setNom("aa");
        resp.setIdentifiant("aa");
        resp.setCodeAcces("aa");
        resp.setEcole(ecole);
        responsableRepository.save(resp);

        ecole.addResponsables(resp);
        ecoleRepository.save(ecole);

        for (int i = 0; i < nbClasse; i++) {
            Classe classe = new Classe();
            classe.setNom("Classe" + "_" + prefix + "_" + i);
            classe.setIdentifiantChiffre("C" + prefix + "_" + i);
            classe.setEcole(ecole);
            classeRepository.save(classe);

            for (int j = 0; j < nbEleve; j++) {
                Eleve eleve = new Eleve();
                eleve.setNom("Eleve" + "_" + nbEleve + "_" + j);
                eleve.setClasse(classe);
                eleveRepository.save(eleve);

                // Ajoute 1 commande tous les 2 élèves
                // if (j % 2 == 0) {
                CommandeEleve commande = new CommandeEleve();
                commande.setEleve(eleve);
                commande.setMontantEcoleHT(10.0);
                commande.setMontantParentHT(15.0);
                CommandeProduit commandeProduit1 = new CommandeProduit();
                commandeProduit1.setMontantEcoleHT(PRODUIT_PHOTO_INDIVID.getPrixEcoleHT());
                commandeProduit1.setMontantParentHT(PRODUIT_PHOTO_INDIVID.getPrixParentHT());
                commandeProduit1.setQuantite(1);
                commandeProduit1.setProduit(PRODUIT_PHOTO_INDIVID);
                commandeProduit1.setCommandeEleve(commande);

                CommandeProduit commandeProduit2 = new CommandeProduit();
                commandeProduit2.setMontantEcoleHT(PRODUIT_PHOTOS_GROUPE.getPrixEcoleHT());
                commandeProduit2.setMontantParentHT(PRODUIT_PHOTOS_GROUPE.getPrixParentHT());
                commandeProduit2.setQuantite(1);
                commandeProduit2.setProduit(PRODUIT_PHOTOS_GROUPE);
                commandeProduit2.setCommandeEleve(commande);

                commande.setProduitsCommandes(Arrays.asList(commandeProduit1, commandeProduit2));

                commandeEleveRepository.save(commande);
            }
        }
        return ecole;
    }

}
