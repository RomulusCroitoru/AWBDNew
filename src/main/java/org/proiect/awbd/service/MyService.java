package org.proiect.awbd.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private static final Logger logger = LoggerFactory.getLogger(MyService.class);

    public void doSomething() {
        logger.debug("Aceasta este un mesaj de debug.");
        logger.info("Aceasta este un mesaj informativ.");
        logger.warn("Aceasta este o avertizare.");
        logger.error("Aceasta este o eroare.");
    }
}
