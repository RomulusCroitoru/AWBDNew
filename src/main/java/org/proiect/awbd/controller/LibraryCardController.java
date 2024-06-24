package org.proiect.awbd.controller;

import org.proiect.awbd.model.LibraryCard;
import org.proiect.awbd.service.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/library-cards")
public class LibraryCardController {

    private final LibraryCardService libraryCardService;

    @Autowired
    public LibraryCardController(LibraryCardService libraryCardService) {
        this.libraryCardService = libraryCardService;
    }

    @GetMapping
    public ResponseEntity<List<LibraryCard>> getAllLibraryCards() {
        List<LibraryCard> libraryCards = libraryCardService.getAllLibraryCards();
        return ResponseEntity.ok(libraryCards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryCard> getLibraryCardById(@PathVariable Long id) {
        Optional<LibraryCard> libraryCard = libraryCardService.getLibraryCardById(id);
        return libraryCard.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LibraryCard> createLibraryCard(@RequestBody LibraryCard libraryCard) {
        LibraryCard createdLibraryCard = libraryCardService.saveLibraryCard(libraryCard);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLibraryCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryCard> updateLibraryCard(@PathVariable Long id, @RequestBody LibraryCard libraryCardDetails) {
        Optional<LibraryCard> existingLibraryCard = libraryCardService.getLibraryCardById(id);
        if (existingLibraryCard.isPresent()) {
            LibraryCard updatedLibraryCard = libraryCardService.saveLibraryCard(libraryCardDetails);
            return ResponseEntity.ok(updatedLibraryCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibraryCard(@PathVariable Long id) {
        Optional<LibraryCard> libraryCard = libraryCardService.getLibraryCardById(id);
        if (libraryCard.isPresent()) {
            libraryCardService.deleteLibraryCardById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Alte metode pentru alte operații CRUD
}
