package com.pj.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pj.blog.dto.CategoryDto;
import com.pj.blog.services.CategoryServices;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    
    @Autowired
    private CategoryServices categoryServices;

    // @GetMapping
    // public String testing(){
    //     return "category is working";
    // }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto){
       return categoryServices.createCategory(categoryDto);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> getAllCategories(){
        return categoryServices.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryById(@PathVariable("categoryId") Integer cId ){
        return categoryServices.getCategoryById(cId);
    }
    
    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String deleteCategory(@PathVariable("categoryId") Integer cId){
        categoryServices.deleteCategory(cId);
        return "Deleted category";
    }

}
