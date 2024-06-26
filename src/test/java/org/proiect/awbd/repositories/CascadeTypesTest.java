package org.proiect.awbd.repositories;

import org.junit.jupiter.api.Test;
import org.proiect.awbd.model.Author;
import org.proiect.awbd.MySQLAplication;
import org.proiect.awbd.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = {MySQLAplication.class})
public class CascadeTypesTest {

    private static final Logger logger = LoggerFactory.getLogger(CascadeTypesTest.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void insertAuthor() {
        logger.info("Starting insertAuthor test");

        // Cream un autor nou
        Author author = new Author();
        author.setName("TEST. AUTOR");
        logger.info("Created a new author with name: {}", author.getName());

        // Salvăm autorul în baza de date
        authorRepository.save(author);
        logger.info("Saved the author to the database");

        // Căutăm autorul după nume
        Optional<Author> savedAuthorOpt = authorRepository.findByName("TEST. AUTOR");
        assertTrue(savedAuthorOpt.isPresent(), "Author should be present");
        logger.info("Found the author in the database");

        // Obținem autorul salvat
        Author savedAuthor = savedAuthorOpt.get();

        // Verificăm că valorile salvate sunt corecte
        assertEquals("TEST. AUTOR", savedAuthor.getName(), "Name should match");
        logger.info("Verified that the saved author's name matches the expected name");

        logger.info("insertAuthor test completed successfully");
    }

    @Test
    public void updateAuthor() {
        logger.info("Starting updateAuthor test");

        // Cream un autor nou
        Author author = new Author();
        author.setName("John Doe");
        logger.info("Created a new author with name: {}", author.getName());

        // Salvăm autorul în baza de date
        authorRepository.save(author);
        logger.info("Saved the author to the database");

        // Căutăm autorul după nume
        Optional<Author> savedAuthorOpt = authorRepository.findByName("John Doe");
        assertTrue(savedAuthorOpt.isPresent(), "Author should be present");
        logger.info("Found the author in the database");

        // Actualizăm numele autorului
        Author savedAuthor = savedAuthorOpt.get();
        savedAuthor.setName("Jane Doe");
        authorRepository.save(savedAuthor);
        logger.info("Updated the author's name to: {}", savedAuthor.getName());

        // Căutăm autorul actualizat după ID
        Optional<Author> updatedAuthorOpt = authorRepository.findById(savedAuthor.getId());
        assertTrue(updatedAuthorOpt.isPresent(), "Updated author should be present");
        logger.info("Found the updated author in the database");

        // Verificăm că numele autorului a fost actualizat corect
        Author updatedAuthor = updatedAuthorOpt.get();
        assertEquals("Jane Doe", updatedAuthor.getName(), "Name should be updated");
        logger.info("Verified that the author's name has been updated correctly");

        logger.info("updateAuthor test completed successfully");
    }

    @Test
    public void deleteAuthor() {
        logger.info("Starting deleteAuthor test");

        // Cream un autor nou
        Author author = new Author();
        author.setName("Mark Twain");
        logger.info("Created a new author with name: {}", author.getName());

        // Salvăm autorul în baza de date
        authorRepository.save(author);
        logger.info("Saved the author to the database");

        // Căutăm autorul după nume
        Optional<Author> savedAuthorOpt = authorRepository.findByName("Mark Twain");
        assertTrue(savedAuthorOpt.isPresent(), "Author should be present");
        logger.info("Found the author in the database");

        // Ștergem autorul din baza de date
        Author savedAuthor = savedAuthorOpt.get();
        authorRepository.delete(savedAuthor);
        logger.info("Deleted the author from the database");

        // Verificăm că autorul a fost șters
        Optional<Author> deletedAuthorOpt = authorRepository.findById(savedAuthor.getId());
        assertTrue(deletedAuthorOpt.isEmpty(), "Author should be deleted");
        logger.info("Verified that the author has been deleted");

        logger.info("deleteAuthor test completed successfully");
    }
}
