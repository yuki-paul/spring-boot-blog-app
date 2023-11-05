package com.example.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsernameOrEmail(String username , String email);
//	
	User findByEmail(String email);
//	
	User findByUsername(String username);
//	
	Boolean existsByUsernameOrEmail(String username, String email);
	
}
