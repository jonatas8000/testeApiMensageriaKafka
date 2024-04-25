package com.rest.testeCadastral.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rest.testeCadastral.DTO.ErroDTO;

@RestControllerAdvice
public class ResourceExceptionHandler  {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroDTO> handleValidationErrors(MethodArgumentNotValidException ex) {
	    ErroDTO erro = new ErroDTO();
	    erro.setErrorException("MethodArgumentNotValidException");
		
		return new ResponseEntity<>(erro, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(ErroServidorException.class)
	public ResponseEntity<ErroDTO> handleErroServidorException(ErroServidorException ex) {
	    ErroDTO erro = new ErroDTO();
	    erro.setErrorException(ex.getMessage());
		
		return new ResponseEntity<>(erro, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
