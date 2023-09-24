package de.ait.ec.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Endpoint /courses is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
class CoursesIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET /courses:")
    public class GetCourses {
        @Test
        public void return_empty_list_of_courses() throws Exception {
            mockMvc.perform(get("/api/courses"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(0)));
        }
    }

    @Nested
    @DisplayName("POST /courses:")
    public class PostCourse {
        @Test
        @Sql(scripts = {"/sql/data.sql"})
        public void return_created_course() throws Exception {
            mockMvc.perform(post("/api/courses")
                    .contentType("application/json")
                    .content("{\n" +
                            "  \"title\": \"string\",\n" +
                            "  \"description\": \"string\",\n" +
                            "  \"beginDate\": \"string\",\n" +
                            "  \"endDate\": \"string\",\n" +
                            "  \"price\": 0" +
                            "}"))
                    .andExpect(jsonPath("$.id", is(5)))
                    .andExpect(status().isCreated());

        }
    }
}