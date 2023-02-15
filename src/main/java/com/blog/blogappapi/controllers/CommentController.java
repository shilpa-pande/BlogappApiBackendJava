package com.blog.blogappapi.controllers;

import com.blog.blogappapi.payloads.ApiResponse;
import com.blog.blogappapi.payloads.CommentDto;
import com.blog.blogappapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.2")
public class CommentController {


    @Autowired
    private CommentService commentService;


    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto createComment=this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }


    @DeleteMapping("/comments/{commentsId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
       this.commentService.deletComment(commentId);
        return new ResponseEntity<>(new ApiResponse("deleted successfully", true), HttpStatus.OK);
    }


}
