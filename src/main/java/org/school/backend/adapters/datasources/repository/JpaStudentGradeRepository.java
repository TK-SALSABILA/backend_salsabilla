package org.school.backend.adapters.datasources.repository;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.schema.jpa.StudentGradeJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface JpaStudentGradeRepository extends JpaRepository<StudentGradeJpa, UUID> {
    Optional<StudentGradeJpa> findByStudentId(UUID studentId);

    @Query("""
    SELECT sg.studentId FROM StudentGradeJpa sg
    WHERE sg.gradeId = :classId
    """)
    List<UUID> findStudentIdsByClassId(@Param("classId") UUID classId);


}
