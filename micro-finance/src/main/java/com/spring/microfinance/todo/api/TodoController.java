package com.spring.microfinance.todo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.microfinance.todo.entity.Todo;
import com.spring.microfinance.todo.service.TodoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/v1/todo")
public class TodoController {

	@Autowired
	TodoService todoServiceImpl;

	@ApiOperation(value = "Creates a Todo")
	@PostMapping
	public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
		return new ResponseEntity<Todo>(todoServiceImpl.createTodo(todo), HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieves all todo")
	@GetMapping
	public ResponseEntity<List<Todo>> getAllTodo() {
		return new ResponseEntity<List<Todo>>(todoServiceImpl.getAllTodo(), HttpStatus.OK);
	}

	@ApiOperation(value = "Updates todo")
	@PutMapping
	public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
		return new ResponseEntity<Todo>(todoServiceImpl.updateTodo(todo), HttpStatus.OK);
	}

	@ApiOperation(value = "Removes the entry from DB, using todo id")
	@DeleteMapping(value = "/{todoId}")
	public ResponseEntity<HttpStatus> deleteBorrower(@PathVariable String todoId) {
		todoServiceImpl.deleteTodo(todoId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieves all todo, based on completed flag")
	@GetMapping(value = "/{isCompleted}")
	public ResponseEntity<List<Todo>> getAllTodoByIscompleted(@PathVariable boolean isCompleted) {
		return new ResponseEntity<List<Todo>>(todoServiceImpl.getTodoByIscompleted(isCompleted), HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieves all todo, based on collection agent id and completed flag")
	@GetMapping(value = "/{caId}/{isCompleted}")
	public ResponseEntity<List<Todo>> getAllTodoByCollectionAgentAndIscompleted(@PathVariable String caId,
			@PathVariable boolean isCompleted) {
		return new ResponseEntity<List<Todo>>(todoServiceImpl.getTodoByCollectionAgentId(caId, isCompleted),
				HttpStatus.OK);
	}

}
