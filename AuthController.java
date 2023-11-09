package com.example.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.PayLoad.LoginDTO;
import com.example.blog.PayLoad.RegisterDTO;
import com.example.blog.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authservice;
	
	
	@PostMapping("/login")
	public ResponseEntity<String> loginService(@RequestBody LoginDTO logindto) {
	  String response = authservice.login(logindto);
	  return ResponseEntity.ok(response);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registetService(@RequestBody RegisterDTO registerDTO) throws Exception{
		String response = authservice.register(registerDTO);
		return ResponseEntity.ok(response);
	}

}
