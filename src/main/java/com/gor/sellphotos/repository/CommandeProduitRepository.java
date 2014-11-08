package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.CommandeProduit;
import com.gor.sellphotos.dao.Utilisateur;

public interface CommandeProduitRepository extends CrudRepository<CommandeProduit, Long> {

    public final static String FIND_BY_ID_CMD_ELEVE_ET_ID_PROD =   "SELECT cp " + 
                                                                    "FROM CommandeProduit cp LEFT JOIN cp.commandeEleve ce " + 
                                                                    "LEFT JOIN cp.produit p " +
                                                                    "WHERE ce.identifiant = :idCmdEleve AND p.identifiant = :idProd";
    
    public final static String FIND_BY_ID_CMD_ELEVE =   "SELECT cp " + 
                                                        "FROM CommandeProduit cp LEFT JOIN cp.commandeEleve ce " + 
                                                        "WHERE ce.identifiant = :idCmdEleve";

    @Query(FIND_BY_ID_CMD_ELEVE_ET_ID_PROD)
    public CommandeProduit findByIdCmdEleveEtIdProd(@Param("idCmdEleve") String idCmdEleve, @Param("idProd") String idProd);

    @Query(FIND_BY_ID_CMD_ELEVE)
    public List<CommandeProduit> findByIdCmdEleve(@Param("idCmdEleve") String idCmdEleve);

}
