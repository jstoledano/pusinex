package org.toledano.pusinex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.toledano.pusinex.models.entity.DistritoFederal;
import org.toledano.pusinex.models.repository.IDistritoFederalRepository;

import java.util.Optional;

public class DistritoFederalService {
    private final IDistritoFederalRepository repository;

    @Autowired
    public DistritoFederalService(final IDistritoFederalRepository repository) {
        this.repository = repository;
    }

    public Optional<DistritoFederal> getDistritoFederal(final int entidad, final int distritoFederal) {
        return repository.findByEntidad_EntidadAndDistritoFederal(entidad, distritoFederal);
    }

}
