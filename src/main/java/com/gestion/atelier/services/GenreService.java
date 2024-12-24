package com.gestion.atelier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.atelier.DTO.GenreDTO;
import com.gestion.atelier.mappers.GenreMapper;
import com.gestion.atelier.repository.GenreRepository;
import com.gestion.atelier.models.Genre;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    private final GenreMapper genreMapper = GenreMapper.INSTANCE;

    //
    public GenreDTO getById(Long id) {
        Genre genre = genreRepository.getById(id);
        return genreMapper.genreToGenreDTO(genre);
    }

    //
    public List<GenreDTO> getAll() {
        List<Genre> genres = genreRepository.getAll(); 
        return genres.stream()
                     .map(genreMapper::genreToGenreDTO)
                     .collect(Collectors.toList());
    }

    // 
    public GenreDTO createGenre(GenreDTO genreDTO) {
        Genre genre = genreMapper.genreDTOToGenre(genreDTO);
        Genre savedGenre = genreRepository.save(genre);
        return genreMapper.genreToGenreDTO(savedGenre);
    }

    //
    public GenreDTO updateGenre(Long id, GenreDTO genreDTO) throws Exception {
        Genre existingGenre = genreRepository.getById(id);
        if (existingGenre != null) {
            existingGenre.setLibelle(genreDTO.getLibelle());
            Genre updatedGenre = genreRepository.save(existingGenre);
            return genreMapper.genreToGenreDTO(updatedGenre);
        }
        throw new Exception("Genre introuvable pour la mise à jour");
    }

    //
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
