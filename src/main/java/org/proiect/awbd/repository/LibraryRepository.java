package org.proiect.awbd.repository;

import org.proiect.awbd.model.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Long> {

}
