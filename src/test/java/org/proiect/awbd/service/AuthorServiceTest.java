package org.proiect.awbd.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.proiect.awbd.dtos.AuthorDTO;
import org.proiect.awbd.mappers.AuthorMapper;
import org.proiect.awbd.model.Author;
import org.proiect.awbd.repository.AuthorRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    AuthorMapper authorMapper;

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorServiceImpl authorService;

    @Test
    public void findAllAuthors() {
        // Given
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author();
        author1.setId(1L);
        author1.setName("John Doe");

        Author author2 = new Author();
        author2.setId(2L);
        author2.setName("Jane Smith");

        authors.add(author1);
        authors.add(author2);

        when(authorRepository.findAll()).thenReturn(authors);

        // When
        List<AuthorDTO> authorDTOs = authorService.findAll();

        // Then
        assertEquals(2, authorDTOs.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void findAuthorById() {
        // Given
        Long authorId = 1L;
        Author author = new Author();
        author.setId(authorId);
        author.setName("John Doe");

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(authorMapper.toDto(author)).thenReturn(new AuthorDTO(author.getId(), author.getName()));

        // When
        AuthorDTO foundAuthorDTO = authorService.findById(authorId);

        // Then
        assertEquals(author.getId(), foundAuthorDTO.getId());
        assertEquals(author.getName(), foundAuthorDTO.getName());
        verify(authorRepository, times(1)).findById(authorId);
    }

    @Test
    public void saveAuthor() {
        // Given
        AuthorDTO authorDTO = new AuthorDTO(null, "Jane Smith");
        Author authorToSave = new Author();
        authorToSave.setId(1L);
        authorToSave.setName("Jane Smith");

        when(authorMapper.toEntity(authorDTO)).thenReturn(authorToSave);
        when(authorRepository.save(authorToSave)).thenReturn(authorToSave);
        when(authorMapper.toDto(authorToSave)).thenReturn(new AuthorDTO(authorToSave.getId(), authorToSave.getName()));

        // When
        AuthorDTO savedAuthorDTO = authorService.save(authorDTO);

        // Then
        assertEquals(authorToSave.getId(), savedAuthorDTO.getId());
        assertEquals(authorToSave.getName(), savedAuthorDTO.getName());
        verify(authorRepository, times(1)).save(authorToSave);
    }

    @Test
    public void deleteAuthorById() {
        // Given
        Long authorId = 1L;

        // When
        authorService.deleteById(authorId);

        // Then
        verify(authorRepository, times(1)).deleteById(authorId);
    }
}
