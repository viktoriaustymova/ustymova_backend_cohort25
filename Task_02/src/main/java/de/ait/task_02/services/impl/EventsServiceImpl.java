package de.ait.task_02.services.impl;

import de.ait.task_02.modells.Event;
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
    public void addEvent(String name, String desc){
        Event event = new Event(name,desc);
        eventsRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventsRepository.findAll();
    }
}
