package com.superheros.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler.
 * @author Victor
 *
 */
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	/**
	 * Custom exception when resource not found.
	 * @param exception {@link ResourceNotFoundException}.
	 * @param request {@link WebRequest}.
	 * @return Custom exception.
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("status", HttpStatus.NOT_FOUND.value());
		response.put("message", exception.getMessage());
		response.put("path", request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
