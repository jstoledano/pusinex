package org.toledano.pusinex.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.toledano.pusinex.models.entity.DistritoFederal;

import java.util.List;
import java.util.Optional;

public interface IDistritoFederalRepository extends CrudRepository<DistritoFederal, Integer> {
    List<DistritoFederal> findByEntidad_entidad(int entidad);
    Optional<DistritoFederal> findByEntidad_EntidadAndDistritoFederal(int entidad, int distrito);
}
