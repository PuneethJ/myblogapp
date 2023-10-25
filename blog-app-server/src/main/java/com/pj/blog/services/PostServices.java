package com.pj.blog.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.blog.dto.PostDto;
import com.pj.blog.entities.Post;
import com.pj.blog.exceptions.ResourceNotFoundException;
import com.pj.blog.repositories.PostRepo;

@Service
public class PostServices {
    @Autowired
    private PostRepo postRepo;

    public PostDto createPost(PostDto postDto) {
        Post post= dtoToPost(postDto);
        postRepo.save(post);
        return postToDto(post);
        
    }

    public List<PostDto> getAllPosts() {
        List<Post> posts= postRepo.findAll();
        List<PostDto> postDtos= posts.stream().map(post->postToDto(post)).toList();
        return postDtos;
    }

     public String deletePost(Integer pId) {
        Post post= postRepo.findById(pId).orElseThrow(()->new ResourceNotFoundException(pId));
        postRepo.delete(post);
        return "Post deleted";
    }

    public PostDto editPost(PostDto postDto, Integer pId){
         Post post= postRepo.findById(pId).orElseThrow(()->new ResourceNotFoundException(pId));
         post.setTitle(postDto.getTitle());
         post.setContent(postDto.getContent());
         Post editedPost= postRepo.save(post);
         return postToDto(editedPost);
    }

    private Post dtoToPost(PostDto postDto){
        Post post= Post.builder().postId(postDto.getPostId())
                    .title(postDto.getTitle())
                    .content(postDto.getContent())
                    .imageName(postDto.getImageName())
                    .addedDate(new Date()).build();
        return post;
    }

    private PostDto postToDto(Post post){
        PostDto postDto= PostDto.builder().postId(post.getPostId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .imageName(post.getImageName())
                        .addedDate(post.getAddedDate()).build();
    return postDto;

    }

   

    
}
