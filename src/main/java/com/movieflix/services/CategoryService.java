package com.movieflix.services;

import com.movieflix.entities.Category;
import com.movieflix.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }


    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }

    public boolean deleteById(UUID id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;

    }
}
