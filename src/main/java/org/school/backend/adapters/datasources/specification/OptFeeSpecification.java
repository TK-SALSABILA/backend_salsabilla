package org.school.backend.adapters.datasources.specification;

import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.school.backend.adapters.schema.jpa.OptFeeJpa;
import org.school.backend.adapters.schema.jpa.StudentGradeJpa;
import org.school.backend.adapters.schema.jpa.StudentLogJpa;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class OptFeeSpecification {

    public static Specification<OptFeeJpa> hasStudentName(String name){
        return ((root, query, criteriaBuilder) -> {
            if(name == null || name.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            Subquery<UUID> subquery = query.subquery(UUID.class);
            Root<StudentLogJpa> studentRoot = subquery.from(StudentLogJpa.class);

            subquery.select(studentRoot.get("id"))
                    .where(
                            criteriaBuilder.and(
                                    criteriaBuilder.equal(studentRoot.get("id"),root.get("studentId")),
                                    criteriaBuilder.like(criteriaBuilder.lower(studentRoot.get("fullName")),"%" + name.toLowerCase() + "%")
                            )
                    );

            return criteriaBuilder.exists(subquery);


        });
    }

    public static Specification<OptFeeJpa> hasStatus(String status){
        return ((root, query, criteriaBuilder) -> {
            if(status == null || status.isBlank()){
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("paymentType"),status.toLowerCase());
        });
    }

    public static Specification<OptFeeJpa> hasMonth(String month){
        return ((root, query, criteriaBuilder) -> {
            if(month == null || month.isBlank()){
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(criteriaBuilder.function("to_char",String.class, root.get("transactionDate"), criteriaBuilder.literal("yyyy-MM")), month);

        });
    }

    public static Specification<OptFeeJpa> hasClassId(UUID classId) {
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
