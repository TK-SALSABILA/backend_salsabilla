package org.school.backend.adapters.infrastructure.configuration;

import org.school.backend.application.usecases.GradeUseCase;
import org.school.backend.application.usecases.StudentLogsUseCase;
import org.school.backend.application.usecases.impl.GradeUseCaseImpl;
import org.school.backend.application.usecases.impl.StudentLogsUseCaseImpl;
import org.school.backend.domain.gateaway.GradeLogGateAway;
import org.school.backend.domain.gateaway.StudentLogGateaway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfiguration {

    @Autowired
    StudentLogGateaway studentRecordsRepository;

    @Autowired
    GradeLogGateAway graderRecordsRepository;

    @Bean
    public StudentLogsUseCase studentService(){
        return new StudentLogsUseCaseImpl(studentRecordsRepository);
    }

    @Bean
    public GradeUseCase gradeService(){return new GradeUseCaseImpl(graderRecordsRepository);
    }
}
