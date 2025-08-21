package org.school.backend.adapters.datasources.repository;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.schema.jpa.StudentLogJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface JpaStudentLogsRepository extends JpaRepository<StudentLogJpa, UUID>, JpaSpecificationExecutor<StudentLogJpa> {

    @Query("SELECT s FROM StudentLogJpa s WHERE LOWER(s.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<StudentLogJpa> findByFullNameLike(@Param("keyword") String keyword);
}
