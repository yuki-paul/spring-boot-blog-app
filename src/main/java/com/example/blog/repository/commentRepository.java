package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.PayLoad.Comment;

public interface commentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPostId(int postId);
}
