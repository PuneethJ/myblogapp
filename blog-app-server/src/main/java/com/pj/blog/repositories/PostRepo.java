package com.pj.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pj.blog.entities.Post;

public interface PostRepo extends JpaRepository<Post,Integer>{
    
}
