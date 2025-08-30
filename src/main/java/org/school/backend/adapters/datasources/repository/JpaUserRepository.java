package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.schema.jpa.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserJpa, UUID> {
    Optional<UserJpa> findByName(String name);
}
