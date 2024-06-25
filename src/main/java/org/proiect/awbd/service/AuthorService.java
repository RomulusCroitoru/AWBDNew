package org.proiect.awbd.service;

import org.proiect.awbd.dtos.AuthorDTO;

import java.util.List;

public interface AuthorService {

    List<AuthorDTO> findAll();

    AuthorDTO findById(Long id);

    AuthorDTO save(AuthorDTO authorDTO);

    void deleteById(Long id);
}
