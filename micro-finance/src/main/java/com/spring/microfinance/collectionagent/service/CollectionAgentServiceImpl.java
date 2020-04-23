package com.spring.microfinance.collectionagent.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.spring.microfinance.borrower.exception.DuplicateValueException;
import com.spring.microfinance.borrower.exception.NoUserDataFoundException;
import com.spring.microfinance.borrower.exception.UserNotFoundException;
import com.spring.microfinance.collectionagent.entity.CollectionAgent;
import com.spring.microfinance.collectionagent.repository.CollectionAgentDAO;
import com.spring.microfinance.util.MicroFinanceUtil;
import com.spring.microfinance.util.Visibility;

@Service
public class CollectionAgentServiceImpl implements CollectionAgentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CollectionAgentServiceImpl.class);

	@Autowired
	private CollectionAgentDAO collectionAgentDAO;

	@Override
	public CollectionAgent getCollectionAgentByMobile(String collectionAgentmMobileNumber) {
		Optional<CollectionAgent> optionalCA = collectionAgentDAO
				.findByMobileNumberAndVisibility(collectionAgentmMobileNumber, Visibility.ACTIVE);
		if (!optionalCA.isPresent()) {
			LOGGER.info(" no collection agent details found for the mobile number " + collectionAgentmMobileNumber);
			throw new UserNotFoundException(
					"No collection agent detail available for mobile number " + collectionAgentmMobileNumber);
		}
		return optionalCA.get();
	}

	@Override
	public List<CollectionAgent> getAllCollectionAgent() {
		List<CollectionAgent> cas = collectionAgentDAO.findByVisibility(Visibility.ACTIVE);
		if (cas.size() == 0) {
			LOGGER.info(" no borrower details found ");
			throw new NoUserDataFoundException("no borrower details found");
		}
		LOGGER.info(" size of the borrower list " + cas.size());
		return cas;
	}

	@Override
	public void deleteCollectionAgent(String id) {
		CollectionAgent collectionAgentToDelete = getCollectionAgentById(id);
		// setting the visibility to deleted
		collectionAgentToDelete.setVisibility(Visibility.DELETED);
		LOGGER.info(" setting visibility to deleted ");
		// updating the borrower detail
		collectionAgentDAO.save(collectionAgentToDelete);

	}

	@Override
	public CollectionAgent updateCollectionAgent(CollectionAgent collectionAgent) {
		try {
			return collectionAgentDAO.save(collectionAgent);
		} catch (DuplicateKeyException duplicateKeyException) {
			LOGGER.info(" Exception occured in update collection agent method in CollectionAgentServiceImpl ");
			LOGGER.info(" Exception message " + duplicateKeyException.getMessage());
			throw new DuplicateValueException(
					MicroFinanceUtil.getFieldNameFromMessage(duplicateKeyException.getMessage()));
		}
	}

	@Override
	public CollectionAgent createCollectionAgent(CollectionAgent collectionAgent) {
		CollectionAgent createdCollectionAgent = null;
		// setting the visibility to active and Id to aadhar number
		collectionAgent.setVisibility(Visibility.ACTIVE);
		collectionAgent.setId(collectionAgent.getAadharNumber());
		try {
			createdCollectionAgent = collectionAgentDAO.save(collectionAgent);
		} catch (DuplicateKeyException duplicateKeyException) {
			LOGGER.info(" Exception occured in Create Collection Agent method in CollectionAgentServiceImpl ");
			LOGGER.info(" Exception message " + duplicateKeyException.getMessage());
			throw new DuplicateValueException(
					MicroFinanceUtil.getFieldNameFromMessage(duplicateKeyException.getMessage()));
		}
		LOGGER.info(" Collection Agent created successfully ");
		return createdCollectionAgent;
	}

	@Override
	public CollectionAgent getCollectionAgentById(String id) {
		Optional<CollectionAgent> optionalCA = collectionAgentDAO.findByIdAndVisibility(id, Visibility.ACTIVE);
		if (!optionalCA.isPresent()) {
			LOGGER.info(" no CA details found for the id " + id);
			throw new UserNotFoundException("No collection agent detail available for id " + id);
		}
		return optionalCA.get();
	}

}
