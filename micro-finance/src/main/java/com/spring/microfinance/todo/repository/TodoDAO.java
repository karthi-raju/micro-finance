package com.spring.microfinance.todo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.microfinance.todo.entity.Todo;

public interface TodoDAO extends MongoRepository<Todo, String> {

	List<Todo> findByIsCompleted(boolean isCompleted);

	List<Todo> findBycollectionAgentIdAndIsCompleted(String collectionAgentId, boolean isCompleted);

}
