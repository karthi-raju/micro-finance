package com.spring.microfinance.loan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.microfinance.loan.entity.Loan;
import com.spring.microfinance.util.Visibility;

@Repository
public interface LoanDAO extends MongoRepository<Loan, String> {

	List<Loan> findByVisibility(Visibility visibility);

	Optional<Loan> findByIdAndVisibility(String loanId, Visibility visibility);

	List<Loan> findByBorrowerIdAndVisibility(String borrowerId, Visibility visibility);

}
