package org.proiect.awbd.repository;

import org.proiect.awbd.model.Author;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Optional<Author> findById(Long id);

    Optional<Author> findByName(String name);

    // Alte metode specifice, dacă este cazul
}

