package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.Classe;

public interface ClasseRepository extends CrudRepository<Classe, Long> {

    public final static String FIND_BY_NOM_ET_NUMERO_ECOLE = "SELECT c " +
                    "FROM Classe c LEFT JOIN c.ecole e " +
                    "WHERE c.nom = :nom and e.numeroEcole = :numeroEcole";

    public final static String FIND_BY_ID_ECOLE_ORDER_BY_NOM = "SELECT c " +
                    "FROM Classe c LEFT JOIN c.ecole ec " +
                    "WHERE ec.id = :idEcole " +
                    "ORDER BY c.nom ASC";

    @Query(FIND_BY_NOM_ET_NUMERO_ECOLE)
    public Classe findByNomEtEcole(@Param("nom") String nom, @Param("numeroEcole") String ecole);

    public Classe findByIdentifiantChiffre(String identifiantChiffre);

    @Query(FIND_BY_ID_ECOLE_ORDER_BY_NOM)
    public List<Classe> findByIdEcoleOrderByNom(@Param("idEcole") Long idEcole);

}
