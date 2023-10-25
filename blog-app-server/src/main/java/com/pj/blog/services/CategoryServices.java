package com.pj.blog.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pj.blog.dto.CategoryDto;
import com.pj.blog.entities.Category;
import com.pj.blog.exceptions.ResourceNotFoundException;
import com.pj.blog.repositories.CategoryRepo;

@Service
public class CategoryServices {

    @Autowired
    private CategoryRepo categoryRepo;

    //create,update,delete,getbyId, getAll

    public CategoryDto createCategory(CategoryDto categoryDto){
        Category category= dtoToCategory(categoryDto);
        return categoryToDto(categoryRepo.save(category));
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories= categoryRepo.findAll();
        List<CategoryDto> categoryDtos=categories.stream().map(category->categoryToDto(category)).toList();
        return categoryDtos;
    }

    public CategoryDto getCategoryById(Integer cId) {
        Category category=categoryRepo.findById(cId).orElseThrow(() -> new ResourceNotFoundException(cId));
        return categoryToDto(category);
    }
    

    private Category dtoToCategory(CategoryDto categoryDto){
        Category category= new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        return category;
    }

    private CategoryDto categoryToDto(Category category){
        CategoryDto categoryDto= CategoryDto.builder().categoryId(category.getCategoryId())
                                .categoryName(category.getCategoryName())
                                .categoryDescription(category.getCategoryDescription())
                                .build();
        return categoryDto;
    }

    public void deleteCategory(Integer cId) {
         Category category=categoryRepo.findById(cId).orElseThrow(() -> new ResourceNotFoundException(cId));
         categoryRepo.delete(category);
    }

    


    
}
