package com.spring.microfinance.borrower.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.spring.microfinance.borrower.entity.Borrower;
import com.spring.microfinance.borrower.exception.BorrowerNotFoundException;
import com.spring.microfinance.borrower.exception.DuplicateValueException;
import com.spring.microfinance.borrower.exception.NoBorrowerDataFoundException;
import com.spring.microfinance.borrower.repository.BorrowerDAO;
import com.spring.microfinance.util.MicroFinanceUtil;
import com.spring.microfinance.util.Visibility;

@Service
public class BorrowerServiceImpl implements BorrowerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BorrowerServiceImpl.class);

	@Autowired
	private BorrowerDAO borrowerDAO;

	@Override
	public Borrower getBorrower(String borrowerMobileNumber) {
		Optional<Borrower> optionalBorrower = borrowerDAO.findByMobileNumberAndVisibility(borrowerMobileNumber,
				Visibility.ACTIVE);
		if (!optionalBorrower.isPresent()) {
			LOGGER.info(" no borrower details found for the id " + borrowerMobileNumber);
			throw new BorrowerNotFoundException(borrowerMobileNumber);
		}
		return optionalBorrower.get();
	}

	@Override
	public List<Borrower> getAllBorrower() {
		List<Borrower> borrowers = borrowerDAO.findByVisibility(Visibility.ACTIVE);
		if (borrowers.size() == 0) {
			LOGGER.info(" no borrower details found ");
			throw new NoBorrowerDataFoundException();
		}
		LOGGER.info(" size of the borrower list " + borrowers.size());
		return borrowers;
	}

	@Override
	public void deleteBorrower(String borrowerId) {
		Borrower borrowerToDelete = getBorrower(borrowerId);
		// setting the visibility to deleted
		borrowerToDelete.setVisibility(Visibility.DELETED);
		LOGGER.info(" setting visibility to deleted ");
		// updating the borrower detail
		borrowerDAO.save(borrowerToDelete);
	}

	@Override
	public Borrower updateBorrower(Borrower borrower) {
		getBorrower(borrower.getMobileNumber());
		return borrowerDAO.save(borrower);
	}

	@Override
	public Borrower createBorrower(Borrower borrower) {
		Borrower createdBorrower = null;
		// setting the visibility to active
		borrower.setVisibility(Visibility.ACTIVE);
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

}
