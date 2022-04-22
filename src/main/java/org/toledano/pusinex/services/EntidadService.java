package org.toledano.pusinex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.toledano.pusinex.models.entity.Entidad;
import org.toledano.pusinex.models.repository.IEntidadRepository;

import java.util.Optional;

public class EntidadService {

    private final IEntidadRepository repository;

    @Autowired
    public EntidadService(IEntidadRepository repository) {
        this.repository = repository;
    }

    Optional<Entidad> getEntidad(final int entidad) {
        return repository.findById(entidad);
    }

}
