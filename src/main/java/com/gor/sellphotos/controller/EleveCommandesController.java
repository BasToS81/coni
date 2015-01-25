package com.gor.sellphotos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gor.sellphotos.dao.Classe;
import com.gor.sellphotos.dao.Eleve;
import com.gor.sellphotos.dao.ModeleEtTarif;
import com.gor.sellphotos.dao.Produit;
import com.gor.sellphotos.dto.EleveDTO;
import com.gor.sellphotos.dto.ModeleEtTarifDTO;
import com.gor.sellphotos.dto.ProduitDTO;
import com.gor.sellphotos.dto.ecole.CommandeClasseSyntheseDTOEcole;
import com.gor.sellphotos.dto.ecole.CommandeEleveSyntheseDTOEcole;
import com.gor.sellphotos.dto.ecole.CommandeProduitDTOEcole;
import com.gor.sellphotos.repository.ClasseRepository;
import com.gor.sellphotos.repository.CommandeEleveRepository;
import com.gor.sellphotos.repository.CommandeFamilleRepository;
import com.gor.sellphotos.repository.CommandeProduitRepository;
import com.gor.sellphotos.repository.EleveRepository;
import com.gor.sellphotos.repository.FamilleRepository;
import com.gor.sellphotos.repository.ModeleEtTarifRepository;
import com.gor.sellphotos.repository.ProduitRepository;
import com.gor.sellphotos.repository.TvaRepository;
import com.gor.sellphotos.security.SecuritySessionData;
import com.gor.sellphotos.security.UPAWithSessionDataToken;
import com.gor.sellphotos.utils.MapperUtils;

/**
 *
 */
@Controller
public class EleveCommandesController extends AbstractRestHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EleveCommandesController.class);

    @Autowired
    private FamilleRepository familleRepository;

    @Autowired
    private EleveRepository eleveRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private CommandeFamilleRepository commandeFamilleRepository;

    @Autowired
    private CommandeEleveRepository commandeEleveRepository;

    @Autowired
    private ModeleEtTarifRepository modeleEtTarifRepository;

    @Autowired
    private CommandeProduitRepository commandeProduitRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private TvaRepository tvaRepository;

    @RequestMapping("/ws/ecole/commande/classe/getList")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommandeClasseSyntheseDTOEcole getCommandesEleveOfClasse(@RequestParam("idClasse") String identifiantChiffreClasse, Authentication authentication) {

        SecuritySessionData sessionData = ((UPAWithSessionDataToken) authentication).getSessionData();
        String identifiantUtilisateur = sessionData.getIdentifiantUtilisateur();
        Long identifiantEcole = sessionData.getIdentifiantEcole();

        LOGGER.debug("loading commandes classe {}", identifiantChiffreClasse);

        CommandeClasseSyntheseDTOEcole commandesClasseSyntheseDTO = new CommandeClasseSyntheseDTOEcole();

        ModeleEtTarif modeleEtTarif = modeleEtTarifRepository.findByIdEcole(identifiantEcole);
        commandesClasseSyntheseDTO.setModeleEtTarif(MapperUtils.convert(modeleEtTarif, ModeleEtTarifDTO.class));

        Classe classe = classeRepository.findByIdentifiantChiffre(identifiantChiffreClasse);
        commandesClasseSyntheseDTO.setNom(classe.getNom());

        List<Object[]> commandes = commandeEleveRepository.findSyntheseByIdentifiantChiffreClasse(identifiantChiffreClasse);
        Map<String, CommandeEleveSyntheseDTOEcole> commandesDTO = new HashMap<String, CommandeEleveSyntheseDTOEcole>();

        for (Object[] commandeEleve : commandes) {

            // Récupération de la commande si elle est déjà présente dans la map
            CommandeEleveSyntheseDTOEcole cmdDTO = commandesDTO.get((String) commandeEleve[1]);
            if (cmdDTO == null) {
                // création de la commande
                cmdDTO = new CommandeEleveSyntheseDTOEcole();

                Eleve eleve = eleveRepository.findByIdentifiant((String) commandeEleve[1]);
                EleveDTO eleveDTO = MapperUtils.convert(eleve, EleveDTO.class);
                cmdDTO.setEleve(eleveDTO);

                for (Produit produit : modeleEtTarif.getProduits()) {
                    Long idProduit = (Long) commandeEleve[3];

                    CommandeProduitDTOEcole cmdProduitDTO = new CommandeProduitDTOEcole();
                    ProduitDTO produitDTO = MapperUtils.convert(produit, ProduitDTO.class);
                    cmdProduitDTO.setProduit(produitDTO);

                    cmdProduitDTO.setQuantite(0);
                    cmdProduitDTO.setMontantParentHT(0);
                    cmdProduitDTO.setMontantEcoleHT(0);
                    cmdProduitDTO.setMontantParentTTC(0);
                    cmdProduitDTO.setMontantEcoleTTC(0);

                    cmdProduitDTO.setMontantParentRestantAPayerHT(0);
                    cmdProduitDTO.setMontantEcoleRestantAPayerHT(0);
                    cmdProduitDTO.setMontantParentRestantAPayerTTC(0);
                    cmdProduitDTO.setMontantEcoleRestantAPayerTTC(0);

                    if (idProduit == produit.getId()) {

                        cmdProduitDTO.setQuantite(((Long) commandeEleve[4]).intValue());
                        cmdProduitDTO.setMontantParentHT((double) commandeEleve[5]);
                        cmdProduitDTO.setMontantEcoleHT((double) commandeEleve[6]);
                        cmdProduitDTO.setMontantParentTTC((double) commandeEleve[7]);
                        cmdProduitDTO.setMontantEcoleTTC((double) commandeEleve[8]);
                        cmdProduitDTO.setMontantParentRestantAPayerHT((double) commandeEleve[9]);
                        cmdProduitDTO.setMontantEcoleRestantAPayerHT((double) commandeEleve[10]);
                        cmdProduitDTO.setMontantParentRestantAPayerTTC((double) commandeEleve[11]);
                        cmdProduitDTO.setMontantEcoleRestantAPayerTTC((double) commandeEleve[12]);

                    }

                    cmdDTO.setMontantTotalEcoleHT(cmdProduitDTO.getMontantEcoleHT());
                    cmdDTO.setMontantTotalParentHT(cmdProduitDTO.getMontantParentHT());
                    cmdDTO.setMontantTotalEcoleTTC(cmdProduitDTO.getMontantEcoleTTC());
                    cmdDTO.setMontantTotalParentTTC(cmdProduitDTO.getMontantParentTTC());
                    cmdDTO.setMontantRestantAPayerEcoleHT(cmdProduitDTO.getMontantEcoleRestantAPayerHT());
                    cmdDTO.setMontantRestantAPayerParentHT(cmdProduitDTO.getMontantParentRestantAPayerHT());
                    cmdDTO.setMontantRestantAPayerEcoleTTC(cmdProduitDTO.getMontantEcoleRestantAPayerTTC());
                    cmdDTO.setMontantRestantAPayerParentTTC(cmdProduitDTO.getMontantParentRestantAPayerTTC());

                    cmdDTO.addProduitsCommandes(cmdProduitDTO);
                }

                commandesDTO.put(eleve.getIdentifiant(), cmdDTO);
                commandesClasseSyntheseDTO.addCommandeEleveSynthese(cmdDTO);

            }
            else {
                // Mise à jour de la commande

                for (CommandeProduitDTOEcole cmdProduitDTO : cmdDTO.getProduitsCommandes()) {
                    Long idProduit = (Long) commandeEleve[3];
                    if (idProduit == cmdProduitDTO.getProduit().getId()) {

                        cmdProduitDTO.setQuantite(((Long) commandeEleve[4]).intValue());
                        cmdProduitDTO.setMontantParentHT((double) commandeEleve[5]);
                        cmdProduitDTO.setMontantEcoleHT((double) commandeEleve[6]);
                        cmdProduitDTO.setMontantParentTTC((double) commandeEleve[7]);
                        cmdProduitDTO.setMontantEcoleTTC((double) commandeEleve[8]);
                        cmdProduitDTO.setMontantParentRestantAPayerHT((double) commandeEleve[9]);
                        cmdProduitDTO.setMontantEcoleRestantAPayerHT((double) commandeEleve[10]);
                        cmdProduitDTO.setMontantParentRestantAPayerTTC((double) commandeEleve[11]);
                        cmdProduitDTO.setMontantEcoleRestantAPayerTTC((double) commandeEleve[12]);

                        cmdDTO.setMontantTotalEcoleHT(cmdProduitDTO.getMontantEcoleHT());
                        cmdDTO.setMontantTotalParentHT(cmdProduitDTO.getMontantParentHT());
                        cmdDTO.setMontantTotalEcoleTTC(cmdProduitDTO.getMontantEcoleTTC());
                        cmdDTO.setMontantTotalParentTTC(cmdProduitDTO.getMontantParentTTC());
                        cmdDTO.setMontantRestantAPayerEcoleHT(cmdProduitDTO.getMontantEcoleRestantAPayerHT());
                        cmdDTO.setMontantRestantAPayerParentHT(cmdProduitDTO.getMontantParentRestantAPayerHT());
                        cmdDTO.setMontantRestantAPayerEcoleTTC(cmdProduitDTO.getMontantEcoleRestantAPayerTTC());
                        cmdDTO.setMontantRestantAPayerParentTTC(cmdProduitDTO.getMontantParentRestantAPayerTTC());

                        break;
                    }
                }

            }

        }

        LOGGER.debug("commandes eleve de classe {}", commandesClasseSyntheseDTO);
        return commandesClasseSyntheseDTO;
    }
}
