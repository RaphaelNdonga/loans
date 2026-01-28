package com.raphaelndonga.loans.mapper;

import com.raphaelndonga.loans.dto.LoansDto;
import com.raphaelndonga.loans.entity.Loans;

public class LoansMapper {
    public static LoansDto mapToLoansDto(Loans loans){
        LoansDto loansDto = new LoansDto();
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());

        return loansDto;
    }

    public static Loans mapToLoans(LoansDto loansDto){
        Loans loans = new Loans();
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loansDto.setOutstandingAmount(loansDto.getOutstandingAmount());
        loansDto.setMobileNumber(loansDto.getMobileNumber());

        return loans;
    }
}
