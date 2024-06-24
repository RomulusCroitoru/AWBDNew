package org.proiect.awbd.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Book> books;
}
