package org.toledano.pusinex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.toledano.pusinex.models.repository.IUserRepository;

@Service
public class UserService {
    private final IUserRepository repository;

    // Constructor
    @Autowired
    public UserService(final IUserRepository repository) {
        this.repository = repository;
    }
}
