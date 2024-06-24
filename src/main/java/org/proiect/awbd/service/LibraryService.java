package org.proiect.awbd.service;

import org.proiect.awbd.model.Library;
import org.proiect.awbd.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<Library> getAllLibraries() {
        return (List<Library>) libraryRepository.findAll();
    }

    public Optional<Library> getLibraryById(Long id) {
        return libraryRepository.findById(id);
    }

    public Library saveLibrary(Library library) {
        return libraryRepository.save(library);
    }

    public void deleteLibraryById(Long id) {
        libraryRepository.deleteById(id);
    }

    // Alte metode specifice pentru operațiile CRUD sau de business
}
