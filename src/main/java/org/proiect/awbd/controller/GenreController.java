package org.proiect.awbd.controller;

import org.proiect.awbd.model.Genre;
import org.proiect.awbd.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = (List<Genre>) genreRepository.findAll();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            return ResponseEntity.ok(genre.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre savedGenre = genreRepository.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genre) {
        if (!genreRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        genre.setId(id); // Ensure the genre id is set for update
        Genre updatedGenre = genreRepository.save(genre);
        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        if (!genreRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        genreRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
