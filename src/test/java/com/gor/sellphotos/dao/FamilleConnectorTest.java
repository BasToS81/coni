package com.gor.sellphotos.dao;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gor.sellphotos.controller.FamilleController;
import com.gor.sellphotos.controller.ImportController;
import com.gor.sellphotos.dto.FamilleDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class OTADaemonCallableTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
                "classpath:spring-config-test.xml"
})
public class FamilleConnectorTest {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FamilleConnectorTest.class);

    /**
     * Exception.
     * 
     * @throws XPathExpressionException
     *             the x path expression exception
     */
    @Test
    public void getFamille() {
        LOGGER.info("Test getFamille");
        ImportController importController = new ImportController();
        importController.importDonnees();

        FamilleController familleController = new FamilleController();
        FamilleDTO f = familleController.getFamille("0001");
        LOGGER.debug("famille : " + f);

        LOGGER.info("Fin Test getFamille");
    }

}
