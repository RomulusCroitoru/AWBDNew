package org.proiect.awbd.service;

import jakarta.validation.Valid;
import org.proiect.awbd.model.Publisher;
import org.proiect.awbd.repository.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private static final Logger logger = LoggerFactory.getLogger(PublisherService.class);

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public Publisher savePublisher(@Valid Publisher publisher) {
        logger.info("Saving publisher: {}", publisher.getName());
        return publisherRepository.save(publisher);
    }

    public List<Publisher> getAllPublishers() {
        return (List<Publisher>) publisherRepository.findAll();
    }

    public Optional<Publisher> getPublisherById(Long id) {
        return publisherRepository.findById(id);
    }

    @Transactional
    public void deletePublisherById(Long id) {
        try {
            publisherRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting publisher with id {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete publisher");
        }
    }

}
