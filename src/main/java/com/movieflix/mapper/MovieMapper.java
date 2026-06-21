package com.movieflix.mapper;

import com.movieflix.controllers.request.MovieRequest;
import com.movieflix.controllers.request.StreamingRequest;
import com.movieflix.controllers.response.CategoryResponse;
import com.movieflix.controllers.response.MovieResponse;
import com.movieflix.controllers.response.StreamingResponse;
import com.movieflix.entities.Category;
import com.movieflix.entities.Movie;
import com.movieflix.entities.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {
    public static Movie toMovie(MovieRequest request) {

        //Montando uma lista de category APENAS com id's
        List<Category> categories = request.categories().stream()
                .map(x -> Category.builder().id(x).build())
                .toList();

        List<Streaming> streamings = request.streaming().stream()
                .map(x -> Streaming.builder().id(x).build())
                .toList();

        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie){

        List<CategoryResponse> categories = movie.getCategories().stream()
                .map(CategoryMapper::toCategoryResponse).toList();
        List<StreamingResponse> streamings = movie.getStreamings().stream()
                .map(StreamingMapper::toStreamingResponse).toList();
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
}
