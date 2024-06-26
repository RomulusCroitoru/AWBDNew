package org.proiect.awbd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.proiect.awbd.dtos.BookDTO;
import org.proiect.awbd.mappers.BookMapper;
import org.proiect.awbd.model.*;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private Author author;

    @Mock
    private Library library;

    @Mock
    private Genre genre;

    @Mock
    private Publisher publisher;

    @InjectMocks
    private BookMapper bookMapper;

    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        // Initialize entities and DTO for testing
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor(author);
        book.setLibrary(library);
        book.setGenre(genre);
        book.setPublisher(publisher);

        Member borrower = new Member();
        borrower.setId(101L);
        Set<Member> borrowers = new HashSet<>();
        borrowers.add(borrower);
        book.setBorrowers(borrowers);

        bookDTO = new BookDTO(book);
    }

    @Test
    public void testToBookDTO() {
        BookDTO resultDTO = BookMapper.toBookDTO(book);

        assertEquals(book.getId(), resultDTO.getId());
        assertEquals(book.getTitle(), resultDTO.getTitle());
        assertEquals(book.getAuthor().getId(), resultDTO.getAuthorId());
        assertEquals(book.getLibrary().getId(), resultDTO.getLibraryId());
        assertEquals(book.getGenre().getId(), resultDTO.getGenreId());
        assertEquals(book.getPublisher().getId(), resultDTO.getPublisherId());
        assertEquals(Collections.singletonList(101L), resultDTO.getBorrowerIds());
    }

    @Test
    public void testToBook() {
        Book resultBook = BookMapper.toBook(bookDTO);

        assertEquals(bookDTO.getId(), resultBook.getId());
        assertEquals(bookDTO.getTitle(), resultBook.getTitle());

    }
}
