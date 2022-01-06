package com.project.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class InvalidIdException extends Exception{
	
	public InvalidIdException(String str)
	{
		super(str);
		
	}
	

}
