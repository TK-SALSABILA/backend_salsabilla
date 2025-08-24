package org.school.backend.adapters.datasources.repository;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.schema.jpa.TuitionFeeJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface JpaTuitionFeeRepository extends JpaRepository<TuitionFeeJpa, UUID>, JpaSpecificationExecutor<TuitionFeeJpa> {
    @Query("SELECT t FROM TuitionFeeJpa t WHERE t.studentId IN :studentIds AND t.month = :month")
    List<TuitionFeeJpa> findByStudentIdsAndMonthAndStatus(
            @Param("studentIds") List<UUID> studentIds,
            @Param("month") String month
    );

    @Query(
            value = """
                SELECT * 
                FROM tuition_student
                WHERE student_id IN (:studentIds)
                  AND month = :month
                """,
            nativeQuery = true
    )
    Optional<TuitionFeeJpa> findByStudentIdsAndMonth(
            @Param("studentIds") UUID studentIds,
            @Param("month") String month
    );
}
