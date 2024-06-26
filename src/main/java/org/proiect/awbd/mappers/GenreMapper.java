package org.proiect.awbd.mappers;

import org.proiect.awbd.dtos.GenreDTO;
import org.proiect.awbd.model.Genre;


public class GenreMapper {

    private GenreMapper() {
        // Constructor privat pentru a preveni instantierea clasei
    }

    public static GenreDTO toGenreDTO(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());

        return genreDTO;
    }

    public static Genre toGenre(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());

        return genre;
    }
}
