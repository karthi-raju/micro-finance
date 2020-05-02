package com.spring.microfinance.borrower.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.spring.microfinance.borrower.entity.Borrower;
import com.spring.microfinance.borrower.repository.BorrowerDAO;
import com.spring.microfinance.exception.DuplicateValueException;
import com.spring.microfinance.exception.NoDataFoundException;
import com.spring.microfinance.exception.UserNotFoundException;
import com.spring.microfinance.util.MicroFinanceUtil;
import com.spring.microfinance.util.Visibility;

@Service
public class BorrowerServiceImpl implements BorrowerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BorrowerServiceImpl.class);

	@Autowired
	private BorrowerDAO borrowerDAO;

	@Override
	public Borrower getBorrowerByMobile(String borrowerMobileNumber) {
		Optional<Borrower> optionalBorrower = borrowerDAO.findByMobileNumberAndVisibility(borrowerMobileNumber,
				Visibility.ACTIVE);
		if (!optionalBorrower.isPresent()) {
			LOGGER.info(" no borrower details found for the mobile number " + borrowerMobileNumber);
			throw new UserNotFoundException("No borrower detail available for mobile number " + borrowerMobileNumber);
		}
		return optionalBorrower.get();
	}

	@Override
	public List<Borrower> getAllBorrower() {
		List<Borrower> borrowers = borrowerDAO.findByVisibility(Visibility.ACTIVE);
		if (borrowers.size() == 0) {
			LOGGER.info(" no borrower details found ");
			throw new NoDataFoundException("no borrower details found");
		}
		LOGGER.info(" size of the borrower list " + borrowers.size());
		return borrowers;
	}

	// TODO: While marking as DELETED, mark the references to DELETED
	@Override
	public void deleteBorrower(String id) {
		Borrower borrowerToDelete = getBorrowerById(id);
		// setting the visibility to deleted
		borrowerToDelete.setVisibility(Visibility.DELETED);
		LOGGER.info(" setting visibility to deleted ");
		// updating the borrower detail
		borrowerDAO.save(borrowerToDelete);
	}

	@Override
	public Borrower updateBorrower(Borrower borrower) {
		try {
			return borrowerDAO.save(borrower);
		} catch (DuplicateKeyException duplicateKeyException) {
			LOGGER.info(" Exception occured in update Borrower method in BorrowerServiceImpl ");
			LOGGER.info(" Exception message " + duplicateKeyException.getMessage());
			throw new DuplicateValueException(
					MicroFinanceUtil.getFieldNameFromMessage(duplicateKeyException.getMessage()));
		}
	}

	// TODO: While creating, check if mobile number is already exist with visibility
	// as DELETED and retain the profile.

	@Override
	public Borrower createBorrower(Borrower borrower) {
		Borrower createdBorrower = null;
		// setting the visibility to active and Id to aadhar number
		borrower.setVisibility(Visibility.ACTIVE);
		borrower.setId(borrower.getAadharNumber());
		try {
			createdBorrower = borrowerDAO.save(borrower);
		} catch (DuplicateKeyException duplicateKeyException) {
			LOGGER.info(" Exception occured in Create Borrower method in BorrowerServiceImpl ");
			LOGGER.info(" Exception message " + duplicateKeyException.getMessage());
			throw new DuplicateValueException(
					MicroFinanceUtil.getFieldNameFromMessage(duplicateKeyException.getMessage()));
		}
		LOGGER.info(" borrower created successfully ");
		return createdBorrower;
	}

	@Override
	public Borrower getBorrowerById(String id) {
		Optional<Borrower> optionalBorrower = borrowerDAO.findByIdAndVisibility(id, Visibility.ACTIVE);
		if (!optionalBorrower.isPresent()) {
			LOGGER.info(" no borrower details found for the id " + id);
			throw new UserNotFoundException(" no borrower details found for the id " + id);
		}
		return optionalBorrower.get();
	}

}
