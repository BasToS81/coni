package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.CommandeFamille;

public interface CommandeFamilleRepository extends CrudRepository<CommandeFamille, Long> {

    public final static String COUNT_BY_ID_ELEVE = "SELECT count(cf) " + "FROM CommandeEleve ce LEFT JOIN ce.commandeFamille cf " + "LEFT JOIN ce.eleve e "
                    + "WHERE e.identifiant = :idEleve";

    public final static String FIND_BY_ID_ELEVE = "SELECT cf " + "FROM CommandeEleve ce LEFT JOIN ce.commandeFamille cf " + "LEFT JOIN ce.eleve e "
                    + "WHERE e.identifiant = :idEleve";

    public final static String FIND_BY_IDENTIFIANT_ET_ID_ELEVE_ET_ID_ECOLE = "SELECT cf FROM Eleve e , CommandeFamille cf "
                    + " INNER JOIN e.famille f "
                    + "WHERE e.identifiant = :idEleve AND cf MEMBER OF f.commandes "
                    + " AND f.ecole.id = :idEcole "
                    + " AND cf.identifiant = :idCommande ";

    public final static String FIND_VALIDATE_BY_ID_ELEVE = "SELECT cf " + "FROM CommandeEleve ce LEFT JOIN ce.commandeFamille cf " + "LEFT JOIN ce.eleve e "
                    + "WHERE e.identifiant = :idEleve and cf.statut in ('EN_ATTENTE_VALID_RESPONSABLE')";

    public final static String FIND_NOT_EN_COURS_BY_ID_ELEVE = "SELECT cf " + "FROM CommandeEleve ce LEFT JOIN ce.commandeFamille cf "
                    + "LEFT JOIN ce.eleve e " + "WHERE e.identifiant = :idEleve and cf.statut not in ('EN_COURS')";

    public final static String FIND_VALIDATE_BY_ID_ECOLE = "SELECT cf " + "FROM CommandeFamille cf "
                    + "WHERE cf.famille.ecole.id = :idEcole and cf.statut in ('EN_ATTENTE_VALID_RESPONSABLE')";

    public final static String FIND_BY_ID_CMD_ECOLE = "SELECT cf " + "FROM CommandeFamille cf LEFT JOIN cf.commandeEcole cecol "
                    + "WHERE cecol.identifiant = :idCmdEcole";

    public final static String FIND_BY_ID_CMD_ECOLE_ET_STATUT = "SELECT cf " + "FROM CommandeFamille cf LEFT JOIN cf.commandeEcole cecol "
                    + "WHERE cecol.identifiant = :idCmdEcole and cf.statut = :statut";

    public final static String FIND_BY_ID_FAMILLE = "SELECT cf " + "FROM CommandeFamille  cf LEFT JOIN cf.famille f " + "WHERE f.id = :idFamille";

    public final static String FIND_CMD_DE_TOUTE_LA_FAMILLE_BY_ID_ELEVE = "SELECT cf " + "FROM CommandeFamille cf LEFT JOIN cf.famille f "
                    + "WHERE f IN (SELECT f2 FROM Eleve e2 LEFT JOIN e2.famille f2 " + "WHERE e2.identifiant = :idEleve) " + "ORDER BY cf.identifiant desc";

    public final static String FIND_BY_ID_ECOLE_ET_STATUT_NON_PAYE = "SELECT cf " + "FROM CommandeFamille cf LEFT JOIN cf.famille f LEFT JOIN f.ecole e "
                    + "WHERE e.id = :idEcole AND cf.statutPaiement = 'NON_PAYE' ";

    public CommandeFamille findByIdentifiant(Long identifiant);

    @Query(FIND_BY_IDENTIFIANT_ET_ID_ELEVE_ET_ID_ECOLE)
    public CommandeFamille findByIdentifiantEtIdEleveEtIdEcole(@Param("idCommande") Long identifiant, @Param("idEleve") String identifiantEleve,
                    @Param("idEcole") Long identifiantEcole);

    @Query(FIND_BY_ID_ELEVE)
    public List<CommandeFamille> findByIdentifiantEleve(@Param("idEleve") String identifiantEleve);

    @Query(FIND_VALIDATE_BY_ID_ELEVE)
    public List<CommandeFamille> findValidateByIdentifiantEleve(@Param("idEleve") String identifiantEleve);

    @Query(FIND_NOT_EN_COURS_BY_ID_ELEVE)
    public List<CommandeFamille> findNotEnCoursByIdentifiantEleve(@Param("idEleve") String identifiantEleve);

    @Query(FIND_BY_ID_FAMILLE)
    public List<CommandeFamille> findByIdentifiantFamille(@Param("idFamille") String identifiantFamille);

    @Query(FIND_BY_ID_CMD_ECOLE)
    public List<CommandeFamille> findByIdCmdEcole(@Param("idCmdEcole") String idCmdEcole);

    @Query(FIND_BY_ID_CMD_ECOLE_ET_STATUT)
    public List<CommandeFamille> findByIdCmdEcoleEtStatut(@Param("idCmdEcole") String idCmdEcole, @Param("statut") String statut);

    @Query(FIND_CMD_DE_TOUTE_LA_FAMILLE_BY_ID_ELEVE)
    public List<CommandeFamille> findCmdDeTouteLaFamilleByIdEleveOrderByIdentifiantDesc(@Param("idEleve") String identifiantEleve);

    @Query(COUNT_BY_ID_ELEVE)
    public int countByIdEleve(@Param("idEleve") String identifiantEleve);

    @Query(FIND_BY_ID_ECOLE_ET_STATUT_NON_PAYE)
    public List<CommandeFamille> findByIdEcoleEtStatutNonPaye(@Param("idEcole") Long idEcole);

    @Query(FIND_VALIDATE_BY_ID_ECOLE)
    public List<CommandeFamille> findValidateByIdEcole(@Param("idEcole") Long idEcole);

}
