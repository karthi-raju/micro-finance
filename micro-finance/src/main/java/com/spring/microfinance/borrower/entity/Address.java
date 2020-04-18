package com.spring.microfinance.borrower.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {
	
	private String addressLine1;

	private String addressLine2;

	private String landMark;

	private String zipcode;

	private String locality;
}
