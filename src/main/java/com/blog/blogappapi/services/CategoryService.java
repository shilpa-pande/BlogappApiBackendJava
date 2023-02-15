package com.blog.blogappapi.services;

import com.blog.blogappapi.payloads.CategoryDto;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface CategoryService {

     CategoryDto createCategory(CategoryDto categoryDto);

     CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

     void deleteCategory(Integer categoryId);


     CategoryDto getCategory(Integer categoryId);

     List<CategoryDto> getCategories();
}
