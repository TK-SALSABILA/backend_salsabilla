package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.dto.GradeLogs;

import java.util.List;

public interface GradeRepository {
   List<GradeLogs> findAll(int page, int rpp);
    void create(GradeLogs record);
}
