package com.example.blog.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
//@NamedEntityGraph(name = "User.followersAndFollowing",
//attributeNodes = {
//        @NamedAttributeNode("followers"),
//        @NamedAttributeNode("following")
//}
//)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(nullable = false , unique = true)
	private String email;
	
	@Column(nullable = false , unique = true)
	private String username;
	
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles",
	joinColumns= {@JoinColumn(name = "user_id",referencedColumnName = "id")},
	inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
	private Set<Role> roles;
//	
//	//@OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JsonIgnore
//    @JoinTable(name = "user_followers",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "follower_id")})
//	private Set<User> followers;
	
	//@OneToMany(mappedBy = "followings", cascade = CascadeType.ALL)
//	

//	@ManyToMany(mappedBy = "followers")
//	private Set<User> following;
	
//	private Set<Integer> followerId;
//	
//	private Set<Integer> followingId;
	
	
	
	

}
