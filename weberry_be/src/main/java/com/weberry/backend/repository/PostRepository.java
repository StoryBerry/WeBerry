package com.weberry.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weberry.backend.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	Post findFirstByUserUseridOrderByIdDesc(String userid);
	
	List<Post> findAllByOrderByIdDesc();
	
	Post findById(long id);
	
}
