package com.movieflix.mapper;


import com.movieflix.controllers.request.CategoryRequest;
import com.movieflix.controllers.response.CategoryResponse;
import com.movieflix.entities.Category;
import lombok.experimental.UtilityClass;

@UtilityClass //Essa classe não pode ser intanciada(new)
public class CategoryMapper {

    //Função para pegar um Category passado do usuario para um Category Entity
    public static Category toCategory(CategoryRequest request){
        return Category
                .builder()
                .name(request.name())
                .build();
    }

    //Função para pegar um Category entity e transformar em um CategoryResponse
    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
