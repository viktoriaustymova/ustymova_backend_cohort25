package de.ait.task_02.services;

import de.ait.task_02.modells.Event;
import java.util.List;

public interface EventsService {
    void addEvent(String name, String desc);

    List<Event> getAllEvents();

}
