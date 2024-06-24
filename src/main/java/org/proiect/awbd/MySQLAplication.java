package org.proiect.awbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@SpringBootApplication
@EntityScan("org.proiect.awbd")
public class MySQLAplication {
    public static void main(String[] args) {
        SpringApplication.run(MySQLAplication.class, args);
    }


}
