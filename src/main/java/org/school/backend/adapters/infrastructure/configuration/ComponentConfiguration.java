package org.school.backend.adapters.infrastructure.configuration;

import org.school.backend.application.usecases.*;
import org.school.backend.application.usecases.impl.*;
import org.school.backend.domain.gateaway.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfiguration {

    @Autowired
    StudentLogGateaway studentRecordsRepository;

    @Autowired
    ParentLogGateaway parentRecordRepository;

    @Autowired
    SubjectLogGateaway subjectRecordRepository;

    @Autowired
    GradeLogGateaway gradeRecordRepository;

    @Autowired
    SavingLogGateaway savingRecordRepository;

//    @Autowireds
//    StudentGradeLogGateAway graderRecordsRepository;

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

    @Bean
    public GradeUseCase gradeService(){return new GradeUseCaseImpl(gradeRecordRepository);
    }

    @Bean
    public SavingLogsUseCase savingService(){return new SavingLogsUseCaseImpl(savingRecordRepository,studentRecordsRepository);
    }
//    @Bean
//    public StudentGradeUseCase gradeService(){return new StudentGradeUseCaseImpl(graderRecordsRepository);
//    }
}
