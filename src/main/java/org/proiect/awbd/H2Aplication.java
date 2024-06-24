package org.proiect.awbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Profile;

@Profile("test")
@SpringBootApplication
@EntityScan("org.proiect.awbd.model")
public class H2Aplication {
    public static void main(String[] args) {
        SpringApplication.run(H2Aplication.class, args);
    }
}
