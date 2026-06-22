package com.movieflix.controllers;

import com.movieflix.controllers.request.CategoryRequest;
import com.movieflix.controllers.response.CategoryResponse;
import com.movieflix.entities.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController //Indica que é um controller
@RequestMapping("/movieflix/category") // Indica qual o endereço do controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        var categories = categoryService.findAll().stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest request) {

        //Pegamos o request enviado pelo usuario e transformamos em Category entity
        Category category = CategoryMapper.toCategory(request);
        //o service vai retornar um novo Category
        Category category1 = categoryService.save(category);
        //Apos isso convertemos para CategoryResponse
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(category1));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable UUID id) {
        return categoryService.findById(id)
                .map(x -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(x)))
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteById(@PathVariable UUID id) {

        if (categoryService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable UUID id, @RequestBody CategoryRequest request) {
        Optional<Category> salvo = categoryService.update(id, CategoryMapper.toCategory(request));
        return salvo.map(x -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(x)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
