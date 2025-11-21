package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.ActivityClassParticipantModel;

import java.util.List;

public interface ActivityClassParticipantGateway {
    void createClassParticipant(List<ActivityClassParticipantModel> request);
}
