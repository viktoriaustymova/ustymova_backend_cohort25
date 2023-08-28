package de.ait.shop.services.impl;

import de.ait.shop.modells.Event;
import de.ait.shop.repositories.EventsRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("EventsServiceImpl is works...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventsServiceImplTest {
    private EventsServiceImpl eventsService;
    private EventsRepository eventsRepository;

    private static final Event NOT_EXIST_EVENT = new Event("event1", LocalDate.parse("2023-09-01"),LocalDate.parse("2023-09-02"));
    private static final Event EXIST_EVENT = new Event("event", LocalDate.parse("2023-09-01"),LocalDate.parse("2023-09-02"));


    @BeforeEach
    public void setUp(){
        eventsRepository = Mockito.mock(EventsRepository.class);

        when (eventsRepository.findOneByName("event")).thenReturn(EXIST_EVENT);
        when (eventsRepository.findOneByName("event1")).thenReturn(null);
        this.eventsService = new EventsServiceImpl(eventsRepository);
    }

    @Nested
    @DisplayName(("addEvent():"))
    class AddEvent {
        @Test
        public void on_incorrect_name_throws_exceptions(){
            assertThrows(IllegalArgumentException.class, () ->
                    eventsService.addEvent(null, LocalDate.parse("2023-09-01"),LocalDate.parse("2023-09-02")));
        }

        @Test
        public void on_incorrect_date_throws_exceptions(){
            assertThrows(IllegalArgumentException.class, () ->
                    eventsService.addEvent("event", LocalDate.parse("2023-09-02"),LocalDate.parse("2023-09-01")));
        }

        @Test
        public void on_existed_events_throws_exception(){
            assertThrows(IllegalArgumentException.class, () ->
                    eventsService.addEvent("event", LocalDate.parse("2023-09-01"),LocalDate.parse("2023-09-02")));
        }

        @Test
        public void returns_created_event(){
            Event actual = eventsService.addEvent("event1", LocalDate.parse("2023-09-01"),LocalDate.parse("2023-09-02"));
            verify(eventsRepository).save(NOT_EXIST_EVENT);
            assertNotNull(actual);
        }

    }
}