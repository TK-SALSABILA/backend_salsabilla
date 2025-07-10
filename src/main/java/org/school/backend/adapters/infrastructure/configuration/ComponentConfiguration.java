package org.school.backend.adapters.infrastructure.configuration;

import org.school.backend.application.dto.ParentDto;
import org.school.backend.application.usecases.GradeUseCase;
import org.school.backend.application.usecases.ParentUseCase;
import org.school.backend.application.usecases.StudentLogsUseCase;
import org.school.backend.application.usecases.SubjectLogsUseCase;
import org.school.backend.application.usecases.impl.GradeUseCaseImpl;
import org.school.backend.application.usecases.impl.ParentUseCaseImpl;
import org.school.backend.application.usecases.impl.StudentLogsUseCaseImpl;
import org.school.backend.application.usecases.impl.SubjectLogsUseCaseImpl;
import org.school.backend.domain.gateaway.GradeLogGateAway;
import org.school.backend.domain.gateaway.ParentLogGateaway;
import org.school.backend.domain.gateaway.StudentLogGateaway;
import org.school.backend.domain.gateaway.SubjectLogGateaway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class ComponentConfiguration {

    @Autowired
    StudentLogGateaway studentRecordsRepository;

    @Autowired
    ParentLogGateaway parentRecordRepository;

    @Autowired
    SubjectLogGateaway subjectRecordRepository;

//    @Autowired
//    GradeLogGateAway graderRecordsRepository;

    @Bean
    public StudentLogsUseCase studentService(){
        return new StudentLogsUseCaseImpl(studentRecordsRepository);
    }

    @Bean
    public ParentUseCase parentService(){return new ParentUseCaseImpl(parentRecordRepository);}

    @Bean
    public SubjectLogsUseCase subjectService(){
        return new SubjectLogsUseCaseImpl(subjectRecordRepository);
    }

//    @Bean
//    public GradeUseCase gradeService(){return new GradeUseCaseImpl(graderRecordsRepository);
//    }
}
