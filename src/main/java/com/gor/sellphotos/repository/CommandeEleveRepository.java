package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.CommandeEleve;

public interface CommandeEleveRepository extends CrudRepository<CommandeEleve, Long> {

    public final static String COUNT_BY_ID_ELEVE = "SELECT count(ce) " +
                    "FROM CommandeEleve ce LEFT JOIN ce.eleve e " +
                    "WHERE e.identifiant = :idEleve";

    public final static String FIND_BY_ID_ELEVE = "SELECT ce " +
                    "FROM CommandeEleve ce LEFT JOIN ce.eleve e " +
                    "WHERE e.identifiant = :idEleve";

    public final static String FIND_SYNTHESE_BY_ID_CLASSE =
                    "SELECT c.id, el.identifiant, el.nom as nomEleve, p.id as idProduit, sum(pc.quantite) as quantite, "
                                    + "sum(pc.montantParentHT) as montantParent, sum(pc.montantEcoleHT) as montantEcole "
                                    + "FROM "
                                    + " Eleve el "
                                    + " JOIN el.classe c "
                                    + " LEFT JOIN el.commandes ce "
                                    + " LEFT JOIN ce.produitsCommandes pc "
                                    + " LEFT JOIN pc.produit p "
                                    + "WHERE "
                                    + " c.id = :identifiantClasse "
                                    + "GROUP BY "
                                    + " el.identifiant, p.id";

    public final static String FIND_SYNTHESE_BY_ID_CHIFFRE_CLASSE =
                    "SELECT c.id, el.identifiant, el.nom as nomEleve, p.id as idProduit, sum(pc.quantite) as quantite, "
                                    + "sum(pc.montantParentHT) as montantParent, sum(pc.montantEcoleHT) as montantEcole, "
                                    + "sum(pc.montantParentTTC) as montantParentTTC, sum(pc.montantEcoleTTC) as montantEcoleTTC "
                                    + "FROM "
                                    + " Eleve el "
                                    + " JOIN el.classe c "
                                    + " LEFT JOIN el.commandes ce "
                                    + " JOIN ce.commandeFamille cf "
                                    + " LEFT JOIN ce.produitsCommandes pc "
                                    + " JOIN pc.produit p "
                                    + "WHERE "
                                    + " c.identifiantChiffre = :identifiantChiffre and "
                                    + " cf.statut in ('EN_COURS', 'EN_ATTENTE_PAYEMENT') "
                                    + "GROUP BY "
                                    + " el.identifiant, p.id";

    public final static String FIND_BY_ID_CMD_ECOLE = "SELECT ce " +
                    "FROM CommandeEleve ce LEFT JOIN ce.commandeFamille cf " +
                    "LEFT JOIN cf.commandeEcole cecol " +
                    "WHERE cecol.identifiant = :idCmdEcole";

    public final static String FIND_BY_ID_FAMILLE = "SELECT ce " +
                    "FROM CommandeEleve  ce LEFT JOIN ce.eleve e " +
                    "LEFT JOIN e.famille f " +
                    "WHERE f.id = :idFamille";

    public final static String FIND_CMD_DE_TOUTE_LA_FAMILLE_BY_ID_ELEVE = "SELECT ce " +
                    "FROM CommandeEleve ce LEFT JOIN ce.eleve e LEFT JOIN e.famille f " +
                    "WHERE f IN (SELECT f2 FROM Eleve e2 LEFT JOIN e2.famille f2 " +
                    "WHERE e2.identifiant = :idEleve)";

    @Query(FIND_SYNTHESE_BY_ID_CLASSE)
    public List<Object[]> findSyntheseByIdentifiantClasse(@Param("identifiantClasse") String identifiantClasse);

    @Query(FIND_SYNTHESE_BY_ID_CHIFFRE_CLASSE)
    public List<Object[]> findSyntheseByIdentifiantChiffreClasse(@Param("identifiantChiffre") String identifiantChiffreClasse);

    // @Query(FIND_BY_ID_FAMILLE)
    // public List<CommandeEleve> findByIdentifiantFamille(@Param("idFamille") String identifiantFamille);

    // @Query(FIND_BY_ID_CMD_ECOLE)
    // public List<CommandeEleve> findByIdCmdEcole(@Param("idCmdEcole") String idCmdEcole);

    // @Query(FIND_CMD_DE_TOUTE_LA_FAMILLE_BY_ID_ELEVE)
    // public List<CommandeEleve> findCmdDeTouteLaFamilleByIdEleve(@Param("idEleve") String identifiantEleve);

    @Query(COUNT_BY_ID_ELEVE)
    public int countByIdEleve(@Param("idEleve") String identifiantEleve);

}
