package org.school.backend.adapters.controller;

import org.school.backend.application.dto.request.OperationFeeReqDto;
import org.school.backend.application.usecases.OperationalFeeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class OptFeeLogController extends BaseController {
    private final OperationalFeeUseCase operationalFeeUseCase;

    public OptFeeLogController(final OperationalFeeUseCase operationalFeeUseCase){
        this.operationalFeeUseCase = operationalFeeUseCase;
    }

    @RequestMapping(value = "operational/record", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "rpp", defaultValue = "1") int rpp,
            @RequestParam(name = "q",required = false) String q,
            @RequestParam(name = "status",required = false) String status,
            @RequestParam(name = "month",required = false) String month,
            @RequestParam(name = "classId",required = false) UUID classId
    ){
//        SavingParamDto req = new SavingParamDto(page, rpp, q, status, month, classId);
        return responseDefault.build(operationalFeeUseCase.findAllFee(page, rpp, q, status, month, classId).get(),timeStamp,HttpStatus.OK);

    }

    @RequestMapping(value = "operational/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody OperationFeeReqDto record){
        operationalFeeUseCase.createPayment(record);
        return ResponseEntity.status(HttpStatus.CREATED).body("Operational fee stdent record created successfully");
    }

}
