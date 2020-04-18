package com.spring.microfinance.borrower.service;

import java.util.List;

import com.spring.microfinance.borrower.entity.Borrower;

public interface BorrowerService {

	public Borrower getBorrower(String BorrowerId);

	public List<Borrower> getAllBorrower();

	public void deleteBorrower(String BorrowerId);

	public Borrower updateBorrower(Borrower Borrower);

	public Borrower createBorrower(Borrower Borrower);

}
