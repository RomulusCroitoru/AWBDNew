package org.proiect.awbd.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.proiect.awbd.H2Aplication;
import org.proiect.awbd.repository.PublisherRepository;
import org.proiect.awbd.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = H2Aplication.class)
@ActiveProfiles("test")
public class PublisherServiceIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(PublisherServiceIntegrationTest.class);

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    public void testSavePublisher() {
        logger.info("Starting testSavePublisher");

        // Creăm un nou publisher
        Publisher publisher = new Publisher();
        publisher.setName("Editura X");

        // Salvăm publisher-ul în baza de date
        Publisher savedPublisher = publisherService.savePublisher(publisher);

        // Verificăm că publisher-ul a fost salvat corect
        Assertions.assertNotNull(savedPublisher.getId(), "Publisher ID should not be null");
        Assertions.assertEquals("Editura X", savedPublisher.getName(), "Publisher name should be 'Editura X'");

        logger.info("testSavePublisher completed successfully");
    }

    @Test
    public void testGetAllPublishers() {
        logger.info("Starting testGetAllPublishers");

        // Salvăm mai întâi câțiva publisheri în baza de date
        Publisher publisher1 = new Publisher();
        publisher1.setName("Editura A");
        publisherService.savePublisher(publisher1);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Editura B");
        publisherService.savePublisher(publisher2);

        // Obținem toți publisherii
        List<Publisher> allPublishers = publisherService.getAllPublishers();

        // Verificăm că avem exact 5 publisheri în listă
        Assertions.assertEquals(5, allPublishers.size(), "The number of publishers should be 5");

        logger.info("testGetAllPublishers completed successfully");
    }

    @Test
    public void testDeletePublisher() {
        logger.info("Starting testDeletePublisher");

        // Creăm un nou publisher
        Publisher publisher = new Publisher();
        publisher.setName("Editura C");

        // Salvăm publisher-ul în baza de date
        Publisher savedPublisher = publisherService.savePublisher(publisher);
        Long publisherId = savedPublisher.getId();

        // Ștergem publisher-ul din baza de date
        publisherService.deletePublisherById(publisherId);

        // Verificăm că publisher-ul a fost șters efectiv
        Optional<Publisher> deletedPublisher = publisherRepository.findById(publisherId);
        Assertions.assertFalse(deletedPublisher.isPresent(), "Publisher should have been deleted");

        logger.info("testDeletePublisher completed successfully");
    }
}
