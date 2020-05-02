package com.spring.microfinance.collectionagent.entity;

import java.util.Date;

import javax.persistence.Embedded;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.spring.microfinance.borrower.entity.Address;
import com.spring.microfinance.util.Visibility;

import lombok.Data;

@Data
@Document(collection = "collection_agent")
public class CollectionAgent {

	@Id
	private String id;

	@Indexed(unique = true)
	private String aadharNumber;

	@Indexed(unique = true)
	private String mobileNumber;

	@Indexed(unique = true)
	private String drivingLicenseNumber;

	private String name;

	@Embedded
	private Address address;

	private Date joiningDate;

	private float salary;

	private Visibility visibility;

}
