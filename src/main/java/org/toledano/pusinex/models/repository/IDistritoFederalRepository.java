package org.toledano.pusinex.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.toledano.pusinex.models.entity.DistritoFederal;

import java.util.List;

public interface IDistritoFederalRepository extends CrudRepository<DistritoFederal, Integer> {
    List<DistritoFederal> findByEntidad_id(int entidad);
}
