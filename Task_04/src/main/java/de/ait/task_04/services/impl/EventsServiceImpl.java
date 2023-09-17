package de.ait.task_04.services.impl;

import de.ait.task_04.dto.EventDto;
import de.ait.task_04.dto.NewEventDto;
import de.ait.task_04.models.Event;
import de.ait.task_04.repositories.EventsRepository;
import de.ait.task_04.services.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.task_04.dto.EventDto.from;

@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventsService {

    private final EventsRepository eventsRepository;


    @Override
    public List<EventDto> getAllEvents() {
        return from(eventsRepository.findAll());
    }

    @Override
    public EventDto addEvent(NewEventDto newEvent) {
        Event event = Event.builder().
                name(newEvent.getEventName()).
                desc(newEvent.getEventDesc()).
                build();

        eventsRepository.save(event);
        return from(event);
    }
}
