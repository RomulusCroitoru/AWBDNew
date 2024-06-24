package org.proiect.awbd.repository;

import org.proiect.awbd.model.LibraryCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryCardRepository extends CrudRepository<LibraryCard, Long> {

}
