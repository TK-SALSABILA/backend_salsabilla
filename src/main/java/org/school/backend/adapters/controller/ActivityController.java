package org.school.backend.adapters.controller;

import org.school.backend.application.dto.request.ActivityRequestDto;
import org.school.backend.application.usecases.ActivityStudentParticipantUseCase;
import org.school.backend.application.usecases.ActivityUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class ActivityController extends BaseController {
    private final ActivityUseCase activityUseCase;
    private final ActivityStudentParticipantUseCase activityStudentParticipantUseCase;

    public ActivityController(
            final ActivityUseCase activityUseCase,
            final ActivityStudentParticipantUseCase activityStudentParticipantUseCase
    ){
        this.activityUseCase = activityUseCase;
        this.activityStudentParticipantUseCase = activityStudentParticipantUseCase;
    }

    @RequestMapping(value = "activity/record", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "rpp", defaultValue = "1") int rpp
    ){
        return responseDefault.build(activityUseCase.getActivity(page, rpp), timeStamp, HttpStatus.OK);
    }

    @RequestMapping(value = "/{activityId}/students", method = RequestMethod.GET)
    public ResponseEntity<?> getListStudentsActivity(
            @PathVariable UUID activityId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "rpp", defaultValue = "1") int rpp
    ){
        return responseDefault.build(activityStudentParticipantUseCase.getListStudent(activityId, page, rpp),timeStamp,HttpStatus.OK);
    }

    @RequestMapping(value = "activity/create",method = RequestMethod.POST)
    public ResponseEntity<?> createActivity( @RequestBody ActivityRequestDto request){
        activityUseCase.createActivity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Activity Student record created successfully");
    }
}
