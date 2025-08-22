package org.school.backend.application.dto;

public interface BaseParamsDto {
    int page();
    int rpp();
    String q();

    default boolean hasKeyword() {
        return q() != null && !q().isBlank();
    }
}