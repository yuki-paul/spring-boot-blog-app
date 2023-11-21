package com.example.blog.service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blog.PayLoad.followDto;
import com.example.blog.entity.Follows;
import com.example.blog.entity.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.repository.followRepository;

@Service
public class UserService {
	
	@Autowired
	private followRepository followRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
//	public String follow(int userId, int followingUserId) {
//		User currUser = userRepository.findById(userId)
//				.orElseThrow(()-> new UsernameNotFoundException("user not found"));
//		Follows follows = new Follows(userId,followingUserId);
//	}
	
	public String follow (int userId, int IwantTofollowingId) {
	
		User followerUser = userRepository.findById(userId).get();
		
		User followingId = userRepository.findById(IwantTofollowingId).get();
		
		Follows follow = new Follows();
		
		follow.setFollower(followerUser);
		follow.setFollowing(followingId);
		followRepository.save(follow);
		
		return "user successfully following";
	}
	
	public List<followDto> getFollowers(int userId){
		
		User user = userRepository.findById(userId).get();
		
		List<Follows> followers = followRepository.findAllByFollowingId(userId);
		
	    System.out.println(followers.size());
		
		List<User> users = new ArrayList<>() ;
		
		
		for(Follows follow : followers) {
			
			User check = userRepository.findById(follow.getFollower().getId()).get();
			System.out.println(check.getEmail());
			
			users.add(userRepository.findById(follow.getFollower().getId()).get());
		}
		
		List<followDto> followersList = users.stream().map((curruser) -> {
			followDto follows = new followDto();
			System.out.println(curruser.getEmail());
			follows.setEmail(user.getEmail());
			follows.setUsername(user.getUsername());
			return follows;
		}).collect(Collectors.toList());
		
		return followersList;
		
	}
	
	

}
