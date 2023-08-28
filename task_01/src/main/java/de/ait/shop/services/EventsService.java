package de.ait.shop.services;

import de.ait.shop.modells.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventsService {
    Event addEvent(String name, LocalDate startDate, LocalDate endDate);

    List<Event> getAllEvents();
}
