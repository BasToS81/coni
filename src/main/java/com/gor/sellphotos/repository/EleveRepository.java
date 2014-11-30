package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.Eleve;

public interface EleveRepository extends CrudRepository<Eleve, Long> {

    public final static String FIND_BY_ID_FAMILLE = "SELECT e " +
                    "FROM Eleve e LEFT JOIN e.famille f " +
                    "WHERE f.id = :idFamille";

    public final static String FIND_TOUTE_LA_FAMILLE_BY_ID_ELEVE = "SELECT e " +
                    "FROM Eleve e LEFT JOIN e.famille f " +
                    "WHERE f IN (SELECT f2 FROM Eleve e2 " +
                    "LEFT JOIN e2.famille f2 " +
                    "WHERE e2.identifiant = :idFamille)";

    public Eleve findByIdentifiantChiffre(String identifiantChiffre);

    @Query(FIND_BY_ID_FAMILLE)
    public List<Eleve> findByIdFamille(@Param("idFamille") Long idFamille);

    @Query(FIND_TOUTE_LA_FAMILLE_BY_ID_ELEVE)
    public List<Eleve> findTouteLaFamilleByIdEleve(@Param("idFamille") String identifiant);

    public Eleve findByIdentifiant(String identifiant);
}
