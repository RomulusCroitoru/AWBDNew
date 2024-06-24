package org.proiect.awbd.repositories;

import org.junit.jupiter.api.Test;
import org.proiect.awbd.model.Author;
import org.proiect.awbd.MySQLAplication;
import org.proiect.awbd.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("dev")
@ContextConfiguration(classes = {MySQLAplication.class})
public class CascadeTypesTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void insertAuthor() {
        // Cream un autor nou
        Author author = new Author();
        author.setName("TEST. AUTOR");

        // Salvăm autorul în baza de date
        authorRepository.save(author);

        // Căutăm autorul după nume
        Optional<Author> savedAuthorOpt = authorRepository.findByName("TEST. AUTOR");
        assertTrue(savedAuthorOpt.isPresent(), "Author should be present");

        // Obținem autorul salvat
        Author savedAuthor = savedAuthorOpt.get();

        // Verificăm că valorile salvate sunt corecte
        assertEquals("TEST. AUTOR", savedAuthor.getName(), "Name should match");
    }

    @Test
    public void updateAuthor() {
        // Create a new author
        Author author = new Author();
        author.setName("John Doe");

        // Save the author to the database
        authorRepository.save(author);

        // Retrieve the saved author from the database
        Optional<Author> savedAuthorOpt = authorRepository.findByName("John Doe");
        assertTrue(savedAuthorOpt.isPresent(), "Author should be present");

        // Update the author's name
        Author savedAuthor = savedAuthorOpt.get();
        savedAuthor.setName("Jane Doe");
        authorRepository.save(savedAuthor);

        // Retrieve the updated author from the database
        Optional<Author> updatedAuthorOpt = authorRepository.findById(savedAuthor.getId());
        assertTrue(updatedAuthorOpt.isPresent(), "Updated author should be present");

        // Verify that the author's name has been updated correctly
        Author updatedAuthor = updatedAuthorOpt.get();
        assertEquals("Jane Doe", updatedAuthor.getName(), "Name should be updated");
    }

    @Test
    public void deleteAuthor() {
        // Create a new author
        Author author = new Author();
        author.setName("Mark Twain");

        // Save the author to the database
        authorRepository.save(author);

        // Retrieve the saved author from the database
        Optional<Author> savedAuthorOpt = authorRepository.findByName("Mark Twain");
        assertTrue(savedAuthorOpt.isPresent(), "Author should be present");

        // Delete the author from the database
        Author savedAuthor = savedAuthorOpt.get();
        authorRepository.delete(savedAuthor);

        // Verify that the author has been deleted
        Optional<Author> deletedAuthorOpt = authorRepository.findById(savedAuthor.getId());
        assertTrue(deletedAuthorOpt.isEmpty(), "Author should be deleted");
    }
}
