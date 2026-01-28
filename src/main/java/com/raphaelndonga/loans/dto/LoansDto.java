package com.raphaelndonga.loans.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LoansDto {
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @NotEmpty(message = "Mobile number should not be null or empty")
    private String mobileNumber;

    @Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
    @NotEmpty(message = "Loan number should not be null or empty")
    private String loanNumber;

    @NotEmpty(message = "Loan type should not be null or empty")
    private String loanType;

    @Positive(message = "Total Loan should be greater than zero")
    private int totalLoan;

    @PositiveOrZero(message = "Amount paid should be positive or zero")
    private int amountPaid;

    @PositiveOrZero(message = "Outstanding amount should be positive or zero")
    private int outstandingAmount;
}
