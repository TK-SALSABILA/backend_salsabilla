package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.SavingModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SavingLogGateaway {
    Optional<List<SavingModel>> findAll(int page,int rpp);
    Optional<List<SavingModel>> findSavings(int page, int rpp,String studentName, String status, String month, UUID classId);
    void create(SavingModel record);
    Integer reduction(UUID studentId);
    void withDrawSaving(UUID studentId, Integer amount, String description);
}
