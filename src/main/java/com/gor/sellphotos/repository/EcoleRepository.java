package com.gor.sellphotos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.Ecole;

public interface EcoleRepository extends CrudRepository<Ecole, Long> {
	
    public final static String FIND_BY_IDENTIFIANT_UTILISATEUR = "SELECT e " + "FROM Ecole e, Responsable r "
                    + "WHERE r.identifiant = :idUtilisateur and r.ecole.id = e.id";

    @Query(FIND_BY_IDENTIFIANT_UTILISATEUR)
    public Ecole findByIdentifiantUtilisateur(@Param("idUtilisateur") String idUtilisateur);

    public Ecole findByNumeroEcole(String numeroEcole);


}
