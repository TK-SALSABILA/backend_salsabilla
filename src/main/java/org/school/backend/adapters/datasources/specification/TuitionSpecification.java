package org.school.backend.adapters.datasources.specification;

import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.school.backend.adapters.schema.jpa.StudentGradeJpa;
import org.school.backend.adapters.schema.jpa.StudentLogJpa;
import org.school.backend.adapters.schema.jpa.TuitionFeeJpa;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class TuitionSpecification {

        public static Specification<TuitionFeeJpa> hasClassId(UUID classId) {
            return (root, query, cb) -> {
                if (classId == null) return cb.conjunction();
                Subquery<UUID> sub = query.subquery(UUID.class);
                Root<StudentGradeJpa> grade = sub.from(StudentGradeJpa.class);
                sub.select(grade.get("studentId"))
                        .where(cb.equal(grade.get("gradeId"), classId));
                return root.get("studentId").in(sub);
            };
        }

        public static Specification<TuitionFeeJpa> hasMonth(String month) {
            return (root, query, cb) -> {
                if (month == null || month.isBlank()) return cb.conjunction();
                return cb.equal(root.get("month"), month);
            };
        }

        public static Specification<TuitionFeeJpa> hasStatus(String status) {
            return (root, query, cb) -> {
                if (status == null || status.isBlank()) return cb.conjunction();
                return cb.equal(root.get("status"), status);
            };
        }

        public static Specification<TuitionFeeJpa> hasStudentName(String name) {
            return (root, query, cb) -> {
                if (name == null || name.isBlank()) return cb.conjunction();
                Subquery<UUID> sub = query.subquery(UUID.class);
                Root<StudentLogJpa> student = sub.from(StudentLogJpa.class);
                sub.select(student.get("id"))
                        .where(
                                cb.and(
                                        cb.equal(student.get("id"), root.get("studentId")),
                                        cb.like(cb.lower(student.get("fullName")), "%" + name.toLowerCase() + "%")
                                )
                        );
                return cb.exists(sub);
            };
        }
}
