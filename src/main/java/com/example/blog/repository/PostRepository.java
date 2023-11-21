package com.example.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	Optional<Post> findByTitleOrId(String title , int id);
	
}
