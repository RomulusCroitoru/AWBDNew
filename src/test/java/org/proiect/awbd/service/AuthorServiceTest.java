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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceTest.class);

    @Mock
    AuthorMapper authorMapper;

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorServiceImpl authorService;

    @Test
    public void findAllAuthors() {
        logger.info("Starting findAllAuthors test");

        // Given
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author();
        author1.setId(1L);
        author1.setName("John Doe");
        logger.info("Created author1 with id: {} and name: {}", author1.getId(), author1.getName());

        Author author2 = new Author();
        author2.setId(2L);
        author2.setName("Jane Smith");
        logger.info("Created author2 with id: {} and name: {}", author2.getId(), author2.getName());

        authors.add(author1);
        authors.add(author2);

        when(authorRepository.findAll()).thenReturn(authors);
        logger.info("Mocked authorRepository to return list of authors");

        // When
        List<AuthorDTO> authorDTOs = authorService.findAll();
        logger.info("Called authorService.findAll(), received {} authorDTOs", authorDTOs.size());

        // Then
        assertEquals(2, authorDTOs.size());
        verify(authorRepository, times(1)).findAll();
        logger.info("Verified that findAll() was called once on authorRepository");

        logger.info("findAllAuthors test completed successfully");
    }

    @Test
    public void findAuthorById() {
        logger.info("Starting findAuthorById test");

        // Given
        Long authorId = 1L;
        Author author = new Author();
        author.setId(authorId);
        author.setName("John Doe");
        logger.info("Created author with id: {} and name: {}", authorId, author.getName());

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        when(authorMapper.toDto(author)).thenReturn(new AuthorDTO(author.getId(), author.getName()));
        logger.info("Mocked authorRepository and authorMapper for findById");

        // When
        AuthorDTO foundAuthorDTO = authorService.findById(authorId);
        logger.info("Called authorService.findById({}), received AuthorDTO with id: {} and name: {}", authorId, foundAuthorDTO.getId(), foundAuthorDTO.getName());

        // Then
        assertEquals(author.getId(), foundAuthorDTO.getId());
        assertEquals(author.getName(), foundAuthorDTO.getName());
        verify(authorRepository, times(1)).findById(authorId);
        logger.info("Verified that findById() was called once on authorRepository");

        logger.info("findAuthorById test completed successfully");
    }

    @Test
    public void saveAuthor() {
        logger.info("Starting saveAuthor test");

        // Given
        AuthorDTO authorDTO = new AuthorDTO(null, "Jane Smith");
        logger.info("Created AuthorDTO with name: {}", authorDTO.getName());

        Author authorToSave = new Author();
        authorToSave.setId(1L);
        authorToSave.setName("Jane Smith");
        logger.info("Created Author entity to save with id: {} and name: {}", authorToSave.getId(), authorToSave.getName());

        when(authorMapper.toEntity(authorDTO)).thenReturn(authorToSave);
        when(authorRepository.save(authorToSave)).thenReturn(authorToSave);
        when(authorMapper.toDto(authorToSave)).thenReturn(new AuthorDTO(authorToSave.getId(), authorToSave.getName()));
        logger.info("Mocked authorMapper and authorRepository for save");

        // When
        AuthorDTO savedAuthorDTO = authorService.save(authorDTO);
        logger.info("Called authorService.save(), received AuthorDTO with id: {} and name: {}", savedAuthorDTO.getId(), savedAuthorDTO.getName());

        // Then
        assertEquals(authorToSave.getId(), savedAuthorDTO.getId());
        assertEquals(authorToSave.getName(), savedAuthorDTO.getName());
        verify(authorRepository, times(1)).save(authorToSave);
        logger.info("Verified that save() was called once on authorRepository");

        logger.info("saveAuthor test completed successfully");
    }

    @Test
    public void deleteAuthorById() {
        logger.info("Starting deleteAuthorById test");

        // Given
        Long authorId = 1L;
        logger.info("Prepared to delete author with id: {}", authorId);

        // When
        authorService.deleteById(authorId);
        logger.info("Called authorService.deleteById({})", authorId);

        // Then
        verify(authorRepository, times(1)).deleteById(authorId);
        logger.info("Verified that deleteById() was called once on authorRepository");

        logger.info("deleteAuthorById test completed successfully");
    }
}
