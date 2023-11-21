package com.example.blog.exception;

import org.springframework.http.HttpStatusCode;

public class BlogApplicationException extends RuntimeException {

private HttpStatusCode  code ;
	
	private String message;

	public BlogApplicationException(HttpStatusCode code,String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	
	
}
