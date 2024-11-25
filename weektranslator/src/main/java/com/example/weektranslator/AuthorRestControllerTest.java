package com.example.weektranslator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AuthorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllAuthors() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testCreateAuthor() throws Exception {
        String newAuthor = "{ \"name\": \"J.R.R. Tolkien\" }";

        mockMvc.perform(post("/authors")
                        .contentType("application/json")
                        .content(newAuthor))
                .andExpect(status().isCreated());
    }
}

