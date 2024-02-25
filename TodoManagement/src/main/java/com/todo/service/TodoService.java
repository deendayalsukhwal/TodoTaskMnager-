package com.todo.service;

import java.util.List;

import com.todo.dto.TodoDto;

public interface TodoService {
	
	TodoDto createTodo(TodoDto todoDto);
	TodoDto geTodoDto(Long id);
	List<TodoDto> getAllTodo();	
	TodoDto updateTodo(TodoDto todoDto);
	void deleteTodo(Long id);
	TodoDto completeTodo(Long id);
	TodoDto inCompleteTodo(Long id);
	
}
