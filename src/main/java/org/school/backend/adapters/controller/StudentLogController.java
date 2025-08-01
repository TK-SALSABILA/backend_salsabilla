package org.school.backend.adapters.controller;

import org.school.backend.application.dto.request.StudentRequestDto;
import org.school.backend.application.usecases.StudentLogsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class StudentLogController extends BaseController{

    private final StudentLogsUseCase studentRecordService;

    public StudentLogController(final StudentLogsUseCase studentRecordService){
        this.studentRecordService = studentRecordService;
    }

    @RequestMapping(value = "student/record", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "rpp", defaultValue = "1") int rpp){
        return responseDefault.build(studentRecordService.findAll(rpp,page).get(),timeStamp, HttpStatus.OK);

    }

    @RequestMapping(value = "student/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable("id") UUID id){
        return responseDefault.build(studentRecordService.findById(id).get(), timeStamp,HttpStatus.OK);
    }

    @RequestMapping(value = "student/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody StudentRequestDto record){
        studentRecordService.create(record);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student record created successfully");
    }
}
