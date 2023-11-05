package com.example.blog.PayLoad;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

	@Email
	private String email;
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	@Size(min=1 , message="mini 1 Char required")
	private String content;
}
