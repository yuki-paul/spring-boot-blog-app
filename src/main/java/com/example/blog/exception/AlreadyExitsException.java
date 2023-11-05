package com.example.blog.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AlreadyExitsException extends Exception {
	
	private String username;
	
	private String email;
	
	private String message;
	
	public AlreadyExitsException( String username , String email ) {
		this.username = username;
		
		this.email = email;
		
		this.message = String.format("%s and %s already existed retry",username,email);
	}
	

}
