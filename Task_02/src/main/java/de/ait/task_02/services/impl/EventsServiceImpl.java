package de.ait.task_02.services.impl;

import de.ait.task_02.dto.RegisterDto;
import de.ait.task_02.models.Event;
import de.ait.task_02.repositories.EventsRepository;
import de.ait.task_02.services.EventsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;

    public EventsServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public void addEvent(RegisterDto registerDto){
        Event event = new Event(registerDto.getInputEventName(),
                registerDto.getInputEventDesc());
        eventsRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventsRepository.findAll();
    }
}
