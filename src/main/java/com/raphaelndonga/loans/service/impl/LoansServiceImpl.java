package com.raphaelndonga.loans.service.impl;

import com.raphaelndonga.loans.constants.LoansConstants;
import com.raphaelndonga.loans.dto.LoansDto;
import com.raphaelndonga.loans.entity.Loans;
import com.raphaelndonga.loans.exception.LoansAlreadyExistsException;
import com.raphaelndonga.loans.exception.ResourceNotFoundException;
import com.raphaelndonga.loans.mapper.LoansMapper;
import com.raphaelndonga.loans.repository.LoansRepository;
import com.raphaelndonga.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {
    private LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> presentLoan = loansRepository.findByMobileNumber(mobileNumber);
        if(presentLoan.isPresent()){
            throw new LoansAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber){
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);

        return newLoan;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {

        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(()->
                new ResourceNotFoundException("Loans","mobileNumber", mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans);
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        return false;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        return false;
    }
}
