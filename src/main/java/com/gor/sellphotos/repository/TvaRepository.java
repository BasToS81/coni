package com.gor.sellphotos.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.Tva;

public interface TvaRepository extends CrudRepository<Tva, Long> {

    public final static String FIND_BY_DATE_DE_DEMANDE = "SELECT t " +
                    "FROM Tva t " +
                    "WHERE t.dateDebutValidite <= :dateDeDemande " +
                    "and ( t.dateFinValidite = NULL or t.dateFinValidite >= :dateDeDemande )";

    public final static String FIND_BY_DATE_COURANTE = "SELECT t " +
                    "FROM Tva t " +
                    "WHERE t.dateDebutValidite <= CURRENT_DATE " +
                    "and ( t.dateFinValidite = NULL or t.dateFinValidite >= CURRENT_DATE )";

    @Query(FIND_BY_DATE_DE_DEMANDE)
    public Tva findByDateDeDemande(@Param("dateDeDemande") Date dateDeDemande, @Param("dateDeDemande") Date dateDeDemandeIdentique);

    @Query(FIND_BY_DATE_COURANTE)
    public Tva findByDateCourante();
}
