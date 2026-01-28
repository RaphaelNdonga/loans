package com.raphaelndonga.loans.service;

import com.raphaelndonga.loans.dto.LoansDto;
import com.raphaelndonga.loans.entity.Loans;

public interface ILoansService {
    void createLoan(String mobileNumber);
    LoansDto fetchLoan(String mobileNumber);
    boolean updateLoan(LoansDto loansDto);
    boolean deleteLoan(String mobileNumber);
}
