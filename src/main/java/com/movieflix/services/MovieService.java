package com.movieflix.services;

import com.movieflix.controllers.request.MovieRequest;
import com.movieflix.controllers.response.MovieResponse;
import com.movieflix.entities.Category;
import com.movieflix.entities.Movie;
import com.movieflix.entities.Streaming;
import com.movieflix.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie save(Movie movie){
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(UUID id){
        return movieRepository.findById(id);
    }

    private List<Category> findCategories(List<Category> categories){

        List<Category> categoriesFound = new ArrayList<>();
        categories
                .forEach(x -> {
                    categoryService.findById(x.getId()).ifPresent(category -> categoriesFound.add(category));
                });

        return categoriesFound;
    }
    private List<Streaming> findStreamings(List<Streaming> streamings){

        List<Streaming> streamingFound = new ArrayList<>();
        streamings
                .forEach(x -> {
                    streamingService.findById(x.getId()).ifPresent(streaming -> streamingFound.add(streaming));
                });

        return streamingFound;
    }

    public Optional<Movie> update(UUID id, Movie movie) {
        return Optional.empty();
    }

    public boolean deleteById(UUID id) {
        return false;
    }


}
