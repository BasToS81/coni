package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.Eleve;

public interface EleveRepository extends CrudRepository<Eleve, Long> {
	
    public final static String FIND_BY_ID_FAMILLE="SELECT e " +
                    "FROM Eleve e LEFT JOIN e.famille f " +
                    "WHERE f.id = :idFamille";

    
	public Eleve findByIdentifiantChiffre(String identifiantChiffre);
	
	@Query(FIND_BY_ID_FAMILLE)
    public List<Eleve> findByIdFamille(Long idFamille);

}
