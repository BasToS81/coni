package com.gor.sellphotos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.CommandeEleve;
import com.gor.sellphotos.dao.Utilisateur;

public interface CommandeEleveRepository extends CrudRepository<CommandeEleve, Long> {
	
    public final static String FIND_BY_ID_CMD_ECOLE="SELECT ce " +
                    "FROM CommandeEleve ce LEFT JOIN ce.commandeEcole cecol " +
                    "WHERE cecol.identifiant = :idCmdEcole";

    public final static String FIND_BY_ID_CMD_ECOLE_ET_STATUT="SELECT ce " +
                    "FROM CommandeEleve ce LEFT JOIN ce.commandeEcole cecol " +
                    "WHERE cecol.identifiant = :idCmdEcole and ce.statut = :statut";

    
	public CommandeEleve findByIdentifiant(String identifiant);
	
	@Query(FIND_BY_ID_CMD_ECOLE)
	public List<CommandeEleve> findByIdCmdEcole(String idCmdEcole);
	
	@Query(FIND_BY_ID_CMD_ECOLE_ET_STATUT)
	public List<CommandeEleve> findByIdCmdEcoleEtStatut(String idCmdEcole, String statut);

}
