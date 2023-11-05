package com.example.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.PayLoad.Comment;
import com.example.blog.PayLoad.CommentDto;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.commentRepository;

@Service
public class CommentService {

	@Autowired
	private commentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public String addcomment(int post_id , CommentDto commentdto) {
		
		// create comment
		 
		Comment comment = new Comment();
		comment.setContent(commentdto.getContent());
		comment.setEmail(commentdto.getEmail());
		comment.setUsername(commentdto.getUsername());
		comment.setPost(postRepository.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("post is not found")));
		commentRepository.save(comment);
		
		return "Comment added successfully";
		
	}
	
	public List<CommentDto> getAllComments(int postId)
	{
		List<Comment> comments = commentRepository.findByPostId(postId);
		
		List<CommentDto> returnComments = comments.stream().map(Comment ->{
			CommentDto commentdto = new CommentDto();
			commentdto.setContent(Comment.getContent());
			commentdto.setUsername(Comment.getUsername());
			commentdto.setEmail(Comment.getEmail());
			return commentdto;
		}).collect(Collectors.toList());
		return returnComments;
		
	}
	
}
