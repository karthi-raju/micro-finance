package com.spring.microfinance.borrower.service;

import java.util.List;

import com.spring.microfinance.borrower.entity.Borrower;

public interface BorrowerService {

	public Borrower getBorrowerByMobile(String mobileNumber);

	public List<Borrower> getAllBorrower();

	public void deleteBorrower(String id);

	public Borrower updateBorrower(Borrower Borrower);

	public Borrower createBorrower(Borrower Borrower);

	public Borrower getBorrowerById(String id);

}
