package com.spring.microfinance.loan.service;

import java.util.List;

import com.spring.microfinance.loan.entity.Loan;

public interface LoanService {

	public Loan createLoan(Loan loan);

	public Loan updateLoan(Loan loan);

	public void deleteLoan(String loanId);

	public List<Loan> getAllActiveLoan();

	public Loan getActiveLoan(String loanId);

	public List<Loan> getAllActiveLoansOfBorrower(String borrowerId);

}
