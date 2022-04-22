package org.toledano.pusinex.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.toledano.pusinex.models.entity.TAC;

import java.util.UUID;

public interface ITacRepository extends JpaRepository<TAC, UUID> {
}
