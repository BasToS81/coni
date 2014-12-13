package com.gor.sellphotos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.Famille;

public interface FamilleRepository extends CrudRepository<Famille, Long> {

    public final static String FIND_BY_IDENTIFIANT_UTILISATEUR = "SELECT f " +
                    "FROM Eleve e LEFT JOIN e.famille f " +
                    "WHERE e.identifiant = :idUtilisateur";

    @Query(FIND_BY_IDENTIFIANT_UTILISATEUR)
    public Famille findByIdentifiantUtilisateur(@Param("idUtilisateur") String idUtilisateur);

    public Famille findByIdentifiantsFraterie(String idFraterie);

}
