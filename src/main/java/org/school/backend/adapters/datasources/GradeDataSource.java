package org.school.backend.adapters.datasources;

import org.school.backend.adapters.dto.GradeLogs;

import java.util.List;

public interface GradeDataSource {
    List<GradeLogs> findAll(int page, int rpp);
    void create(GradeLogs record);
}
