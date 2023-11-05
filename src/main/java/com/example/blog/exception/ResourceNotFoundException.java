package com.example.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ResponseStatus(value=HttpStatus.NOT_FOUND)
@ToString
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	
	private String resourceName;
	
	private String feildName;
	
	private int  feildValue;
	public ResourceNotFoundException(String resourceName,String feildName,int feildValue) {
		super(String.format("%s not found with %s : '%s'" ,resourceName,feildName,feildValue));
		this.resourceName = resourceName;
		this.feildName = feildName;
		this.feildValue = feildValue;
		this.message = (String.format("%s not found with %s : '%s'" ,resourceName,feildName,feildValue));
	}
	public ResourceNotFoundException(String message) {
		this.message = message;
	}
	
}
