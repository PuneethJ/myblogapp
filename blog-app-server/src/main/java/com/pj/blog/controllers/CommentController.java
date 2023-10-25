package com.pj.blog.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pj.blog.dto.CommentDto;
import com.pj.blog.services.CommentService;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private CommentService commentService;
    
    @GetMapping
    public List<CommentDto> getAllCommenets(){
        return commentService.getAll();
    }

    @DeleteMapping("/{cId}")
    public String deleteComment(@PathVariable("cId") Integer cId){
        commentService.deleteComment(cId);
        return "Comment Deleted";
    }
}

