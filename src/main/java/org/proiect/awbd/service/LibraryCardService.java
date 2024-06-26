package org.proiect.awbd.service;

import org.proiect.awbd.model.LibraryCard;
import org.proiect.awbd.repository.LibraryCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryCardService {

    private final LibraryCardRepository libraryCardRepository;

    @Autowired
    public LibraryCardService(LibraryCardRepository libraryCardRepository) {
        this.libraryCardRepository = libraryCardRepository;
    }

    public List<LibraryCard> getAllLibraryCards() {
        return (List<LibraryCard>) libraryCardRepository.findAll();
    }

    public Optional<LibraryCard> getLibraryCardById(Long id) {
        return libraryCardRepository.findById(id);
    }

    public LibraryCard saveLibraryCard(LibraryCard libraryCard) {
        return libraryCardRepository.save(libraryCard);
    }

    public void deleteLibraryCardById(Long id) {
        libraryCardRepository.deleteById(id);
    }

}
