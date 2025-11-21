package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.GradeStudentDataSource;
import org.school.backend.domain.gateaway.GradeStudentLogGateaway;
import org.school.backend.domain.model.GradeStudentModel;
import org.school.backend.domain.model.StudentParticipantModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GradeStudentLogGatewayImpl implements GradeStudentLogGateaway {

    final GradeStudentDataSource gradeStudentDataSource;

    public GradeStudentLogGatewayImpl(final GradeStudentDataSource gradeStudentDataSource){
        this.gradeStudentDataSource = gradeStudentDataSource;
    }

    @Override
    public GradeStudentModel findStudentByClass(Object classId) {
        return gradeStudentDataSource.findStudentByClass(classId);
    }

    @Override
    public List<StudentParticipantModel> findActivityStudentParticipant(List<UUID> gradeId) {
        return gradeStudentDataSource.findActivityStudentParticipant(gradeId);
    }
}
