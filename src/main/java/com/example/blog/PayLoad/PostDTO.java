package com.example.blog.PayLoad;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDTO {
	
	private int id;
	
	@NotEmpty
	@Size(min = 2, message = "title should be mini 2 characters")
	private String title;
	
	@NotEmpty
	@Size(min = 10, message = "description should be mini 10 characters")
	private String description;
	
	private String content;
	
	private List<CommentDto> comments;
	

}
