package org.proiect.awbd.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

@ActiveProfiles("test")
public class PublisherEntityTest {

    @Test
    public void testPublisherEntityWithBooks() {
        // Creează un publisher
        Publisher publisher = new Publisher();
        publisher.setId(1L);
        publisher.setName("Editura ABC");

        // Creează o carte asociată cu publisher-ul
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Cartea 1");
        book1.setPublisher(publisher);

        // Adaugă cartea în setul de cărți al publisher-ului
        Set<Book> books = new HashSet<>();
        books.add(book1);
        publisher.setBooks(books);

        // Verifică getterii și setterii entității Publisher
        Assertions.assertEquals(1L, publisher.getId());
        Assertions.assertEquals("Editura ABC", publisher.getName());
        Assertions.assertEquals(1, publisher.getBooks().size());
    }
}
