package com.raphaelndonga.loans.controller;

import com.raphaelndonga.loans.constants.LoansConstants;
import com.raphaelndonga.loans.dto.LoansDto;
import com.raphaelndonga.loans.dto.ResponseDto;
import com.raphaelndonga.loans.service.ILoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoansController {
    private ILoansService iLoansService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam String mobileNumber){
        iLoansService.createLoan(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201)
        );
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoan(@RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                  String mobileNumber){
        LoansDto loansDto = iLoansService.fetchLoan(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@Valid @RequestBody LoansDto loansDto){

        boolean isUpdated = iLoansService.updateLoan(loansDto);

        if (isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(
                    LoansConstants.STATUS_200,
                    LoansConstants.MESSAGE_200
            ));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(
                    LoansConstants.STATUS_417,
                    LoansConstants.MESSAGE_417_UPDATE
            ));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobileNumber){
        boolean isDeleted = iLoansService.deleteLoan(mobileNumber);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(
                    LoansConstants.STATUS_200, LoansConstants.MESSAGE_200
            ));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(
                    LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_DELETE
            ));
        }
    }
}
