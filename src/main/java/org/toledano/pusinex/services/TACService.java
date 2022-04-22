package org.toledano.pusinex.services;

import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.toledano.pusinex.models.entity.TAC;
import org.toledano.pusinex.models.repository.ITacRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class TACService {
    private final ITacRepository repository;

    // Constructor
    @Autowired
    public TACService(final ITacRepository repository) {
        this.repository = repository;
    }

    public Page<TAC> getTac(final int pageNumber, final int size) {
        return repository.findAll(PageRequest.of(pageNumber, size));
    }

    public Optional<TAC> getTac(final UUID id) {
        return repository.findById(id);
    }

    public TAC save(final TAC tac) {
        return repository.save(tac);
    }

    public void delete(final UUID id) {
        repository.deleteById(id);
    }

}
