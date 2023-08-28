package de.ait.shop.repositories.impl;

import de.ait.shop.modells.Event;
import de.ait.shop.repositories.EventsRepository;

import java.util.ArrayList;
import java.util.List;

public class EventsRepositoryListImpl implements EventsRepository {

    private final List<Event> events = new ArrayList<>();
    private long generatedId = 1L;

    @Override
    public Event findById(Long id) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events);
    }

    @Override
    public void save(Event event) {
        events.add(event);

        event.setId(generatedId);

        generatedId++;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Event model) {

    }

    @Override
    public Event findOneByName(String name) {
        for (Event event: events){
            if (event.getName().equals(name)){
                return event;
            }
        }
        return null;
    }
}
