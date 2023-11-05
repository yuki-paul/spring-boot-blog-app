package com.example.blog.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.blog.PayLoad.ErrorDetail;

@ControllerAdvice
public class GlobalExceptionHandler  {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetail> handleResourceNotfound(ResourceNotFoundException
			exception, WebRequest webRequest){
	 ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage()
			 ,webRequest.getDescription(false));
	 return new ResponseEntity<ErrorDetail>(errorDetail,HttpStatus.NOT_FOUND);
	 
	}
	
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//		
//		Map<String , String > errors = new HashMap();
//		
//		ex.getBindingResult().getAllErrors().forEach((error)->{
//			
//			String fieldName=((FieldError) error).getField();
//			String message = ((FieldError) error).getDefaultMessage();
//			errors.put(fieldName,message);
//		});
		
//		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//	}
//	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException
			ex, WebRequest webRequest){
Map<String , String > errors = new HashMap();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			
			String fieldName=((FieldError) error).getField();
			String message = ((FieldError) error).getDefaultMessage();
			errors.put(fieldName,message);
		});
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetail> handleAccessDeniedException(AccessDeniedException
			exception, WebRequest webRequest){
	 ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage()
			 ,webRequest.getDescription(false));
	 return new ResponseEntity<ErrorDetail>(errorDetail,HttpStatus.UNAUTHORIZED);
	 
	}
	
	@ExceptionHandler(AlreadyExitsException.class)
	public ResponseEntity<ErrorDetail> handleResourceNotfound(AlreadyExitsException
			exception, WebRequest webRequest){
	 ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage()
			 ,webRequest.getDescription(false));
	 return new ResponseEntity<ErrorDetail>(errorDetail,HttpStatus.BAD_REQUEST);
	 
	}
	 
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetail> handleResourceNotfound(Exception
			exception, WebRequest webRequest){
	 ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage()
			 ,webRequest.getDescription(false));
	 return new ResponseEntity<ErrorDetail>(errorDetail,HttpStatus.INTERNAL_SERVER_ERROR);
	 
	}
	
	
	
}
