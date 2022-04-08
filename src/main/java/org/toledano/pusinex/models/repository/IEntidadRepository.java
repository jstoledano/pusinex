package org.toledano.pusinex.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.toledano.pusinex.models.entity.Entidad;

public interface IEntidadRepository extends CrudRepository<Entidad, Integer> {
    Entidad findByEntidad(int entidad);
}
