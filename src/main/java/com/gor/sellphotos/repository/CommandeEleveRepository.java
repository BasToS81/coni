package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.CommandeEleve;

public interface CommandeEleveRepository extends CrudRepository<CommandeEleve, Long> {

    public final static String COUNT_BY_ID_ELEVE = "SELECT count(ce) " + "FROM CommandeEleve ce LEFT JOIN ce.eleve e " + "WHERE e.identifiant = :idEleve";

    public final static String FIND_BY_ID_ELEVE = "SELECT ce " + "FROM CommandeEleve ce LEFT JOIN ce.eleve e " + "WHERE e.identifiant = :idEleve";

    public final static String FIND_SYNTHESE_BY_ID_CLASSE = "SELECT c.id, el.identifiant, el.nom as nomEleve, p.id as idProduit, sum(pc.quantite) as quantite, "
                    + "sum(pc.montantParentHT) as montantParent, sum(pc.montantEcoleHT) as montantEcole "
                    + "FROM "
                    + " Eleve el "
                    + " JOIN el.classe c "
                    + " LEFT JOIN el.commandes ce "
                    + " LEFT JOIN ce.produitsCommandes pc "
                    + " LEFT JOIN pc.produit p "
                    + "WHERE "
                    + " c.id = :identifiantClasse " + "GROUP BY " + " el.identifiant, p.id";

    public final static String FIND_SYNTHESE_BY_ID_CHIFFRE_CLASSE = "SELECT c.id, el.identifiant, el.nom as nomEleve, p.id as idProduit, sum(pc.quantite) as quantite, "
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
                    + " cf.statut='EN_ATTENTE_VALID_RESPONSABLE' "
                    + "GROUP BY " + " el.identifiant, p.id";

    public final static String GET_SYNTHESE_MONTANT_A_PAYER_BY_ID_CHIFFRE_CLASSE = "SELECT c.id, el.identifiant, el.nom as nomEleve, sum(ce.montantParentHT) as montantParent, sum(ce.montantEcoleHT) as montantEcole, "
                    + "sum(ce.montantParentTTC) as montantParentTTC, sum(ce.montantEcoleTTC) as montantEcoleTTC "
                    + "FROM "
                    + " Eleve el "
                    + " JOIN el.classe c "
                    + " LEFT JOIN el.commandes ce "
                    + " JOIN ce.commandeFamille cf "
                    + "WHERE "
                    + " c.identifiantChiffre = :identifiantChiffre and " + " cf.statutPaiement = 'NON_PAYE' " + "GROUP BY " + " el.identifiant";

    public final static String FIND_A_COMMANDER_BY_ID_CHIFFRE_CLASSE = "SELECT ce " + "FROM CommandeEleve ce "
                    + "WHERE ce.commandeFamille.statut in ('EN_ATTENTE_VALID_RESPONSABLE') " + "AND ce.eleve.classe.identifiantChiffre = :idChiffreClasse";

    public final static String FIND_BY_ID_FAMILLE = "SELECT ce " + "FROM CommandeEleve  ce LEFT JOIN ce.eleve e " + "LEFT JOIN e.famille f "
                    + "WHERE f.id = :idFamille";

    public final static String FIND_CMD_DE_TOUTE_LA_FAMILLE_BY_ID_ELEVE = "SELECT ce " + "FROM CommandeEleve ce LEFT JOIN ce.eleve e LEFT JOIN e.famille f "
                    + "WHERE f IN (SELECT f2 FROM Eleve e2 LEFT JOIN e2.famille f2 " + "WHERE e2.identifiant = :idEleve)";

    public final static String COUNT_NB_COMMANDES_PAR_ELEVE_BY_ID_ECOLE = "SELECT ce.eleve.id, count(ce)  " + "FROM CommandeEleve ce  "
                    + "LEFT JOIN ce.eleve.classe.ecole ec " + "WHERE ec.id = :idEcole GROUP BY ce.eleve ORDER BY ce.eleve.nom ASC";

    public final static String SUM_MONTANT_TOTAL_PAR_ELEVE_BY_ID_ECOLE = "SELECT ce.eleve.id, sum(ce.montantParentHT), sum(ce.montantEcoleHT), sum(ce.montantParentTTC), sum(ce.montantEcoleTTC)  "
                    + "FROM CommandeEleve ce LEFT JOIN ce.commandeFamille cf "
                    + "LEFT JOIN ce.eleve.classe.ecole ec "
                    + "WHERE ec.id = :idEcole GROUP BY ce.eleve ORDER BY ce.eleve.nom ASC";

    public final static String SUM_MONTANT_RESTANT_A_PAYER_PAR_ELEVE_BY_ID_ECOLE = "SELECT ce.eleve.id, sum(ce.montantParentHT), sum(ce.montantEcoleHT), sum(ce.montantParentTTC), sum(ce.montantEcoleTTC)  "
                    + "FROM CommandeEleve ce LEFT JOIN ce.commandeFamille cf "
                    + "LEFT JOIN ce.eleve.classe.ecole ec "
                    + "WHERE ec.id = :idEcole AND cf.statutPaiement = 'NON_PAYE' GROUP BY ce.eleve ORDER BY ce.eleve.classe.nom ASC";

    public final static String COUNT_NB_COMMANDES_PAR_ELEVE_BY_ID_ECOLE_AND_ID_CLASSE = "SELECT ce.eleve.id, count(ce)  " + "FROM CommandeEleve ce  "
                    + "LEFT JOIN ce.eleve.classe c " + "LEFT JOIN c.ecole ec "
                    + "WHERE ec.id = :idEcole AND c.id = :idClasse GROUP BY ce.eleve ORDER BY ce.eleve.nom ASC";

    public final static String SUM_MONTANT_TOTAL_PAR_ELEVE_BY_ID_ECOLE_AND_ID_CLASSE = "SELECT ce.eleve.id, sum(ce.montantParentHT), sum(ce.montantEcoleHT), sum(ce.montantParentTTC), sum(ce.montantEcoleTTC)  "
                    + "FROM CommandeEleve ce LEFT JOIN ce.commandeFamille cf "
                    + "LEFT JOIN ce.eleve.classe c "
                    + "LEFT JOIN c.ecole ec "
                    + "WHERE ec.id = :idEcole AND c.id = :idClasse GROUP BY ce.eleve ORDER BY ce.eleve.nom ASC";

    public final static String SUM_MONTANT_RESTANT_A_PAYER_PAR_ELEVE_BY_ID_ECOLE_AND_ID_CLASSE = "SELECT ce.eleve.id, sum(ce.montantParentHT), sum(ce.montantEcoleHT), sum(ce.montantParentTTC), sum(ce.montantEcoleTTC)  "
                    + "FROM CommandeEleve ce LEFT JOIN ce.commandeFamille cf "
                    + "LEFT JOIN ce.eleve.classe c "
                    + "LEFT JOIN c.ecole ec "
                    + "WHERE ec.id = :idEcole AND c.id = :idClasse AND cf.statutPaiement = 'NON_PAYE' GROUP BY ce.eleve ORDER BY ce.eleve.classe.nom ASC";

    public final static String COUNT_NB_COMMANDES_PAR_CLASSE_BY_ID_ECOLE = "SELECT ce.eleve.classe.id, count(ce)  " + "FROM CommandeEleve ce  "
                    + "LEFT JOIN ce.eleve.classe.ecole ec " + "WHERE ec.id = :idEcole GROUP BY ce.eleve.classe ORDER BY ce.eleve.nom ASC";

    public final static String SUM_MONTANT_TOTAL_PAR_CLASSE_BY_ID_ECOLE = "SELECT ce.eleve.classe.id, sum(ce.montantParentHT), sum(ce.montantEcoleHT), sum(ce.montantParentTTC), sum(ce.montantEcoleTTC)  "
                    + "FROM CommandeEleve ce LEFT JOIN ce.commandeFamille cf "
                    + "LEFT JOIN ce.eleve.classe.ecole ec "
                    + "WHERE ec.id = :idEcole GROUP BY ce.eleve.classe ORDER BY ce.eleve.classe.nom ASC";

    public final static String SUM_MONTANT_RESTANT_A_PAYER_PAR_CLASSE_BY_ID_ECOLE = "SELECT ce.eleve.classe.id, sum(ce.montantParentHT), sum(ce.montantEcoleHT), sum(ce.montantParentTTC), sum(ce.montantEcoleTTC)  "
                    + "FROM CommandeEleve ce LEFT JOIN ce.commandeFamille cf "
                    + "LEFT JOIN ce.eleve.classe.ecole ec "
                    + "WHERE ec.id = :idEcole AND cf.statutPaiement = 'NON_PAYE' GROUP BY ce.eleve.classe ORDER BY ce.eleve.classe.nom ASC";

    @Query(FIND_SYNTHESE_BY_ID_CLASSE)
    public List<Object[]> findSyntheseByIdentifiantClasse(@Param("identifiantClasse") String identifiantClasse);

    @Query(FIND_SYNTHESE_BY_ID_CHIFFRE_CLASSE)
    public List<Object[]> findSyntheseByIdentifiantChiffreClasse(@Param("identifiantChiffre") String identifiantChiffreClasse);

    @Query(GET_SYNTHESE_MONTANT_A_PAYER_BY_ID_CHIFFRE_CLASSE)
    public List<Object[]> getSyntheseMontantAPayerByIdentifiantChiffreClasse(@Param("identifiantChiffre") String identifiantChiffreClasse);

    // @Query(FIND_BY_ID_FAMILLE)
    // public List<CommandeEleve> findByIdentifiantFamille(@Param("idFamille") String identifiantFamille);

    @Query(FIND_A_COMMANDER_BY_ID_CHIFFRE_CLASSE)
    public List<CommandeEleve> findACommanderByIdChiffreClasse(@Param("idChiffreClasse") String idChiffreClasse);

    // @Query(FIND_CMD_DE_TOUTE_LA_FAMILLE_BY_ID_ELEVE)
    // public List<CommandeEleve> findCmdDeTouteLaFamilleByIdEleve(@Param("idEleve") String identifiantEleve);

    @Query(COUNT_BY_ID_ELEVE)
    public int countByIdEleve(@Param("idEleve") String identifiantEleve);

    @Query(COUNT_NB_COMMANDES_PAR_ELEVE_BY_ID_ECOLE)
    public List<Object[]> countNbCommandesParEleveByIDEcole(@Param("idEcole") Long idEcole);

    @Query(SUM_MONTANT_TOTAL_PAR_ELEVE_BY_ID_ECOLE)
    public List<Object[]> sumMontantTotalParEleveByIDEcole(@Param("idEcole") Long idEcole);

    @Query(SUM_MONTANT_RESTANT_A_PAYER_PAR_ELEVE_BY_ID_ECOLE)
    public List<Object[]> sumMontantRestantAPayerParEleveByIDEcole(@Param("idEcole") Long idEcole);

    @Query(COUNT_NB_COMMANDES_PAR_ELEVE_BY_ID_ECOLE_AND_ID_CLASSE)
    public List<Object[]> countNbCommandesParEleveByIDEcoleAndIDClasse(@Param("idEcole") Long idEcole, @Param("idClasse") Long idClasse);

    @Query(SUM_MONTANT_TOTAL_PAR_ELEVE_BY_ID_ECOLE_AND_ID_CLASSE)
    public List<Object[]> sumMontantTotalParEleveByIDEcoleAndIDClasse(@Param("idEcole") Long idEcole, @Param("idClasse") Long idClasse);

    @Query(SUM_MONTANT_RESTANT_A_PAYER_PAR_ELEVE_BY_ID_ECOLE_AND_ID_CLASSE)
    public List<Object[]> sumMontantRestantAPayerParEleveByIDEcoleAndIDClasse(@Param("idEcole") Long idEcole, @Param("idClasse") Long idClasse);

    @Query(COUNT_NB_COMMANDES_PAR_CLASSE_BY_ID_ECOLE)
    public List<Object[]> countNbCommandesParClasseByIDEcole(@Param("idEcole") Long idEcole);

    @Query(SUM_MONTANT_TOTAL_PAR_CLASSE_BY_ID_ECOLE)
    public List<Object[]> sumMontantTotalParClasseByIDEcole(@Param("idEcole") Long idEcole);

    @Query(SUM_MONTANT_RESTANT_A_PAYER_PAR_CLASSE_BY_ID_ECOLE)
    public List<Object[]> sumMontantRestantAPayerParClasseByIDEcole(@Param("idEcole") Long idEcole);
}
