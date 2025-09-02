package org.school.backend.adapters.controller;

import org.school.backend.application.dto.GradeDto;
import org.school.backend.application.dto.response.SubjectLogsDto;
import org.school.backend.application.usecases.GradeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class GradeLogController extends BaseController {

    private final GradeUseCase gradeRecordService;

    public GradeLogController(final GradeUseCase gradeRecordService){
        this.gradeRecordService = gradeRecordService;
    }

    @RequestMapping(value = "grade/record", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "rpp", defaultValue = "1") int rpp){
        return responseDefault.build(gradeRecordService.findAll(rpp,page).get(),timeStamp, HttpStatus.OK);

    }
    @RequestMapping(value = "grade/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody GradeDto record){
        gradeRecordService.create(record);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student record created successfully");
    }
}
