package org.proiect.awbd.mappers;

import org.proiect.awbd.dtos.AuthorDTO;
import org.proiect.awbd.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDTO toDto(Author author) {
        Long id = author.getId();
        String name = author.getName();
        return new AuthorDTO(id, name);
    }

    public Author toEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        return author;
    }
}
