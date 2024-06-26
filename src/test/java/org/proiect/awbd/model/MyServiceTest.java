package org.proiect.awbd.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.proiect.awbd.MySQLAplication;
import org.proiect.awbd.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = MySQLAplication.class)
public class MyServiceTest {

    @Autowired
    private MyService myService;

    @Test
    public void testDoSomething() {
        myService.doSomething();
    }
}
