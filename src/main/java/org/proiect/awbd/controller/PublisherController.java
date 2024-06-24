package org.proiect.awbd.controller;

import org.proiect.awbd.model.Publisher;
import org.proiect.awbd.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/publishers")
@Validated
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Long id) {
        return publisherService.getPublisherById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Publisher createPublisher(@Valid @RequestBody Publisher publisher) {
        return publisherService.savePublisher(publisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @Valid @RequestBody Publisher publisher) {
        if (!publisherService.getPublisherById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        publisher.setId(id);
        return ResponseEntity.ok(publisherService.savePublisher(publisher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        if (!publisherService.getPublisherById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        publisherService.deletePublisherById(id);
        return ResponseEntity.noContent().build();
    }
}
