package com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {
	
	

}
