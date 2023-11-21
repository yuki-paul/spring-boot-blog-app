package com.example.blog.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.PayLoad.JWTAuthResponse;
import com.example.blog.PayLoad.LoginDTO;
import com.example.blog.PayLoad.RegisterDTO;
import com.example.blog.Security.JwtTokenProvider;
import com.example.blog.entity.Role;
import com.example.blog.entity.User;
import com.example.blog.exception.AlreadyExitsException;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;

@Service
public class AuthService {
	
	private AuthenticationManager authenticationManager;
	
	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private PasswordEncoder passwordEncoder;
	
	private JwtTokenProvider jwtTokenProvider;
	
	
	AuthService(AuthenticationManager authenticationManager
			,UserRepository userRepository 
			,RoleRepository roleRepository
			,PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider){
		this.authenticationManager=authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	public JWTAuthResponse login(LoginDTO logindto) {
		System.out.println(logindto.getUsernameOrEmail() +" "+logindto.getUsernameOrEmail());
	  Authentication authentication=	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logindto.getUsernameOrEmail(), logindto.getPassword()));
	
	      System.out.println("ssssss : "+authentication);
		  SecurityContextHolder.getContext().setAuthentication(authentication);
		  String token = jwtTokenProvider.generateToken(authentication);
		  
		  JWTAuthResponse jwtresponse = new JWTAuthResponse();
		  jwtresponse.setAccessToken(token);
		  return jwtresponse;
	  
	}
	
	public String register(RegisterDTO registerdto) throws AlreadyExitsException {
		
		if(userRepository.existsByUsernameOrEmail(registerdto.getUsername(), registerdto.getEmail())) {
			throw new AlreadyExitsException(registerdto.getUsername(), registerdto.getPassword());
		}
		
		User user = new User();
		user.setName(registerdto.getName());
		user.setEmail(registerdto.getEmail());
		user.setName(registerdto.getName());
		user.setPassword(passwordEncoder.encode(registerdto.getPassword()));
		user.setUsername(registerdto.getUsername());
		
		
		Set<Role> roles =  new HashSet<>();
		
		System.out.println(user.toString());
		
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);
		System.out.println(user.getRoles().toString());
		System.out.println(user.toString());
		userRepository.save(user);
		
		return "User registered successfully";
		
		
		
		
	}
	

}
