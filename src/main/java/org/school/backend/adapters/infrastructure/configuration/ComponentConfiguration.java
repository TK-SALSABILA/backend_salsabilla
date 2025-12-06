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

    @Autowired
    GradeStudentLogGateaway gradeStudentLogRepository;

    @Autowired
    TuitionFeeLogGateaway tuitionFeeRecordRepository;

    @Autowired
    OperationalFeeGateway operationalFeeRecordRepository;

    @Autowired
    LoggerGateway loggerGateway;

    @Autowired
    ActivityGateway activityGateway;

    @Autowired
    ActivityStudentParticipantGateway activityStudentParticipantGateway;

    @Autowired
    ActivityClassParticipantGateway activityClassParticipantGateway;

    @Autowired
    ActivityPaymentGateway activityPaymentGateway;

//    @Autowireds
//    StudentGradeLogGateAway graderRecordsRepository;

    @Bean
    public StudentLogsUseCase studentService(){
        return new StudentLogsUseCaseImpl(studentRecordsRepository, loggerGateway );
    }

    @Bean
    public ParentUseCase parentService(){return new ParentUseCaseImpl(parentRecordRepository,loggerGateway);}

    @Bean
    public SubjectLogsUseCase subjectService(){
        return new SubjectLogsUseCaseImpl(subjectRecordRepository,loggerGateway);
    }

    @Bean
    public GradeUseCase gradeService(){return new GradeUseCaseImpl(gradeRecordRepository,loggerGateway);
    }

    @Bean
    public SavingLogsUseCase savingService(){return new SavingLogsUseCaseImpl(savingRecordRepository,studentRecordsRepository,loggerGateway);
    }

    @Bean
    public TuitionFeeUseCase tuitionFeeService(){return new TuitonFeeUseCaseImpl(tuitionFeeRecordRepository,gradeStudentLogRepository,studentRecordsRepository,savingRecordRepository,loggerGateway);};

    @Bean
    public OperationalFeeUseCase operationalFeeService(){return new OperationalFeeUseCaseImpl(operationalFeeRecordRepository,studentRecordsRepository,loggerGateway);}

    @Bean
    public ActivityUseCase activityService(){
        return new ActivityUseCaseImpl(activityGateway,activityClassParticipantGateway,gradeStudentLogRepository,activityStudentParticipantGateway,loggerGateway);
    }

    @Bean
    public ActivityStudentParticipantUseCase activityStudentParticipantService(){
        return  new ActivityStudentParticipantUseCaseImpl(activityStudentParticipantGateway,savingRecordRepository,activityPaymentGateway, activityGateway);
    }

}
