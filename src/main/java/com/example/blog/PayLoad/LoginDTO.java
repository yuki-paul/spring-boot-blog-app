package com.example.blog.PayLoad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
	
	private String UsernameOrEmail;
	
	private String password;

}
