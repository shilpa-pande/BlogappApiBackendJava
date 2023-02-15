package com.blog.blogappapi.services;

import com.blog.blogappapi.entity.Post;
import com.blog.blogappapi.payloads.PostDto;
import com.blog.blogappapi.payloads.PostResponse;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface PostService {


    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);
    void deletePost(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);



    List<PostDto> searchPosts(String keyword);



}
