package org.school.backend.adapters.datasources.specification;

import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.school.backend.adapters.schema.jpa.SavingLogJpa;
import org.school.backend.adapters.schema.jpa.StudentGradeJpa;
import org.school.backend.adapters.schema.jpa.StudentLogJpa;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class SavingSpecification {

    public static Specification<SavingLogJpa> hasStudentName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) {
                return cb.conjunction();
            }

            Subquery<UUID> subquery = query.subquery(UUID.class);
            Root<StudentLogJpa> studentRoot = subquery.from(StudentLogJpa.class);

            subquery.select(studentRoot.get("id"))
                    .where(
                            cb.and(
                                    cb.equal(studentRoot.get("id"), root.get("studentId")),
                                    cb.like(cb.lower(studentRoot.get("fullName")), "%" + name.toLowerCase() + "%")
                            )
                    );

            return cb.exists(subquery);
        };
    }
    public static Specification<SavingLogJpa> hasStatus(String status) {
        return (root, query, cb) -> {
            if (status == null || status.isBlank()) {
                return cb.conjunction();
            }
            return cb.equal(cb.lower(root.get("paymentType")), status.toLowerCase());
        };
    }

    public static Specification<SavingLogJpa> hasMonth(String month) {
        return (root, query, cb) -> {
            if (month == null || month.isBlank()) {
                return cb.conjunction();
            }

            return cb.like(cb.function("to_char", String.class, root.get("transactionDate"), cb.literal("yyyy-MM")), month);
        };
    }

    public static Specification<SavingLogJpa> hasClassId(UUID classId) {
        return (root, query, cb) -> {
            if (classId == null) {
                return cb.conjunction();
            }

            Subquery<UUID> subquery = query.subquery(UUID.class);
            Root<StudentGradeJpa> gradeRoot = subquery.from(StudentGradeJpa.class);
            subquery.select(gradeRoot.get("studentId"))
                    .where(cb.equal(gradeRoot.get("gradeId"), classId));

            return root.get("studentId").in(subquery);
        };
    }
}
