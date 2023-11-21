package com.example.blog.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		System.out.println(passwordEncoder.encode("yugesh"));
		System.out.println(passwordEncoder.encode("yuki"));
		System.out.println(passwordEncoder.encode("Jothika@30"));

	}

}
