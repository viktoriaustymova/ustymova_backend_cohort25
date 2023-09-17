package de.ait.task_04.services;

import de.ait.task_04.dto.EventDto;
import de.ait.task_04.dto.NewEventDto;

import java.util.List;

public interface EventsService {

    List<EventDto> getAllEvents();

    EventDto addEvent(NewEventDto newEvent);
}
