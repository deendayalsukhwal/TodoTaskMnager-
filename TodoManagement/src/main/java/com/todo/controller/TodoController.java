package com.todo.controller;
 

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.dto.TodoDto;
import com.todo.service.TodoService;

import lombok.AllArgsConstructor;
 
@RestController
@AllArgsConstructor
@RequestMapping("API/todo")
public class TodoController {
	
	private TodoService todoService;
	
	//Create todo rest Api
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto)
	{
		TodoDto saveTodoDto =todoService.createTodo(todoDto);
		return new ResponseEntity<>(saveTodoDto,HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long id)
	{
		TodoDto todoDto = todoService.geTodoDto(id);
		return new ResponseEntity<>(todoDto,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<TodoDto>> GetAllTodo()
	{
		List<TodoDto> todoDto = todoService.getAllTodo();
		return new ResponseEntity<>(todoDto,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long id, @RequestBody TodoDto todoDto)
	{
		todoDto.setId(id);
		TodoDto updateTodo = todoService.updateTodo(todoDto);
		
	return new ResponseEntity<>(updateTodo,HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable Long id)
	{
		 todoService.deleteTodo(id);
		return new ResponseEntity<>("Todo deleted Succesfully !",HttpStatus.OK);
	}
	@PatchMapping("{id}/complete")
	public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id)
	{
	 TodoDto todoDto =	todoService.completeTodo(id);
	 return ResponseEntity.ok(todoDto);
	}
	@PatchMapping("{id}/inComplete")
	public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable Long id)
	{
	 TodoDto todoDto =	todoService.inCompleteTodo(id);
	 return ResponseEntity.ok(todoDto);
	}
}
