package com.example.blog.entity;

import java.util.List;

import com.example.blog.PayLoad.Comment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="posts", uniqueConstraints = 
{@UniqueConstraint(columnNames = 
	{"title"})})
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@Column(name="title",nullable = false)
	private String title;
	
	
	
	@Column(nullable = false)
	private String description;
	
	
	
	@Column(nullable = false)
	private String content;
	
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> postComments;
	

}
