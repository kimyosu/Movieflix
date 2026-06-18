package com.movieflix.controllers;

import com.movieflix.entities.Category;
import com.movieflix.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController //Indica que é um controller
@RequestMapping("/movieflix/category") // Indica qual o endereço do controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.findALl();
    }

    @PostMapping
    public Category saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category
        );
    }

    @GetMapping("/{id}")
    public Category getByCategoryId(@PathVariable UUID id){
        return categoryService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void DeleteCategoryById(@PathVariable UUID id){
        categoryService.deleteCategory(id);
    }

}
