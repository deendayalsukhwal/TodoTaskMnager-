package com.todo.service.imp;

import java.util.List; 
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.todo.dto.TodoDto;
import com.todo.entity.Todo;
import com.todo.exception.TodoNotFoundException;
import com.todo.repository.TodoRespository;
import com.todo.service.TodoService;

import lombok.AllArgsConstructor;
 

@Service
@AllArgsConstructor
public class TodoServiceImp implements TodoService {

	private TodoRespository todoRespository;
	private ModelMapper modelMapper;
	
	// insert TODO into database
	@Override
	public TodoDto createTodo(TodoDto todoDto) {
	 
		//convert DTO into JPA
		Todo todo = modelMapper.map(todoDto,Todo.class);
		
		//save into database
		Todo saveTodo = todoRespository.save(todo);
		
 
		//convert JPA into DTO
		TodoDto saveDto = modelMapper.map(saveTodo, TodoDto.class);
		
		
		return saveDto;
	}

	//Get TODO From database
	@Override
	public TodoDto geTodoDto(Long id) {
		 
		Todo optionaltodo = todoRespository.findById(id)
				.orElseThrow(()->new TodoNotFoundException("Todo not found with id "+id));
		
		//convert JPA into DTO
		TodoDto getTodoDto = modelMapper.map(optionaltodo, TodoDto.class);
		
		return getTodoDto;
	}

	// get All TODO from database
	@Override
	public List<TodoDto> getAllTodo() {
		
		List<Todo> optional = todoRespository.findAll();
		
		//convert JPA into DTO
		return optional.stream().map((todo)->modelMapper.map(todo, TodoDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto) {
		
		
		Todo exstingTodo = todoRespository.findById(todoDto.getId())
				.orElseThrow(()-> new TodoNotFoundException("Todo not found with id:"+todoDto.getId()));
		exstingTodo.setCompleted(todoDto.isCompleted());
		exstingTodo.setDescription(todoDto.getDescription());
		exstingTodo.setTitle(todoDto.getTitle());
		
		Todo save = todoRespository.save(exstingTodo);
		//convert JPA into DTO
		TodoDto saveDto = modelMapper.map(save, TodoDto.class);
	
		return saveDto; 	
	}

	@Override
	public void deleteTodo(Long id) {
		
		 todoRespository.findById(id)
				.orElseThrow(()-> new TodoNotFoundException("Todo not found with id:"+id));
		todoRespository.deleteById(id);
	}

	@Override
	public TodoDto completeTodo(Long id) {
	Todo todo = todoRespository.findById(id)
			.orElseThrow(()-> new TodoNotFoundException("Todo not found with id: "+id));
	
	todo.setCompleted(true);
	Todo saveTodo =  todoRespository.save(todo);
	return modelMapper.map(saveTodo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo(Long id) {
	Todo todo = todoRespository.findById(id)
			.orElseThrow(()->new TodoNotFoundException("Todo not found with id : "+id));
	todo.setCompleted(false);
	Todo saveTodo =todoRespository.save(todo);

		return modelMapper.map(saveTodo, TodoDto.class);
	}

}
