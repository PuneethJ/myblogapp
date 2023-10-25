package com.pj.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.blog.dto.CommentDto;
import com.pj.blog.entities.Comment;
import com.pj.blog.exceptions.ResourceNotFoundException;
import com.pj.blog.repositories.CommentRepo;



@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    public List<CommentDto> getAll() {
        List<Comment> comments= commentRepo.findAll();
        return comments.stream().map(comment->commentToDto(comment)).toList();
    }

    public void deleteComment(Integer cId){
        Comment comment= commentRepo.findById(cId).orElseThrow(()->new ResourceNotFoundException(cId));
        commentRepo.delete(comment);
    }

    private CommentDto commentToDto(Comment comment){
        CommentDto commentDto= new CommentDto();
        commentDto.setContent(comment.getContent());
        return commentDto;
    }

    // private Comment dtoToComment(CommentDto commentDto){
    //     Comment comment= new Comment();
    //     comment.setContent(commentDto.getContent());
    //     return comment;
    // }
}
