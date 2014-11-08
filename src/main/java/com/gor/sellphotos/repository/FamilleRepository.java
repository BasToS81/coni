package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.Famille;
import com.gor.sellphotos.dao.Utilisateur;

public interface FamilleRepository extends CrudRepository<Famille, Long> {
	
    public final static String FIND_BY_IDENTIFIANT_UTILISATEUR= "SELECT f " +
                                                                "FROM Famille f LEFT JOIN f.utilisateur u " +
                                                                "WHERE u.identifiant = :idUtilisateur";
    
    @Query(FIND_BY_IDENTIFIANT_UTILISATEUR)
	public Famille findByIdentifiantUtilisateur(@Param("idUtilisateur") String idUtilisateur);

}
