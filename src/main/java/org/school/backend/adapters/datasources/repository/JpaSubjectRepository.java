package org.school.backend.adapters.datasources.repository;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.schema.jpa.SubjectLogJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Transactional
public interface JpaSubjectRepository extends JpaRepository<SubjectLogJpa, UUID>, JpaSpecificationExecutor<SubjectLogJpa> {
}
