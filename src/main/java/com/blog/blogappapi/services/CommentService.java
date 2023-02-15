package com.blog.blogappapi.services;

import com.blog.blogappapi.entity.Comment;
import com.blog.blogappapi.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deletComment(Integer commentId);
}
