package com.spring.microfinance.todo.service;

import java.util.List;

import com.spring.microfinance.todo.entity.Todo;

public interface TodoService {

	public Todo createTodo(Todo todo);

	public Todo updateTodo(Todo todo);

	public void deleteTodo(String todoId);

	public List<Todo> getAllTodo();

	public List<Todo> getTodoByIscompleted(boolean isCompleted);

	public List<Todo> getTodoByCollectionAgentId(String collectionAgentId, boolean isCompleted);

}
