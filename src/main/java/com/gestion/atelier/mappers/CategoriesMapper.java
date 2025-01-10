package com.gestion.atelier.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.gestion.atelier.DTO.CategoriesDTO;
import com.gestion.atelier.models.Categories;

@Mapper
public interface CategoriesMapper {
    CategoriesMapper INSTANCE = Mappers.getMapper(CategoriesMapper.class);

    CategoriesDTO categoriesToCategoriesDTO(Categories categories);

    Categories categoriesDTOToCategories(CategoriesDTO categoriesDTO);
}
