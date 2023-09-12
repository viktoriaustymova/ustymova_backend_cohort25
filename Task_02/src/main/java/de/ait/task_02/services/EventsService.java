package de.ait.task_02.services;

import de.ait.task_02.dto.RegisterDto;
import de.ait.task_02.models.Event;
import java.util.List;

public interface EventsService {
    void addEvent(RegisterDto registerDto);

    List<Event> getAllEvents();

}
