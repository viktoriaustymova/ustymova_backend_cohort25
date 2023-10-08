package de.ait.events.controllers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Endpoint /locations works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class LocationsIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET/ locations:")
    public class GetLocations{
        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_empty_list_of_locations() throws Exception{
            mockMvc.perform(get("/api/locations"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()",is(0)));
        }

        @Test
        @Sql(scripts = "/sql/data.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_list_of_locations_for_not_empty_database() throws Exception{
            mockMvc.perform(get("/api/locations"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()",is(2)))
                    .andExpect(jsonPath("$.[0].id", is(1)))
                    .andExpect(jsonPath("$.[1].id", is(2)))
                    .andExpect(jsonPath("$.[1].city", is("Stuttgart")));;
        }
    }

    @Nested
    @DisplayName("POST /locations:")
    public class PostLocation{
        @Test
        @Sql(scripts = {"/sql/data.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_created_location() throws Exception {
            mockMvc.perform(post("/api/locations")
                    .contentType("application/json")
                    .content("{\n" +
                            "  \"name\": \"Mercedes-Benz Conference Center\",\n" +
                            "  \"country\": \"Germany\",\n" +
                            "  \"city\": \"Stuttgart\",\n" +
                            "  \"address\": \"Mercedesstrasse 132\"\n" +
                            "}"))
                    .andExpect(jsonPath("$.id", is(3)))
                    .andExpect(status().isCreated());

        }
        @Test
        public void return_400_for_not_valid_location() throws Exception {
            mockMvc.perform(post("/api/locations")
                            .contentType("application/json")
                            .content("{\n" +
                                    "  \"name\": \"M\",\n" +
                                    "  \"country\": \"Germany\",\n" +
                                    "  \"city\": \"Stuttgart\",\n" +
                                    "  \"address\": \"Mercedesstrasse 132\"\n" +
                                    "}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errors.size()", is(1)));
        }
    }

    @Nested
    @DisplayName("GET/locations/{location-id}:")
    public class GetLocation {
        @Test
        @Sql(scripts = "/sql/data.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_existed_location() throws Exception{
            mockMvc.perform(get("/api/locations/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id",is(1)))
                    .andExpect(jsonPath("$.name",is("Messe Dresden")))
                    .andExpect(jsonPath("$.city",is("Dresden")));
        }

        @Test
        @Sql(scripts = "/sql/data.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_404_for_not_existed_location() throws Exception{
            mockMvc.perform(get("/api/locations/3"))
                    .andExpect(status().isNotFound());
        }
    }
}
