package com.example.blog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.blog.entity.Follows;

public interface followRepository extends JpaRepository<Follows, Integer>{

	
//	@Query("select f from follows f where f.following.id = :id")
//	List<Follows> findFollowersByFollowingId(@Param("id") int id);
	
	List<Follows> findAllByFollowingId(int id);
	
	
}
