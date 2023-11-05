package com.example.blog.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.PayLoad.LoginDTO;
import com.example.blog.PayLoad.RegisterDTO;
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
	
	
	AuthService(AuthenticationManager authenticationManager
			,UserRepository userRepository 
			,RoleRepository roleRepository
			,PasswordEncoder passwordEncoder){
		this.authenticationManager=authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public String login(LoginDTO logindto) {
		
	  Authentication authentication=	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logindto.getUsernameOrEmail(), logindto.getPassword()));
	
	  SecurityContextHolder.getContext().setAuthentication(authentication);
	  
	  return "user is successfully logged-in";
	  
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
		
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
		
		return "User registered successfully";
		
		
		
		
	}
	

}
