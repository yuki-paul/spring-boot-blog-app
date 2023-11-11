package com.example.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.PayLoad.followDto;
import com.example.blog.service.UserService;

@RestController
@RequestMapping("/user/follow")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/add/{userId}/following/{followingId}")
	public String followRequest(@PathVariable("userId") int userId,
			@PathVariable("followingId") int followingId) {
		userService.follow(userId, followingId);
		return "you are now following the user";
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<followDto> getFollowers(@PathVariable("id") int id){
		return userService.getFollowers(id);
		
	}
	
	
	
}
