package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.dto.GradeLogs;

import java.util.List;
import java.util.Optional;

public interface GradeRepository {
   List<GradeLogs> findAll(int page, int rpp);
    void create(GradeLogs record);
}
