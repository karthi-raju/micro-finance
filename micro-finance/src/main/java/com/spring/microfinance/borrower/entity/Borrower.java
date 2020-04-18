package com.spring.microfinance.borrower.entity;

import javax.persistence.Embedded;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.spring.microfinance.util.Visibility;

import lombok.Data;

@Data
@Document(collection="borrower")
public class Borrower {

	private String name;

	@Indexed(unique=true)
	private String aadharNumber;

	@Indexed(unique=true)
	private String mobileNumber;

	@Embedded
	private Address address;

	private String referenceId;

	private float creditScore;

	private float intrestLimit;
	
	private float borrowerLimit;

	private Visibility visibility;

}
