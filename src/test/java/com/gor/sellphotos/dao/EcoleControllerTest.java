package com.gor.sellphotos.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gor.sellphotos.SellPhotos;
import com.gor.sellphotos.controller.ImportController;
import com.gor.sellphotos.dto.ClasseSyntheseDTO;
import com.gor.sellphotos.dto.EleveSyntheseDTO;
import com.gor.sellphotos.repository.ClasseRepository;
import com.gor.sellphotos.repository.EcoleRepository;
import com.gor.sellphotos.repository.EleveRepository;

/**
 * The Class OTADaemonCallableTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SellPhotos.class)
public class EcoleControllerTest extends CommonTest {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(EcoleControllerTest.class);

    @Autowired
    ImportController importController;

    @Autowired
    EcoleRepository ecoleRepository;

    @Autowired
    EleveRepository eleveRepository;

    @Autowired
    ClasseRepository classeRepository;

    /**
     * Exception.
     * 
     * @throws InterruptedException
     * @throws SQLException
     * @throws XPathExpressionException
     *             the x path expression exception
     */
    @Test
    public void testSynthese() throws InterruptedException, SQLException {

        Ecole ecole = importController.generateEcole("testSynthese", 1, 2);

        List<Object[]> results = ecoleRepository.findSynthese(ecole.getId());

        List<ClasseSyntheseDTO> synthese = new ArrayList<ClasseSyntheseDTO>();
        for (Object[] result : results) {
            ClasseSyntheseDTO ecoleSyntheseDTO = new ClasseSyntheseDTO();
            ecoleSyntheseDTO.setNom((String) result[0]);
            ecoleSyntheseDTO.setNbEleves(((Long) result[1]).intValue());
            ecoleSyntheseDTO.setNbCommandes(((Long) result[2]).intValue());

            synthese.add(ecoleSyntheseDTO);
        }
        System.out.println(synthese);

    }

    /**
     * Exception.
     * 
     * @throws InterruptedException
     * @throws SQLException
     * @throws XPathExpressionException
     *             the x path expression exception
     */
    @Test
    public void testSyntheseClasse() throws InterruptedException, SQLException {

        Ecole ecole = importController.generateEcole("testSyntheseClasse", 1, 2);

        List<Object[]> results = ecoleRepository.findClasse(ecole.getIdentifiantChiffre());

        List<EleveSyntheseDTO> synthese = new ArrayList<EleveSyntheseDTO>();
        for (Object[] result : results) {
            EleveSyntheseDTO classeSyntheseDTO = new EleveSyntheseDTO();
            classeSyntheseDTO.setNomEleve((String) result[0]);
            classeSyntheseDTO.setNbCommandes((Long) result[1]);
            classeSyntheseDTO.setTotalVente((Double) result[2]);
            classeSyntheseDTO.setTotalAchat((Double) result[3]);

            synthese.add(classeSyntheseDTO);
        }
        System.out.println(synthese);

    }

}
