package org.toledano.pusinex.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.toledano.pusinex.models.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
}
