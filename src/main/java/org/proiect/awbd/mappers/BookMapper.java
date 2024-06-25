package org.proiect.awbd.mappers;

import org.proiect.awbd.dtos.BookDTO;
import org.proiect.awbd.model.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

    private BookMapper() {
        // Constructor privat pentru a preveni instantierea clasei
    }

    public static BookDTO toBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthorId(book.getAuthor() != null ? book.getAuthor().getId() : null);
        bookDTO.setLibraryId(book.getLibrary() != null ? book.getLibrary().getId() : null);
        bookDTO.setGenreId(book.getGenre() != null ? book.getGenre().getId() : null);
        bookDTO.setPublisherId(book.getPublisher() != null ? book.getPublisher().getId() : null);
        bookDTO.setBorrowerIds(
                book.getBorrowers() != null ?
                        book.getBorrowers().stream().map(member -> member.getId()).collect(Collectors.toList())
                        : null
        );
        return bookDTO;
    }

    public static Book toBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());

        return book;
    }
}
