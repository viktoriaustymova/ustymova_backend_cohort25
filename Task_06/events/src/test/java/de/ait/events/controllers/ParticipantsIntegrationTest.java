package de.ait.events.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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
@DisplayName("Endpoint /participants works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class ParticipantsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("POST/participants/register:")
    public class RegisterParticipant{

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_created_participant() throws Exception{
            mockMvc.perform(post("/api/participants/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "  \"firstName\": \"Alex\",\n" +
                            "  \"lastName\": \"Meyer\",\n" +
                            "  \"email\": \"alex@mail.com\",\n" +
                            "  \"password\": \"Qwerty007!\"\n" +
                            "}"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.role", is("PARTICIPANT")));
        }

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_400_for_bad_format_email() throws Exception{
            mockMvc.perform(post("/api/participants/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "  \"firstName\": \"Alex\",\n" +
                                    "  \"lastName\": \"Meyer\",\n" +
                                    "  \"email\": \"alex.mail.com\",\n" +
                                    "  \"password\": \"Qwerty007!\"\n" +
                                    "}"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @Sql(scripts = "/sql/data.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_409_for_existed_email() throws Exception{
            mockMvc.perform(post("/api/participants/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "  \"firstName\": \"Alex\",\n" +
                                    "  \"lastName\": \"Meyer\",\n" +
                                    "  \"email\": \"alex@mail.com\",\n" +
                                    "  \"password\": \"Qwerty007!\"\n" +
                                    "}"))
                    .andExpect(status().isConflict());
        }
    }
}