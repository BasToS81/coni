package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.CommandeEcole;

public interface CommandeEcoleRepository extends CrudRepository<CommandeEcole, Long> {

    public final static String FIND_BY_ID_ECOLE = "SELECT cec " +
                    "FROM CommandeEcole cec LEFT JOIN cec.ecole e " +
                    "WHERE e.id = :idEcole";

    public final static String FIND_BY_ID_ECOLE_AND_STATUT = "SELECT cec " +
                    "FROM CommandeEcole cec LEFT JOIN cec.ecole e " +
                    "WHERE e.id = :idEcole and cec.statut=:statut";

    public CommandeEcole findByIdentifiant(String identifiant);

    @Query(FIND_BY_ID_ECOLE)
    public List<CommandeEcole> findByIdEcole(@Param("idEcole") Long idEcole);

    @Query(FIND_BY_ID_ECOLE_AND_STATUT)
    public List<CommandeEcole> findByIdEcoleEtStatut(@Param("idEcole") Long idEcole, @Param("statut") String statut);

    public List<CommandeEcole> findByStatut(String statut);

    public CommandeEcole findById(Long id);

}
