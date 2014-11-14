package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.CommandeEleve;

public interface CommandeEleveRepository extends CrudRepository<CommandeEleve, Long> {

    public final static String FIND_BY_ID_ELEVE = "SELECT ce " +
                    "FROM CommandeEleve ce LEFT JOIN ce.eleve e " +
                    "WHERE e.identifiant = :idEleve";

    public final static String FIND_BY_ID_CMD_ECOLE = "SELECT ce " +
                    "FROM CommandeEleve ce LEFT JOIN ce.commandeEcole cecol " +
                    "WHERE cecol.identifiant = :idCmdEcole";

    public final static String FIND_BY_ID_CMD_ECOLE_ET_STATUT = "SELECT ce " +
                    "FROM CommandeEleve ce LEFT JOIN ce.commandeEcole cecol " +
                    "WHERE cecol.identifiant = :idCmdEcole and ce.statut = :statut";

    public final static String FIND_BY_ID_FAMILLE = "SELECT ce " +
                    "FROM CommandeEleve  ce LEFT JOIN ce.eleve e " +
                    "LEFT JOIN e.famille f " +
                    "WHERE f.id = :idFamille";

    public final static String FIND_CMD_DE_TOUTE_LA_FAMILLE_BY_ID_ELEVE = "SELECT ce " +
                    "FROM CommandeEleve ce LEFT JOIN ce.eleve e LEFT JOIN e.famille f " +
                    "WHERE f IN (SELECT f2 FROM Eleve e2 LEFT JOIN e2.famille f2 " +
                    "WHERE e2.identifiant = :idEleve)";

    public CommandeEleve findByIdentifiant(String identifiant);

    @Query(FIND_BY_ID_ELEVE)
    public List<CommandeEleve> findByIdentifiantEleve(@Param("idEleve") String identifiantEleve);

    @Query(FIND_BY_ID_FAMILLE)
    public List<CommandeEleve> findByIdentifiantFamille(@Param("idFamille") String identifiantFamille);

    @Query(FIND_BY_ID_CMD_ECOLE)
    public List<CommandeEleve> findByIdCmdEcole(@Param("idCmdEcole") String idCmdEcole);

    @Query(FIND_BY_ID_CMD_ECOLE_ET_STATUT)
    public List<CommandeEleve> findByIdCmdEcoleEtStatut(@Param("idCmdEcole") String idCmdEcole, @Param("statut") String statut);

    @Query(FIND_CMD_DE_TOUTE_LA_FAMILLE_BY_ID_ELEVE)
    public List<CommandeEleve> findCmdDeTouteLaFamilleByIdEleve(@Param("idEleve") String identifiantEleve);

}
