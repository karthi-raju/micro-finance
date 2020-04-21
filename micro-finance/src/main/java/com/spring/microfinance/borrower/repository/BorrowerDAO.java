package com.spring.microfinance.borrower.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.microfinance.borrower.entity.Borrower;
import com.spring.microfinance.util.Visibility;

@Repository
public interface BorrowerDAO extends MongoRepository<Borrower, String> {

	Optional<Borrower> findByMobileNumberAndVisibility(String mobileNumber, Visibility visibility);

	List<Borrower> findByVisibility(Visibility visibility);

	Optional<Borrower> findByIdAndVisibility(String borrowerId, Visibility visibility);

}
