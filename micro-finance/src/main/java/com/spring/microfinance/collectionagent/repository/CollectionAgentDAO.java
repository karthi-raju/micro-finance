package com.spring.microfinance.collectionagent.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.microfinance.collectionagent.entity.CollectionAgent;
import com.spring.microfinance.util.Visibility;

@Repository
public interface CollectionAgentDAO extends MongoRepository<CollectionAgent, String> {

	Optional<CollectionAgent> findByMobileNumberAndVisibility(String mobileNumber, Visibility visibility);

	List<CollectionAgent> findByVisibility(Visibility visibility);

	Optional<CollectionAgent> findByDrivingLicenseNumberAndVisibility(String drivingLicenseNumber,
			Visibility visibility);

	Optional<CollectionAgent> findByIdAndVisibility(String borrowerId, Visibility visibility);
}
