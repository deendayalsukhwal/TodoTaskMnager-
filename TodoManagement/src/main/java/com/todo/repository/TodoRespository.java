package com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.entity.Todo;

public interface TodoRespository extends JpaRepository<Todo, Long> {

}
