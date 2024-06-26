package org.proiect.awbd.model;

import org.junit.jupiter.api.Test;
import org.proiect.awbd.MySQLAplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ActiveProfiles("dev")
@SpringBootTest(classes = MySQLAplication.class) // Replace YourApplication.class with your main application class
@AutoConfigureMockMvc(addFilters = false)
public class PublisherIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenInvalidPublisher_thenReturns400() throws Exception {
        mockMvc.perform(post("/publishers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name is required"));
    }

    @Test
    public void whenValidPublisher_thenReturns200() throws Exception {
        mockMvc.perform(post("/publishers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Valid Name\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Valid Name"));
    }
}
