package org.school.backend.adapters.controller;

import org.school.backend.application.dto.request.SavingRequestDto;
import org.school.backend.application.usecases.SavingLogsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class SavingLogController extends BaseController {
    private final SavingLogsUseCase savingLogsUseCase;

    public SavingLogController(final SavingLogsUseCase savingLogsUseCase){
        this.savingLogsUseCase = savingLogsUseCase;
    }

    @RequestMapping(value = "saving/record", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "rpp", defaultValue = "1") int rpp){
        return responseDefault.build(savingLogsUseCase.findAll(rpp,page).get(),timeStamp, HttpStatus.OK);

    }

    @RequestMapping(value = "saving/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody SavingRequestDto record){
        System.out.println(record + " ini dari controller");
        savingLogsUseCase.create(record);
        return ResponseEntity.status(HttpStatus.CREATED).body("Saving stdent record created successfully");
    }
}
