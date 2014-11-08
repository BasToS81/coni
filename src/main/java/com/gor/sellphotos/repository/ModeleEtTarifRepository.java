package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.ModeleEtTarif;
import com.gor.sellphotos.dao.Utilisateur;

public interface ModeleEtTarifRepository extends CrudRepository<ModeleEtTarif, Long> {
	
    public final static String FIND_BY_NUMERO_ECOLE= "SELECT mt " +
                                                     "FROM ModeleEtTarif mt LEFT JOIN mt.ecole e " +
                                                     "WHERE e.numeroEcole = :numeroEcole";

    @Query(FIND_BY_NUMERO_ECOLE)
	public ModeleEtTarif findByNumeroEcole(@Param("numeroEcole") String numeroEcole);

}
