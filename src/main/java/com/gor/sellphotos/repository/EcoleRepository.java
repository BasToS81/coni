package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.Ecole;

public interface EcoleRepository extends CrudRepository<Ecole, Long> {

    //@formatter:off
    public final static String FIND_BY_IDENTIFIANT_UTILISATEUR = 
                    "SELECT e "
                    + "FROM "
                    + " Ecole e, "
                    + " Responsable r "
                    + "WHERE "
                    + " r.identifiant = :idUtilisateur "
                    + " and r.ecole.id = e.id";

    public final static String FIND_SYNTHESE = 
                    "SELECT c.identifiantChiffre, c.nom as nomClasse, count(el.id) as nbEleves, count(ce.id) as nbCommandes, "
                    + "sum(p.prix_parent_ttc) as totalVente, sum(p.prix_ecole_ttc) as totalAchat "
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
                    + "sum(p.prix_parent_ttc) as totalVente, sum(p.prix_ecole_ttc) as totalAchat "
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

    // @formatter:on

    @Query(FIND_BY_IDENTIFIANT_UTILISATEUR)
    public Ecole findByIdentifiantUtilisateur(@Param("idUtilisateur") String idUtilisateur);

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
}
