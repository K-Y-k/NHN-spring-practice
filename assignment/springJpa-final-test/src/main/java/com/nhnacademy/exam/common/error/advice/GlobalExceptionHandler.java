package com.nhnacademy.exam.common.error.advice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nhnacademy.exam.department.exception.DepartmentDuplicateException;
import com.nhnacademy.exam.department.exception.DepartmentNotFoundException;
import com.nhnacademy.exam.department.exception.EmptyParameterException;
import com.nhnacademy.exam.department.exception.MissingParameterException;
import com.nhnacademy.exam.common.error.dto.GlobalErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DepartmentDuplicateException.class)
	public ResponseEntity<?> handleDuplicateException(Exception ex) {
		GlobalErrorResponse body = new GlobalErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(Exception ex) {
		GlobalErrorResponse body = new GlobalErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());

		HttpHeaders headers = new HttpHeaders();
		headers.setConnection("keep-alive");
		headers.add("Keep-Alive", "timeout=60");

		return new ResponseEntity<>(body, headers, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MissingParameterException.class)
	public ResponseEntity<?> handleMissingParameterException(Exception ex) {
		GlobalErrorResponse body = new GlobalErrorResponse(
			"Required request parameter '" + ex.getMessage() + "' for method parameter type String is not present",
			HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyParameterException.class)
	public ResponseEntity<?> handleEmptyParameterException(Exception ex) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("title", "Required request parameter '" + ex.getMessage() + "' for method parameter type String is not present");
		body.put("status", 400);
		body.put("timestamp", LocalDateTime.now());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

}
