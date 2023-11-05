package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.PayLoad.PostDTO;
import com.example.blog.PayLoad.PostResponse;
import com.example.blog.PayLoad.ResponseDTO;
import com.example.blog.service.PostService;
import com.example.blog.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("post")
public class PostController {
	
	
	
	@Autowired
	private PostService postservice;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDto) {
		return new ResponseEntity<PostDTO>(postservice.createPost(postDto),HttpStatus.CREATED);
	}
	@GetMapping("/all")
	public ResponseEntity<PostResponse> getAll(
			@RequestParam(value = "pageNo" , defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo , 
			@RequestParam(value="pageSize" , defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value="sortBy" , defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy ,
			@RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION , required = false) String sortDir
			){
		return new ResponseEntity<PostResponse>(postservice.getAllPosts(pageNo, pageSize,sortBy , sortDir),HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getByID(@PathVariable("id") int postId){
		
		return new ResponseEntity<PostDTO>(postservice.getPostById(postId),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseDTO updateById(@PathVariable("id") int postId,@RequestBody PostDTO postdto){
		ResponseDTO response = new ResponseDTO(  postservice.updatePost(postId, postdto),HttpStatus.OK, "the values updated sucessful");
		return response;
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") int id){
		postservice.deletebyId(id);
		return new ResponseEntity<String>("deleted successfully",HttpStatus.OK);
	}
}
