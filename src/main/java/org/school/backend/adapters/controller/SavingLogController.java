package org.school.backend.adapters.controller;

import org.school.backend.application.dto.request.SavingParamDto;
import org.school.backend.application.dto.request.SavingRequestDto;
import org.school.backend.application.usecases.SavingLogsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class SavingLogController extends BaseController {
    private final SavingLogsUseCase savingLogsUseCase;

    public SavingLogController(final SavingLogsUseCase savingLogsUseCase){
        this.savingLogsUseCase = savingLogsUseCase;
    }

    @RequestMapping(value = "saving/record", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "rpp", defaultValue = "1") int rpp,
            @RequestParam(name = "q",required = false) String q,
            @RequestParam(name = "status",required = false) String status,
            @RequestParam(name = "month",required = false) String month,
            @RequestParam(name = "classId",required = false) UUID classId
    ){
        SavingParamDto req = new SavingParamDto(page, rpp, q, status, month, classId);
        return responseDefault.build(savingLogsUseCase.findAll(req).get(),timeStamp, HttpStatus.OK);

    }

    @RequestMapping(value = "saving/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody SavingRequestDto record){
        savingLogsUseCase.create(record);
        return ResponseEntity.status(HttpStatus.CREATED).body("Saving stdent record created successfully");
    }

    @RequestMapping(value = "saving/{studentId}/balance", method = RequestMethod.GET)
    public ResponseEntity<?> checkBalance(@PathVariable("studentId")UUID studentId){
        return responseDefault.build(savingLogsUseCase.checkBalance(studentId),timeStamp, HttpStatus.OK);
    }
}
