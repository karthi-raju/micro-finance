package com.spring.microfinance.todo.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.spring.microfinance.util.TypeEnum;

import lombok.Data;

@Data
public class Todo {

	@Id
	private String id;

	private String loanId;

	private TypeEnum typeEnum;

	private float amount;

	private float penalty;

	private float collectionFee;

	private String collectionAgentId;

	private Date completedDate;

	private short collectionRetryCount;

	private String comments;

	private boolean isCompleted;
}
