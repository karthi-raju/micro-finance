package com.spring.microfinance.loan.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.spring.microfinance.util.Visibility;

import lombok.Data;

@Data
@Document(value = "loan")
public class Loan {

	@Id
	private String id;

	private String borrowerId;

	private String loanAmount;

	private Date targetedRepaymentDate;

	private float intrestRate;

	private int totalInstallment;

	private int installmentInterval;

	private int pendingInstallment;

	private float pendingAmount;

	private Date loanStartDate;

	private String assetPledged;

	private String description;

	private float assetPledgedValue;

	private float collectedSoFar;

	private String collectionAgentId;

	private Visibility visibility;
}
