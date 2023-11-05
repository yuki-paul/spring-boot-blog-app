package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.PayLoad.CommentDto;
import com.example.blog.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts/comment")
public class CommentController {
	
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/{postId}/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String addingComment(@PathVariable(value = "postId") int postId, @Valid @RequestBody CommentDto commentDto) {
		return commentService.addcomment(postId, commentDto);
		
	}
	
	@GetMapping("/{postId}/get/all")
	public List<CommentDto> getAllComments(@PathVariable(value="postId") int postId){
		return commentService.getAllComments(postId);
	}

}
