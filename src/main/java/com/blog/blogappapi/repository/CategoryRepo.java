package com.blog.blogappapi.repository;

import com.blog.blogappapi.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
