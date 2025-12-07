package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.ActivityClassDto;
import org.school.backend.application.dto.request.ActivityRequestDto;
import org.school.backend.application.dto.response.ActivityResponseDto;
import org.school.backend.application.mappers.ActivityMapper;
import org.school.backend.application.usecases.ActivityUseCase;
import org.school.backend.domain.gateaway.*;
import org.school.backend.domain.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActivityUseCaseImpl implements ActivityUseCase {
    private final ActivityGateway activityGateway;
    private final ActivityClassParticipantGateway activityClassParticipantGateway;
    private final GradeStudentLogGateaway studentGradeGateway;
    private final ActivityStudentParticipantGateway activityStudentParticipantGateway;

    private final LoggerGateway logger;


    public ActivityUseCaseImpl(
            final ActivityGateway activityGateway,
            final ActivityClassParticipantGateway activityClassParticipantGateway,
            final GradeStudentLogGateaway studentGradeGateway,
            final ActivityStudentParticipantGateway activityStudentParticipantGateway,
            final LoggerGateway logger
    ){
        this.activityGateway = activityGateway;
        this.activityClassParticipantGateway = activityClassParticipantGateway;
        this.studentGradeGateway= studentGradeGateway;
        this.activityStudentParticipantGateway = activityStudentParticipantGateway;
        this.logger = logger;
    }

    @Override
    public List<ActivityResponseDto> getActivity(int page, int rpp) {
        return ActivityMapper.toListDto(activityGateway.getActivityStudent(page, rpp));
    }

    @Override
    public ActivityResponseDto getActivityDetail(UUID id) {
        return  ActivityMapper.convertModelToResponse(activityGateway.getActivityDetail(id));
    }

    @Override
    public void createActivity(ActivityRequestDto record) {
        // 1. Create activity
        ActivityModel activityModel = activityGateway.createActivity(ActivityMapper.convertEntityToModel(record));
        logger.info(String.valueOf(activityModel.id()) + "ini adalah activitymodel id");
        // 2. Create class participants
        List<ActivityClassParticipantModel> classParticipants = new ArrayList<>();
        for (ActivityClassDto classDto : record.classParticipant()) {
            logger.info(String.valueOf(classDto));
            ActivityClassParticipantModel participant = new ActivityClassParticipantModel(
                    activityModel.id(),
                    classDto.gradeId(),
                    classDto.gradeName()
            );
            classParticipants.add(participant);
        }
        activityClassParticipantGateway.createClassParticipant(classParticipants);

        // 3. Get active students from selected grades
        List<UUID> classIds = record.classParticipant()
                .stream()
                .map(ActivityClassDto::gradeId)
                .toList();

        logger.info(String.valueOf(classIds) + " list class id");

        List<StudentParticipantModel> students = studentGradeGateway.findActivityStudentParticipant(classIds);
        logger.info(students.toString() + "ini list studentns");

        // 4. Calculate amount per student
        int totalStudents = students.size();
        int amountPerStudent = record.totalFundsRequired() / totalStudents;

        // 5. Create student participants
        List<ActivityStudentParticipantModel> studentParticipants = new ArrayList<>();
        for (StudentParticipantModel student : students) {
            ActivityStudentParticipantModel participant = new ActivityStudentParticipantModel(
                    activityModel.id(),
                    student.studentId(),
                    student.gradeId(),
                    student.studentName(),
                    student.studentNik(),
                    student.gradeName(),
                    amountPerStudent,
                    0,
                    amountPerStudent,
                    "BELUM_LUNAS"
            );
            studentParticipants.add(participant);
        }
        logger.info(studentParticipants.toString() + "  ini hasil dari looping untuk data di masukan");

        activityStudentParticipantGateway.createActivityStudents(studentParticipants);
    }
}
