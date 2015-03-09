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

    public final static String FIND_BY_ID_ECOLE = "SELECT e " +
                    "FROM Eleve e LEFT JOIN e.classe c LEFT JOIN c.ecole ec " +
                    "WHERE ec.id = :idEcole";

    public final static String FIND_BY_ID_ECOLE_ORDER_BY_NOM = "SELECT e " +
                    "FROM Eleve e LEFT JOIN e.classe c LEFT JOIN c.ecole ec " +
                    "WHERE ec.id = :idEcole " +
                    "ORDER BY e.nom ASC";

    public final static String FIND_BY_ID_ECOLE_AND_ID_CLASSE_ORDER_BY_NOM = "SELECT e " +
                    "FROM Eleve e LEFT JOIN e.classe c LEFT JOIN c.ecole ec " +
                    "WHERE ec.id = :idEcole and c.id = :idClasse " +
                    "ORDER BY e.nom ASC";

    public final static String FIND_BY_ID_ECOLE_ORDER_BY_CLASSE = "SELECT e " +
                    "FROM Eleve e LEFT JOIN e.classe c LEFT JOIN c.ecole ec " +
                    "WHERE ec.id = :idEcole " +
                    "ORDER BY c.nom, e.nom ASC";

    public Eleve findByIdentifiantChiffre(String identifiantChiffre);

    @Query(FIND_BY_ID_FAMILLE)
    public List<Eleve> findByIdFamille(@Param("idFamille") Long idFamille);

    @Query(FIND_TOUTE_LA_FAMILLE_BY_ID_ELEVE)
    public List<Eleve> findTouteLaFamilleByIdEleve(@Param("idFamille") String identifiant);

    public Eleve findByIdentifiant(String identifiant);

    @Query(FIND_BY_ID_ECOLE)
    public List<Eleve> findByIdEcole(@Param("idEcole") Long idEcole);

    @Query(FIND_BY_ID_ECOLE_ORDER_BY_NOM)
    public List<Eleve> findByIdEcoleOrderByNom(@Param("idEcole") Long idEcole);

    @Query(FIND_BY_ID_ECOLE_ORDER_BY_CLASSE)
    public List<Eleve> findByIdEcoleOrderByClasse(@Param("idEcole") Long idEcole);

    @Query(FIND_BY_ID_ECOLE_AND_ID_CLASSE_ORDER_BY_NOM)
    public List<Eleve> findByIdEcoleAndIdClasseOrderByNom(@Param("idEcole") Long idEcole, @Param("idClasse") Long idClasse);

}
