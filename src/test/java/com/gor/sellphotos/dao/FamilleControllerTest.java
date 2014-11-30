package com.gor.sellphotos.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gor.sellphotos.controller.FamilleCommandesController;
import com.gor.sellphotos.controller.FamilleController;
import com.gor.sellphotos.controller.ImportController;
import com.gor.sellphotos.dto.CommandeEleveDTO;
import com.gor.sellphotos.dto.CommandeProduitDTO;
import com.gor.sellphotos.dto.FamilleDTO;

/**
 * The Class OTADaemonCallableTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
                "classpath:spring-config-test.xml"
})
public class FamilleControllerTest {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FamilleControllerTest.class);

    @Autowired
    ImportController importController;

    @Autowired
    FamilleController familleController;

    @Autowired
    FamilleCommandesController familleCommandesController;

    /**
     * Exception.
     * 
     * @throws XPathExpressionException
     *             the x path expression exception
     */
    @Before
    public void getFamille() {
        LOGGER.info("Test getFamille");
        importController.importDonnees();

        FamilleDTO f = familleController.getFamille("0001");
        LOGGER.debug("famille : " + f);

        assertEquals("Ecole numéro 1", f.getNomEcole());
        assertEquals(0, f.getIdFamille());

        LOGGER.info("Fin Test getFamille");
    }

    /**
     * Exception.
     * 
     * @throws XPathExpressionException
     *             the x path expression exception
     */
    @Test
    public void getCommandesAndCreation() {
        LOGGER.info("Test getCommandesAndCreation");

        List<CommandeEleveDTO> cmds = familleCommandesController.getCommandesFamille("0001");

        assertEquals(0, cmds.size());

        CommandeEleveDTO cmd = familleCommandesController.createCommandeEleve("0001", "0001", true);

        cmds = familleCommandesController.getCommandesFamille("0001");

        assertEquals(1, cmds.size());

        LOGGER.info("Fin Test getCommandesAndCreation");
    }

    /**
     * Exception.
     * 
     * @throws XPathExpressionException
     *             the x path expression exception
     */
    @Test
    public void saveCommande() {
        LOGGER.info("Test saveCommande");

        CommandeEleveDTO cmd = familleCommandesController.createCommandeEleve("0001", "0001", true);

        List<CommandeProduitDTO> produits = cmd.getProduitsCommandes();
        produits.get(0).setQuantite(2);
        produits.get(1).setQuantite(4);

        familleCommandesController.saveCommandeEleve(cmd.getIdentifiant(), "0001", produits);

        List<CommandeEleveDTO> cmds = familleCommandesController.getCommandesFamille("0001");
        assertEquals(2, cmds.get(0).getProduitsCommandes().size());
        assertEquals(2, cmds.get(0).getProduitsCommandes().get(0).getQuantite());

        produits.get(0).setQuantite(5);
        produits.remove(1);

        familleCommandesController.saveCommandeEleve(cmd.getIdentifiant(), "0001", produits);

        cmds = familleCommandesController.getCommandesFamille("0001");
        assertEquals(1, cmds.get(0).getProduitsCommandes().size());
        assertEquals(5, cmds.get(0).getProduitsCommandes().get(0).getQuantite());

        LOGGER.info("Fin Test saveCommande");
    }

    /**
     * Exception.
     * 
     * @throws XPathExpressionException
     *             the x path expression exception
     */
    @Test
    public void deleteCommande() {
        LOGGER.info("Test deleteCommande");

        CommandeEleveDTO cmd = familleCommandesController.createCommandeEleve("0001", "0001", true);

        List<CommandeProduitDTO> produits = cmd.getProduitsCommandes();
        produits.get(0).setQuantite(2);
        produits.get(1).setQuantite(4);

        familleCommandesController.saveCommandeEleve(cmd.getIdentifiant(), "0001", produits);

        List<CommandeEleveDTO> cmds = familleCommandesController.getCommandesFamille("0001");
        assertEquals(2, cmds.get(0).getProduitsCommandes().size());
        assertEquals(2, cmds.get(0).getProduitsCommandes().get(0).getQuantite());

        produits.get(0).setQuantite(5);
        produits.remove(1);

        familleCommandesController.deleteCommandeEleve(cmd.getIdentifiant());

        cmds = familleCommandesController.getCommandesFamille("0001");
        assertEquals(0, cmds.size());

        LOGGER.info("Fin Test deleteCommande");
    }
}
