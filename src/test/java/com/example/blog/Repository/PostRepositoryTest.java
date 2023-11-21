package com.example.blog.Repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.blog.entity.Post;
import com.example.blog.repository.PostRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {

	@Autowired
	private PostRepository postRepository;
	private Post post;
	private Post post1;
	private Post post2;
	
	@BeforeEach
	void setUp() {
		 post = new Post();
		post.setId(100);
		post.setTitle("this is all about spring boot development");
		post.setDescription("rngjkergnvuierghqeuIGHOIAFHOUEA");
		post.setContent("dsafjvnkdjgbnodivnov");
		
		
	    post1 =  new Post();
		post1.setId(1);
		post1.setContent("this good info");
		post1.setTitle("this is great content ");
		post1.setDescription("im greatful for leacture likke you");
		
	    post2 = new Post();
		post2.setId(11);
		post2.setContent("this good info for dummies");
		post2.setTitle("this is great content for all ");
		post2.setDescription("im greatful cox i have you jothika");
		
		
		
	}
//	
	
	
	
	@Test
	void saveTest() {
		// arrange 
		 System.out.println(post1.toString());  
		
		Post check = postRepository.save(post1);
		assertNotNull(check);
		//act
		
		postRepository.save(post);
		postRepository.save(post2);
		
		List<Post> postList = postRepository.findAll();
		
		assertThat(3).isEqualTo(postList.size());
		
		Post dummy = null;
		assertNull(dummy);
		
		
		
		
	}
	
	
	
}
