package org.school.backend.adapters.datasources.specification;

import org.school.backend.adapters.schema.jpa.StudentLogJpa;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class StudentSpecification {

    public static Specification<StudentLogJpa> fullNameContains(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("fullName")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<StudentLogJpa> classIdEquals(UUID classId) {
        return (root, query, cb) -> {
            if (classId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("classId"), classId);
        };
    }

}
