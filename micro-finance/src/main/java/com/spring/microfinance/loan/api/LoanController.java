package com.spring.microfinance.loan.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.microfinance.loan.entity.Loan;
import com.spring.microfinance.loan.service.LoanService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/loan")
public class LoanController {

	@Autowired
	private LoanService loanServiceImpl;

	@ApiOperation(value = "Creates a Loan")
	@PostMapping
	public ResponseEntity<Loan> createBorrower(@RequestBody Loan loan) {
		return new ResponseEntity<Loan>(loanServiceImpl.createLoan(loan), HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieves ACTIVE Loan detail, based on given loanId")
	@GetMapping(value = "/{loanId}")
	public ResponseEntity<Loan> getActiveLoan(@PathVariable String loanId) {
		return new ResponseEntity<Loan>(loanServiceImpl.getActiveLoan(loanId), HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieves all active Loan details")
	@GetMapping(value = "/all")
	public ResponseEntity<List<Loan>> getAllActiveLoans() {
		return new ResponseEntity<List<Loan>>(loanServiceImpl.getAllActiveLoan(), HttpStatus.OK);
	}

	@ApiOperation(value = "Updates Loan details")
	@PutMapping
	public ResponseEntity<Loan> updateLoan(@RequestBody Loan loan) {
		return new ResponseEntity<Loan>(loanServiceImpl.updateLoan(loan), HttpStatus.OK);
	}

	@ApiOperation(value = "Makes Loan visibilty to deleted using loan id")
	@DeleteMapping(value = "/{loanId}")
	public ResponseEntity<HttpStatus> deleteBorrower(@PathVariable String loanId) {
		loanServiceImpl.deleteLoan(loanId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
