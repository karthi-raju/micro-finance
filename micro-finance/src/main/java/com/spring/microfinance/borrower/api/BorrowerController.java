package com.spring.microfinance.borrower.api;

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

import com.spring.microfinance.borrower.entity.Borrower;
import com.spring.microfinance.borrower.service.BorrowerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/borrower")
public class BorrowerController {

	@Autowired
	private BorrowerService borrowerServiceImpl;

	@ApiOperation(value = "Creates a Borrower")
	@PostMapping
	public ResponseEntity<Borrower> createBorrower(@RequestBody Borrower borrower) {
		return new ResponseEntity<Borrower>(borrowerServiceImpl.createBorrower(borrower), HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieves active Borrower detail, based on given mobile number")
	@GetMapping(value = "/{mobileNumber}")
	public ResponseEntity<Borrower> getBorrower(@PathVariable String mobileNumber) {
		return new ResponseEntity<Borrower>(borrowerServiceImpl.getBorrowerByMobile(mobileNumber), HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieves all active Borrower details")
	@GetMapping(value = "/all")
	public ResponseEntity<List<Borrower>> getAllActiveBorrowers() {
		return new ResponseEntity<List<Borrower>>(borrowerServiceImpl.getAllBorrower(), HttpStatus.OK);
	}

	@ApiOperation(value = "Updates Borrower details")
	@PutMapping
	public ResponseEntity<Borrower> updateBorrower(@RequestBody Borrower borrower) {
		return new ResponseEntity<Borrower>(borrowerServiceImpl.updateBorrower(borrower), HttpStatus.OK);
	}

	@ApiOperation(value = "Makes Borrower visibilty to deleted using id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Borrower> deleteBorrower(@PathVariable String id) {
		borrowerServiceImpl.deleteBorrower(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
