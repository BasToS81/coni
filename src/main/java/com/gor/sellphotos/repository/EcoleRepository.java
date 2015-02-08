package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.Ecole;

public interface EcoleRepository extends CrudRepository<Ecole, Long> {

    // @formatter:off
    public final static String FIND_BY_IDENTIFIANT_UTILISATEUR_RESPONSABLE =
                    "SELECT e1 "
                                    + "FROM "
                                    + " Responsable r "
                                    + " JOIN r.ecole e1 "
                                    + "WHERE "
                                    + " r.identifiant = :idUtilisateur ";

    public final static String FIND_BY_IDENTIFIANT_UTILISATEUR_ELEVE =
                    "SELECT e2 "
                                    + "FROM "
                                    + " Eleve el "
                                    + " JOIN el.famille f "
                                    + " JOIN f.ecole e2 "
                                    + "WHERE "
                                    + " el.identifiant = :idUtilisateur ";

    public final static String FIND_SYNTHESE =
                    "SELECT c.identifiantChiffre, c.nom as nomClasse, count(el.id) as nbEleves, count(ce.id) as nbCommandes, "
                                    + "sum(p.prixParentHT) as totalVente, sum(p.prixEcoleHT) as totalAchat "
                                    + "FROM "
                                    + " Classe c "
                                    + " join c.eleves el "
                                    + " left join el.commandes ce "
                                    + " left join ce.produitsCommandes pc "
                                    + " left join pc.produit p "
                                    + "WHERE "
                                    + " ((ce.id is null) or (ce.eleve.id = el.id))"
                                    + " and c.ecole.id = ?"
                                    + "GROUP BY "
                                    + " c.identifiantChiffre, c.nom";

    public final static String FIND_SYNTHESE_CLASSES =
                    "SELECT el.nom as nomClasse, count(ce.id) as nbCommandes, "
                                    + "sum(p.prixParentHT) as totalVente, sum(p.prixEcoleHT) as totalAchat "
                                    + "FROM "
                                    + " Eleve el "
                                    + " join el.classe c "
                                    + " left join el.commandes ce "
                                    + " left join ce.produitsCommandes pc "
                                    + " left join pc.produit p "
                                    + "WHERE "
                                    + " ((ce.id is null) or (ce.eleve.id = el.id))"
                                    + " and c.identifiantChiffre = ?"
                                    + "GROUP BY "
                                    + " el.nom";

    public final static String FIND_ETAT_ACTIVATION_ACTIVE =
                    "SELECT count(el) "
                                    + "FROM Eleve el "
                                    + " JOIN el.classe c "
                                    + " JOIN c.ecole ec "
                                    + " WHERE ec.id = :idEcole and el.dateLimiteAcces >= CURRENT_DATE";

    public final static String FIND_ETAT_ACTIVATION_DESACTIVE =
                    "SELECT count(el) "
                                    + "FROM Eleve el "
                                    + " JOIN el.classe c "
                                    + " JOIN c.ecole ec "
                                    + " WHERE ec.id = :idEcole and el.dateLimiteAcces < CURRENT_DATE";

    // @formatter:on

    @Query(FIND_BY_IDENTIFIANT_UTILISATEUR_RESPONSABLE)
    public Ecole findByIdentifiantUtilisateurResponsable(@Param("idUtilisateur") String idUtilisateur);

    @Query(FIND_BY_IDENTIFIANT_UTILISATEUR_ELEVE)
    public Ecole findByIdentifiantUtilisateurEleve(@Param("idUtilisateur") String idUtilisateur);

    public Ecole findByNumeroEcole(String numeroEcole);

    // nom classe : CLASSE.nom where CLASSE.ecole.id = ? group by classe.id
    // nb eleves : count(eleve.id) join ELEVE
    // nb commandes : count(commande.id) join COMMANDE_ELEVE
    // total vente (prix achat) join COMMANDE_PRODUIT
    // total vente (prix vente) join PRODUIT

    @Query(FIND_SYNTHESE)
    public List<Object[]> findSynthese(Long idEcole);

    @Query(FIND_SYNTHESE_CLASSES)
    public List<Object[]> findClasse(String idChClasse);

    @Query(FIND_ETAT_ACTIVATION_ACTIVE)
    public int findEtatActivationActive(@Param("idEcole") Long idEcole);

    @Query(FIND_ETAT_ACTIVATION_DESACTIVE)
    public int findEtatActivationDesactive(@Param("idEcole") Long idEcole);

    public Ecole findById(Long id);
}
