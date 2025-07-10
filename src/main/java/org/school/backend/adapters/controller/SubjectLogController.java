package org.school.backend.adapters.controller;


import org.school.backend.application.dto.response.SubjectLogsDto;
import org.school.backend.application.usecases.SubjectLogsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class SubjectLogController extends BaseController{

    private final SubjectLogsUseCase subjectReocrdService;

    public SubjectLogController(final SubjectLogsUseCase subjectReocrdService){
        this.subjectReocrdService = subjectReocrdService;
    }

    @RequestMapping(value = "subject/record", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "rpp", defaultValue = "1") int rpp){
        return responseDefault.build(subjectReocrdService.findAll(rpp,page).get(),timeStamp, HttpStatus.OK);

    }

    @RequestMapping(value = "subject/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable("id") UUID id){
        return responseDefault.build(subjectReocrdService.findById(id).get(), timeStamp,HttpStatus.OK);
    }

    @RequestMapping(value = "subject/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody SubjectLogsDto record){
        subjectReocrdService.create(record);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student record created successfully");
    }

    @RequestMapping(value = "subject/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody SubjectLogsDto record) {
        subjectReocrdService.update(id, record);
        return ResponseEntity.status(HttpStatus.OK).body("Subject record updated successfully");
    }

}
