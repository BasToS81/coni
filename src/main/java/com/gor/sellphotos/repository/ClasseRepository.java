package com.gor.sellphotos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.Classe;

public interface ClasseRepository extends CrudRepository<Classe, Long> {

    public final static String FIND_BY_NOM_ET_NUMERO_ECOLE = "SELECT c " +
                    "FROM Classe c LEFT JOIN c.ecole e " +
                    "WHERE c.nom = :nom and e.numeroEcole = :numeroEcole";

    @Query(FIND_BY_NOM_ET_NUMERO_ECOLE)
    public Classe findByNomEtEcole(@Param("nom") String nom, @Param("numeroEcole") String ecole);

    public Classe findByIdentifiantChiffre(String identifiantChiffre);

}
