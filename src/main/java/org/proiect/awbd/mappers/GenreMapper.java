package org.proiect.awbd.mappers;

import org.proiect.awbd.dtos.GenreDTO;
import org.proiect.awbd.model.Genre;

import java.util.stream.Collectors;

public class GenreMapper {

    private GenreMapper() {
        // Constructor privat pentru a preveni instantierea clasei
    }

    public static GenreDTO toGenreDTO(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        // Nu mapăm books aici, deoarece nu este necesar în DTO
        return genreDTO;
    }

    public static Genre toGenre(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        // Nu asignăm books aici, deoarece acestea nu sunt parte din DTO
        return genre;
    }
}
