package com.spring.microfinance.loan.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.microfinance.exception.NoDataFoundException;
import com.spring.microfinance.exception.UserNotFoundException;
import com.spring.microfinance.loan.entity.Loan;
import com.spring.microfinance.loan.repository.LoanDAO;
import com.spring.microfinance.util.Visibility;

@Service
public class LoanServiceImpl implements LoanService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoanServiceImpl.class);

	@Autowired
	private LoanDAO loanDAO;

	@Override
	public Loan createLoan(Loan loan) {
		// set visibility to ACTIVE
		loan.setVisibility(Visibility.ACTIVE);
		Loan createdLoan = loanDAO.save(loan);
		LOGGER.info("Loan created successfully " + createdLoan.getId());
		return createdLoan;

	}

	@Override
	public Loan updateLoan(Loan loan) {
		return loanDAO.save(loan);
	}

	@Override
	public void deleteLoan(String loanId) {
		Loan loanToDelete = getActiveLoan(loanId);
		// setting the visibility to deleted
		loanToDelete.setVisibility(Visibility.DELETED);
		// TODO delete the references too
		loanDAO.save(loanToDelete);

	}

	@Override
	public List<Loan> getAllActiveLoan() {
		List<Loan> loans = loanDAO.findByVisibility(Visibility.ACTIVE);
		if (loans.size() == 0) {
			LOGGER.info(" no loan details found ");
			throw new NoDataFoundException("no loan details found");
		}
		LOGGER.info(" size of the loan list " + loans.size());
		return loans;
	}

	@Override
	public Loan getActiveLoan(String loanId) {
		Optional<Loan> optionalLoan = loanDAO.findByIdAndVisibility(loanId, Visibility.ACTIVE);
		if (!optionalLoan.isPresent()) {
			LOGGER.info(" no loan details found for the id " + loanId);
			throw new UserNotFoundException(" no loan details found for the id " + loanId);
		}
		return optionalLoan.get();
	}

	@Override
	public List<Loan> getAllActiveLoansOfBorrower(String borrowerId) {
		List<Loan> loans = loanDAO.findByBorrowerIdAndVisibility(borrowerId, Visibility.ACTIVE);
		if (loans.size() == 0) {
			LOGGER.info(" no loan details found for borrower " + borrowerId);
			throw new NoDataFoundException(" no loan details found for borrower " + borrowerId);
		}
		LOGGER.info(" size of the borrower's loan list " + loans.size());
		return loans;
	}

}
