package org.school.backend.adapters.controller;

import org.school.backend.adapters.dto.TuitionFeeLogReq;
import org.school.backend.application.dto.request.TuitonFeeReqDto;
import org.school.backend.application.usecases.TuitionFeeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class TuitionFeeLogController extends BaseController{
    private final TuitionFeeUseCase tuitionFeeUseCase;

    public TuitionFeeLogController(final TuitionFeeUseCase tuitionFeeUseCase){
        this.tuitionFeeUseCase = tuitionFeeUseCase;
    }

    @GetMapping("/tuition/record/{classId}/{month}")
    public ResponseEntity<?> findAll(
            @PathVariable UUID classId,
            @PathVariable String month
    ) {
        return responseDefault.build(
                tuitionFeeUseCase.findAllTuition(classId, month).get(),
                timeStamp,
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "tuition/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody TuitonFeeReqDto record){
        tuitionFeeUseCase.createTuition(record);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tuition stdent record created successfully");
    }
}
