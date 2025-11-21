package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.ActivityClass;
import org.school.backend.domain.model.ActivityClassModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityClassMapper {

    public static List<ActivityClassModel> toModel(List<ActivityClass> record){
        List<ActivityClassModel> activityClassModels = new ArrayList<>();

        record.forEach((model) -> {
            ActivityClassModel activityClassModel = new ActivityClassModel(
                model.gradeId, model.gradeName
            );
            activityClassModels.add(activityClassModel);
        });

        return activityClassModels;
    }
}
