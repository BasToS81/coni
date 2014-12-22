package com.gor.sellphotos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.ModeleEtTarif;

public interface ModeleEtTarifRepository extends CrudRepository<ModeleEtTarif, Long> {

    public final static String FIND_BY_NUMERO_ECOLE = "SELECT mt " +
                    "FROM ModeleEtTarif mt LEFT JOIN mt.ecole e " +
                    "WHERE e.numeroEcole = :numeroEcole";

    public final static String FIND_BY_ID_ECOLE = "SELECT mt " +
                    "FROM ModeleEtTarif mt LEFT JOIN mt.ecole e " +
                    "WHERE e.id = :idEcole";

    @Query(FIND_BY_NUMERO_ECOLE)
    public ModeleEtTarif findByNumeroEcole(@Param("numeroEcole") String numeroEcole);

    @Query(FIND_BY_ID_ECOLE)
    public ModeleEtTarif findByIdEcole(@Param("idEcole") Long idEcole);

}
