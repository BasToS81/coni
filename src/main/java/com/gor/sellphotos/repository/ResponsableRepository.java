package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.Famille;
import com.gor.sellphotos.dao.Responsable;
import com.gor.sellphotos.dao.Utilisateur;

public interface ResponsableRepository extends CrudRepository<Responsable, Long> {
    
    public final static String FIND_BY_IDENTIFIANT_UTILISATEUR= "SELECT r " +
                                                                "FROM Responsable r LEFT JOIN r.utilisateur u " +
                                                                "WHERE u.identifiant = :idUtilisateur";

    @Query(FIND_BY_IDENTIFIANT_UTILISATEUR)
    public Responsable findByIdentifiantUtilisateur(@Param("idUtilisateur") String idUtilisateur);

}
