package com.spring.microfinance.todo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.microfinance.exception.NoDataFoundException;
import com.spring.microfinance.todo.entity.Todo;
import com.spring.microfinance.todo.repository.TodoDAO;

public class TodoServiceImpl implements TodoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TodoServiceImpl.class);

	@Autowired
	TodoDAO todoDAO;

	@Override
	public Todo createTodo(Todo todo) {
		Todo createdTodo = todoDAO.save(todo);
		LOGGER.info("Todo created successfully " + createdTodo.getId());
		return createdTodo;
	}

	@Override
	public Todo updateTodo(Todo todo) {
		Todo updatedTodo = todoDAO.save(todo);
		LOGGER.info("Todo updated successfully " + updatedTodo.getId());
		return updatedTodo;
	}

	@Override
	public void deleteTodo(String todoId) {
		todoDAO.deleteById(todoId);
	}

	@Override
	public List<Todo> getAllTodo() {
		List<Todo> todos = todoDAO.findAll();
		if (todos.size() == 0) {
			LOGGER.info("No todo details found");
			LOGGER.debug("size of the todo " + todos.size());
			throw new NoDataFoundException("No todo details found");
		}
		LOGGER.debug("size of the todo " + todos.size());
		return todos;
	}

	@Override
	public List<Todo> getTodoByIscompleted(boolean isCompleted) {
		LOGGER.info("Get todo's that have completed flag as " + isCompleted);
		List<Todo> todos = todoDAO.findByIsCompleted(isCompleted);
		if (todos.size() == 0) {
			LOGGER.info("No todo details found");
			LOGGER.debug("size of the todo " + todos.size());
			throw new NoDataFoundException("No todo details found");
		}
		LOGGER.debug("size of the todo " + todos.size());
		return todos;
	}

	@Override
	public List<Todo> getTodoByCollectionAgentId(String collectionAgentId, boolean isCompleted) {
		LOGGER.info(
				"Get todo's for collection agent " + collectionAgentId + " that have completed flag as " + isCompleted);
		List<Todo> todos = todoDAO.findBycollectionAgentIdAndIsCompleted(collectionAgentId, isCompleted);
		if (todos.size() == 0) {
			LOGGER.info("No todo details found");
			LOGGER.debug("size of the todo " + todos.size());
			throw new NoDataFoundException("No todo details found");
		}
		LOGGER.debug("size of the todo " + todos.size());
		return todos;
	}

}
