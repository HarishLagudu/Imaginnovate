package com.example.demo.exception;

public class NoSuchEmployeeExistsException extends RuntimeException {
 
    
	private static final long serialVersionUID = 1L;
	private String message;
 
    public NoSuchEmployeeExistsException() {}
 
    public NoSuchEmployeeExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
