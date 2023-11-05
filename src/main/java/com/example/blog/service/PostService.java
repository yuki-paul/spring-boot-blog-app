package com.example.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.blog.PayLoad.CommentDto;
import com.example.blog.PayLoad.PostDTO;
import com.example.blog.PayLoad.PostResponse;
import com.example.blog.entity.Post;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.PostRepository;

@Service
public class PostService {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepository postRepo;
	
	public PostDTO createPost(PostDTO postdto) {
		//convert dto to entity
		Post post = mapToPost(postdto);
		postRepo.save(post);
		
		// convert entity to dto
		PostDTO postResponse = mapToDto(post);
		return postResponse;
	}
		
		
		public PostDTO mapToDto(Post post) {
			PostDTO postdto = modelMapper.map(post, PostDTO.class);
//			postdto.setId(post.getId());
//			postdto.setContent(post.getContent());
//			postdto.setDescription(post.getDescription());
//			postdto.setTitle(post.getTitle());
//			postdto.setComments(post.getPostComments());
			return postdto;
		}

		public Post mapToPost(PostDTO postdto) {
			Post post = modelMapper.map(postdto, Post.class);
//			post.setId(postdto.getId());
//			post.setTitle(postdto.getTitle());
//			post.setContent(postdto.getContent());
//			post.setDescription(postdto.getDescription());
			return post;
		}
		
		public PostResponse getAllPosts(int pageNo,int pageSize , String sortBy , String sortDir ){
			
			//create sort instance if sortDir == asc means asc else dsc
			Sort sort = sortDir
					.equalsIgnoreCase(Sort.Direction.ASC.name()) 
					? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
			
			// create a pagable instance 
			Pageable pageable = PageRequest.of(pageNo, pageSize , sort);
			
			Page<Post> allPosts = postRepo.findAll(pageable);
			
			List<Post> listOfPost = allPosts.getContent();
			
			//List<CommentDto> dtoComment = allPosts.getContent().			
			List<PostDTO> res =  listOfPost.stream().map((post) -> mapToDto(post)).collect(Collectors.toList());
			
			PostResponse postResponse = new PostResponse();
			
			postResponse.setContent(res);
			postResponse.setPageNo(allPosts.getNumber());
			postResponse.setPageSize(allPosts.getSize());
			postResponse.setTotalElements(allPosts.getTotalElements());
			postResponse.setTotalPages(allPosts.getTotalPages());
			postResponse.setLast(allPosts.isLast());
			return postResponse;
					
		}
		

		public PostDTO getPostById(int id) {
			Post post = postRepo.findById(id).orElseThrow(()->   new ResourceNotFoundException("post","id",id));
			return mapToDto(post);
		}
		
		public PostDTO updatePost(int id,PostDTO postdto) {
			Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("the post you are looking is not here"));
			post.setContent(postdto.getContent());
			post.setDescription(postdto.getDescription());
			post.setTitle(postdto.getTitle());
			
			postRepo.save(post);
			return mapToDto(post);	
		}
		
		public String deletebyId(int id) {
			Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("the post you are looking is not here"));
		    if(post==null) {
		    	return "Post is already deleted";
		    }
		    postRepo.deleteById(id);
		    return "the post deleted successfully";
		
		}

}
