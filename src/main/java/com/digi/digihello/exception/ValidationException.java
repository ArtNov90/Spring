package com.digi.digihello.exception;
import java.lang.String;


public class ValidationException extends RuntimeException {
	// Constructeur avec un message d'erreur
	public ValidationException(String message) {
		super(message);
	}
}
