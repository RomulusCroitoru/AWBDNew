package org.proiect.awbd.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.proiect.awbd.model.Author;
import org.proiect.awbd.model.Book;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private Long authorId;
    private Long libraryId;
    private Long genreId;
    private Long publisherId;
    private List<Long> borrowerIds;

    // Constructor pentru a crea un BookDTO dintr-un obiect Book
    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();

        // Setăm ID-urile pentru entitățile asociate
        if (book.getAuthor() != null) {
            this.authorId = book.getAuthor().getId();
        }
        if (book.getLibrary() != null) {
            this.libraryId = book.getLibrary().getId();
        }
        if (book.getGenre() != null) {
            this.genreId = book.getGenre().getId();
        }
        if (book.getPublisher() != null) {
            this.publisherId = book.getPublisher().getId();
        }

        // Colectăm ID-urile împrumutaților într-o listă
        if (book.getBorrowers() != null) {
            this.borrowerIds = book.getBorrowers().stream()
                    .map(member -> member.getId())
                    .collect(Collectors.toList());
        }
    }
}
