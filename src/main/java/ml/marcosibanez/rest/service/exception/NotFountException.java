package ml.marcosibanez.rest.service.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import ml.marcosibanez.rest.service.dto.ErrorDTO;


public class NotFountException extends MensajeException{

	private static final long serialVersionUID = 1L;

	public NotFountException(String code, String message) {
		super(code,HttpStatus.NOT_FOUND.value(),message);
	}
	
	public NotFountException(String code, String message, ErrorDTO data) {
		super(code,HttpStatus.NOT_FOUND.value(),message, Arrays.asList(data));
	}

}