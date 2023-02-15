package com.blog.blogappapi.repository;

import com.blog.blogappapi.entity.Category;
import com.blog.blogappapi.entity.Post;
import com.blog.blogappapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    @Query("select p from Post p where p.user = ?1")
    List<Post> findByUser(User user);


    @Query("select p from Post p where p.category = ?1")
    List<Post> findByCategory(Category category);


    @Query("select p from Post p where p.title like concat('%', ?1, '%')")
    List<Post> findByTitleContaining(String title);




}

