package org.proiect.awbd.service;

import org.proiect.awbd.dtos.AuthorDTO;
import org.proiect.awbd.mappers.AuthorMapper;
import org.proiect.awbd.model.Author;
import org.proiect.awbd.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorDTO> findAll() {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO findById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isEmpty()) {
            throw new RuntimeException("Author not found!");
        }
        return authorMapper.toDto(authorOptional.get());
    }

    @Override
    public AuthorDTO save(AuthorDTO authorDTO) {
        Author author = authorMapper.toEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toDto(savedAuthor);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
