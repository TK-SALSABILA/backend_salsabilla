package org.school.backend.adapters.datasources.repository;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.schema.jpa.StudentGradeJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
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

    @Query(value = """
        SELECT 
            sg.student_id,
            s.full_name as student_name,
            s.nik as student_nik,
            sg.grade_id,
            g.grade_level as grade_name
        FROM student_grade sg
        JOIN student s ON sg.student_id = s.id
        JOIN grade g ON sg.grade_id = g.id
        WHERE sg.grade_id IN (:gradeIds)
          AND sg.is_current = true
        ORDER BY g.grade_level, s.full_name
        """, nativeQuery = true)
    List<Map<String, Object>> findActiveStudentsByGradeIds(@Param("gradeIds") List<UUID> gradeIds);
}
