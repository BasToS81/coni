package com.gor.sellphotos.repository;

import org.springframework.data.repository.CrudRepository;

import com.gor.sellphotos.dao.Responsable;

public interface ResponsableRepository extends CrudRepository<Responsable, Long> {

    public Responsable findByIdentifiant(String idUtilisateur);

}
