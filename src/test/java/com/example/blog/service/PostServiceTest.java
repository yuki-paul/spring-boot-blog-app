package com.example.blog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.example.blog.PayLoad.PostDTO;
import com.example.blog.entity.Post;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
	
	@Mock
	private PostRepository postRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	
	@InjectMocks
	private PostService postService;
	
	private Post post;
	
	private PostDTO postdto;
	
	private Post postofid;
	
	
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	
	
//	the work flow should be same as the service layer logic
//	for create method we take input as postdto then convert it to post 
//	then we save then we show the save post my getting postdto and sending
//	
	
	
	
	@Test
	public void postserviceCreateApostTest() {
		//create postdto entity
		postdto = new PostDTO();
		postdto.setContent("hellow this spring boot content");
		postdto.setTitle("spring boot is great");
		postdto.setDescription("hdfbiufqioewufhoerufhoesv");
		
		//create post entity
		post = new Post();
		post.setContent(postdto.getContent());
		post.setDescription(postdto.getDescription());
		post.setTitle(postdto.getTitle());
		
		//telling what to do then the result of it 
		when(modelMapper.map(postdto, Post.class)).thenReturn(post);
		//when we save we want to see the post 
        when(postRepository.save(post)).thenReturn(post);
        //before seeing that converting into postdto
        when(modelMapper.map(post, PostDTO.class)).thenReturn(postdto);
        
        //calling the actual method to cross check 
        PostDTO result = postService.createPost(postdto);
        
        assertNotNull(result);
        assertThat(result.getTitle()).isEqualTo("spring boot is great");

		

		
	}
	
	
	@Test
	void getByIdofPostTest() {
		int postid = 100;
		//input
		postofid = new Post();
		postofid.setId(postid);
		postofid.setContent("dbchbDCIUBEROUFBIERUGVBIERB");
		postofid.setDescription("sdbvhbfiuvbifuvbiubviquvautva");
		postofid.setTitle("asbiucdbsciuciueraciuerfef");
		
		//expected result
		PostDTO expecteddto = new PostDTO();
		expecteddto.setContent(postofid.getContent());
		expecteddto.setId(postofid.getId());
		expecteddto.setDescription(postofid.getDescription());
		expecteddto.setTitle(postofid.getTitle());
		
		when(postRepository.findById(postid)).thenReturn(Optional.of(postofid));
		when(modelMapper.map(postofid, PostDTO.class)).thenReturn(expecteddto);
		
		PostDTO postdtos = postService.getPostById(100);
		
		assertNotNull(postdtos);
		System.out.println(postdtos.toString());
		
		
		assertThat(postdtos.getContent()).isEqualTo(postofid.getContent());
		
	}
	
	@Test
	@DisplayName(" this is xception handling method throws exception")
	void getbyidExceptionTest() {
		int postid = 100;
		//input
		postofid = new Post();
		postofid.setId(postid);
		postofid.setContent("dbchbDCIUBEROUFBIERUGVBIERB");
		postofid.setDescription("sdbvhbfiuvbifuvbiubviquvautva");
		postofid.setTitle("asbiucdbsciuciueraciuerfef");
		
		when(postRepository.findById(200)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, ()-> {
			postService.getPostById(200);
		});
		
		
	}
	
	
	

}
