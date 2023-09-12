package de.ait.task_03.services.impl;

import de.ait.task_03.dto.EventDto;
import de.ait.task_03.dto.NewEventDto;
import de.ait.task_03.models.Event;
import de.ait.task_03.repositories.EventsRepository;
import de.ait.task_03.services.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static de.ait.task_03.dto.EventDto.from;

import java.util.ArrayList;
import java.util.List;

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
        Event event = new Event(newEvent.getEventName(), newEvent.getEventDesc());
        eventsRepository.save(event);
        return from(event);
    }
}
