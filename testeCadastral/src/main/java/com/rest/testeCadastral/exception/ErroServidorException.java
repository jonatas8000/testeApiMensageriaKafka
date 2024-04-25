package com.rest.testeCadastral.exception;

public class ErroServidorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ErroServidorException (String mensagem){
		super(mensagem);
	}

}
