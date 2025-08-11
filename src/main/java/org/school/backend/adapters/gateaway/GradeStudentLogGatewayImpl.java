package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.GradeStudentDataSource;
import org.school.backend.domain.gateaway.GradeStudentLogGateaway;
import org.school.backend.domain.model.GradeStudentModel;
import org.springframework.stereotype.Component;

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
}
