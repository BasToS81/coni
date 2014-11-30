package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gor.sellphotos.dao.Produit;

public interface ProduitRepository extends CrudRepository<Produit, Long> {

    public final static String FIND_PRINCIPAL_BY_MODELE = "SELECT p " +
                    "FROM ModeleEtTarif mt LEFT JOIN mt.modeleEtTarifPrincipal p " +
                    "WHERE mt.id = :idModele";

    public final static String FIND_SUPPLEMENTAIRE_BY_MODELE = "SELECT p " +
                    "FROM ModeleEtTarif mt LEFT JOIN mt.modeleEtTarifSupplementaire p " +
                    "WHERE mt.id = :idModele";

    public Produit findByIdentifiant(String identifiant);

    @Query(FIND_PRINCIPAL_BY_MODELE)
    public List<Produit> findPrincipalByModele(@Param("idModele") Long idModele);

    @Query(FIND_SUPPLEMENTAIRE_BY_MODELE)
    public List<Produit> findSupplementaireByModele(@Param("idModele") Long idModele);

}
