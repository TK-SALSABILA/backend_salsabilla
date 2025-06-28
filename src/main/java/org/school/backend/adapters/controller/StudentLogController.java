package org.school.backend.adapters.controller;

import org.school.backend.application.usecases.StudentLogsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class StudentLogController extends BaseController{

    private final StudentLogsUseCase studentRecordService;

    public StudentLogController(final StudentLogsUseCase studentRecordService){
        this.studentRecordService = studentRecordService;
    }

    @RequestMapping(value = "student/record", method = RequestMethod.GET, headers = "application/json")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "rpp", defaultValue = "1") int rpp){
        return responseDefault.build(studentRecordService.findAll(rpp,page).get(),timeStamp, HttpStatus.OK);

    }

    @RequestMapping(value = "student/{id}", method = RequestMethod.GET, headers = "application/json")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id){
        return responseDefault.build(studentRecordService.findById(id).get(), timeStamp,HttpStatus.OK);
    }
}
