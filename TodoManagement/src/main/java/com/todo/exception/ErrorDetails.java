package com.todo.exception;
 
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
	
	private LocalDateTime timestamp;
	private String path;
	private String errorCode;
	private String status;
	
}
