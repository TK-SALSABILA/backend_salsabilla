package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.schema.jpa.OptFeeJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface JpaOptFeeRepository extends JpaRepository<OptFeeJpa, UUID>, JpaSpecificationExecutor<OptFeeJpa> {
}
