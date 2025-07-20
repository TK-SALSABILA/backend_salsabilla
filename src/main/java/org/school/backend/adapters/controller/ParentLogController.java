package org.school.backend.adapters.controller;

import org.school.backend.application.usecases.ParentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class ParentLogController extends BaseController{

    private final ParentUseCase parentService;

    public ParentLogController(final ParentUseCase parentService){
        this.parentService=parentService;
    }

    @RequestMapping(value = "parent/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<?> findByStudentId(@PathVariable("studentId") UUID studentId){
        return  responseDefault.build(parentService.findByStudentId(studentId).get(),timeStamp, HttpStatus.OK);
    }

}
