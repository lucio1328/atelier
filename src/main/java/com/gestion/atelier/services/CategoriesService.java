package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.CategoriesDTO;
import com.gestion.atelier.DTO.SpecialitesDTO;
import com.gestion.atelier.mappers.CategoriesMapper;
import com.gestion.atelier.repository.CategoriesRepository;
import com.gestion.atelier.models.Categories;
import com.gestion.atelier.models.Specialites;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    private final CategoriesMapper categoriesMapper = CategoriesMapper.INSTANCE;

    //
    public List<CategoriesDTO> getAllCategories() {
        List<Categories> categories = categoriesRepository.getAll();
        if (categories == null || categories.isEmpty()) {
            return List.of();
        }
        return categories.stream()
                          .map(categoriesMapper::categoriesToCategoriesDTO)
                          .collect(Collectors.toList());
    }

    public CategoriesDTO getCategorieById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID de la categorie ne peut pas être nul");
        }
        Optional<Categories> categorieOpt = Optional.ofNullable(categoriesRepository.getById(id));
        if (categorieOpt.isEmpty()) {
            throw new IllegalArgumentException("Aucune spécialité trouvée avec l'ID : " + id);
        }
        return categoriesMapper.categoriesToCategoriesDTO(categorieOpt.get());
    }
}