package org.school.backend.adapters.datasources.specification;

import org.school.backend.adapters.schema.jpa.SubjectLogJpa;
import org.springframework.data.jpa.domain.Specification;

public class SubjectSpecification {

    public static Specification<SubjectLogJpa> findSubject(String name){
        return ((root, query, criteriaBuilder) -> {
            if(name == null || name.isBlank())
            {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("subjectName")), "%" + name.toLowerCase() + "%");
        });
    }

    public static Specification<SubjectLogJpa> findMandatory(Boolean isMandatory){
        return (((root, query, criteriaBuilder) -> {
            if (isMandatory == null)
            {
                return criteriaBuilder.conjunction();
            }
            return isMandatory
                    ? criteriaBuilder.isTrue(root.get("isMandatory"))
                    : criteriaBuilder.isFalse(root.get("isMandatory"));
        }));
    }

}
