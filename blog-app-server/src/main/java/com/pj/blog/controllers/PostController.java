package com.pj.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pj.blog.dto.PostDto;
import com.pj.blog.services.PostServices;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    
    @Autowired
    private PostServices postServices;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@RequestBody PostDto postDto){
        return postServices.createPost(postDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getAllPosts(){
        return postServices.getAllPosts();
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public String deletePost(@PathVariable("postId") Integer pId){
        String msg=postServices.deletePost(pId);;
        return msg;
    }

    @PutMapping("/{postId}")
    public PostDto editPost(@RequestBody PostDto postDto ,@PathVariable("postId") Integer pId){
        return postServices.editPost(postDto, pId);
    }

}
