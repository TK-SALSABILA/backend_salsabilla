package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.SavingModel;

import java.util.List;
import java.util.Optional;

public interface SavingLogGateaway {
    Optional<List<SavingModel>> findAll(int page,int rpp);
    void create(SavingModel record);
}
