package de.ait.shop.services.impl;

import de.ait.shop.modells.Event;
import de.ait.shop.repositories.EventsRepository;
import de.ait.shop.services.EventsService;

import java.time.LocalDate;
import java.util.List;

public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;

    public EventsServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public Event addEvent(String name, LocalDate startDate, LocalDate endDate){
        if(name == null || name.equals("") || name.equals(" ")){
            throw new IllegalArgumentException("Название не может быть пустым");
        }

        if (startDate.isAfter(endDate)){
            throw new IllegalArgumentException("Дата окончания не может быть раньше даты начала");
        }

        Event existedEvent = eventsRepository.findOneByName(name);

        if (existedEvent != null){
            throw new IllegalArgumentException("Событие с таким названием уже есть");
        }

        Event event = new Event(name,startDate,endDate);

        eventsRepository.save(event);

        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventsRepository.findAll();
    }

    @Override
    public void updateEvent(Long id, String newName) {
        List<Event> events = eventsRepository.findAll();
        Event eventForUpdate = null;

        for (Event event: events){
            if(event.getId().equals(id)){
                eventForUpdate = event;
                break;
            }
        }

        if(eventForUpdate == null){
            throw new IllegalArgumentException("Событие c id " +id + " не найдено");
        }

        if(!newName.isBlank()){
            eventForUpdate.setName(newName);
        }
        eventsRepository.update(eventForUpdate);
    }
}
