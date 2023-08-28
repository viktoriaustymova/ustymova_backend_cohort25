package de.ait.shop.repositories.impl;

import de.ait.shop.modells.Event;
import org.junit.jupiter.api.*;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EventsRepositoryFileImpl is works...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)

class EventsRepositoryFileImplTest {

    private static final String TEMP_EVENTS_FILE_NAME = "events_test.txt";

    private EventsRepositoryFileImpl eventsRepository;

    @BeforeEach
    public void setUp() throws Exception{

        createNewFileForTest(TEMP_EVENTS_FILE_NAME);
        eventsRepository = new EventsRepositoryFileImpl(TEMP_EVENTS_FILE_NAME);
    }

    @AfterEach
    public void tearDown(){
        deleteFileAfterTest(TEMP_EVENTS_FILE_NAME);
    }

    @DisplayName("save():")
    @Nested
    class Save {

        @Test
        public void writes_correct_line_to_file() throws Exception{
            Event event = new Event("event", LocalDate.of(2023,9,1)
                    ,LocalDate.of(2023,9,2));

            eventsRepository.save(event);
            String expected = "1#event#2023-09-01#2023-09-02";
            BufferedReader reader = new BufferedReader(new FileReader(TEMP_EVENTS_FILE_NAME));
            String actual = reader.readLine();
            reader.close();
            assertEquals(expected,actual);
        }
    }

    @DisplayName("findAll():")
    @Nested
    class FindAll {
        @Test
        public void returns_correct_list_of_events() throws Exception{
            BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_EVENTS_FILE_NAME));
            writer.write("1#event#2023-09-01#2023-09-02");
            writer.newLine();
            writer.write("2#event1#2023-09-05#2023-09-06");
            writer.newLine();
            writer.close();

            List<Event> expected = Arrays.asList(
                    new Event(1L,"event",LocalDate.of(2023,9,1),LocalDate.of(2023,9,2)),
                    new Event(2L,"event1",LocalDate.of(2023,9,5),LocalDate.of(2023,9,6))
            );

            List<Event> actual = eventsRepository.findAll();

            assertEquals(expected,actual);
        }
    }

    private static void createNewFileForTest(String fileName) throws IOException {

        File file = new File(fileName);
        deleteIfExists(file);
        boolean result = file.createNewFile();
        if (!result) {
            throw new IllegalStateException("Problems with file create");
        }
    }

    private static void deleteIfExists(File file) {
        if(file.exists()){
            boolean result = file.delete();
            if (!result){
                throw new IllegalStateException("Problems with file delete");
            }
        }
    }

    private static void deleteFileAfterTest(String fileName){
        File file = new File(fileName);
        deleteIfExists(file);
    }


}