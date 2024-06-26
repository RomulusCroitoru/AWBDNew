package org.proiect.awbd.controller;

import org.proiect.awbd.model.Library;
import org.proiect.awbd.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("")
    public ResponseEntity<List<Library>> getAllLibraries() {
        List<Library> libraries = libraryService.getAllLibraries();
        return ResponseEntity.ok(libraries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> getLibraryById(@PathVariable Long id) {
        Optional<Library> library = libraryService.getLibraryById(id);
        return library.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Library> createLibrary(@RequestBody Library library) {
        Library createdLibrary = libraryService.saveLibrary(library);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLibrary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Library> updateLibrary(@PathVariable Long id, @RequestBody Library libraryDetails) {
        Optional<Library> existingLibrary = libraryService.getLibraryById(id);
        if (existingLibrary.isPresent()) {
            Library updatedLibrary = libraryService.saveLibrary(libraryDetails);
            return ResponseEntity.ok(updatedLibrary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        Optional<Library> library = libraryService.getLibraryById(id);
        if (library.isPresent()) {
            libraryService.deleteLibraryById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
