package org.toledano.pusinex.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.toledano.pusinex.models.entity.Entidad;

import java.util.List;

public interface EntidadRepository extends CrudRepository<Entidad, Integer> {
    Entidad findByEntidad(int entidad);
}
