package de.ait.events.services.impl;

import de.ait.events.dto.*;
import de.ait.events.exceptions.RestException;
import de.ait.events.models.Event;
import de.ait.events.models.Location;
import de.ait.events.repositories.EventsRepository;
import de.ait.events.repositories.LocationsRepository;
import de.ait.events.services.LocationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static de.ait.events.dto.LocationDto.from;
import static de.ait.events.dto.EventDto.from;


@RequiredArgsConstructor
@Service
public class LocationsServiceImpl implements LocationsService {

    private final LocationsRepository locationsRepository;

    private final EventsRepository eventsRepository;

    @Override
    public LocationDto addLocation(NewLocationDto newLocation) {
        Location location = Location.builder()
                .name(newLocation.getName())
                .country(newLocation.getCountry())
                .city(newLocation.getCity())
                .address(newLocation.getAddress())
                .build();

        locationsRepository.save(location);
        return from(location);
    }

    @Override
    public List<LocationDto> getLocations() {
        List<Location> locations = locationsRepository.findAll();
        return from(locations);
    }

    @Override
    public LocationDto getLocation(Long locationId) {
        Location location = getLocationOrThrow(locationId);
        return from(location);
    }

    @Override
    public EventDto addEventToLocation(Long locationId, NewEventDto newEvent) {
        Location location = getLocationOrThrow(locationId);

        Event event;

        if (newEvent.getExistsEventId() == null){
            event = Event.builder()
                    .title(newEvent.getTitle())
                    .description(newEvent.getDescription())
                    .beginDate(LocalDate.parse(newEvent.getBeginDate()))
                    .endDate(LocalDate.parse(newEvent.getEndDate()))
                    .startTime(LocalTime.parse(newEvent.getStartTime()))
                    .finishTime(LocalTime.parse(newEvent.getFinishTime()))
                    .state(Event.State.DRAFT)
                    .location(location)
                    .build();
        } else {
            event = eventsRepository.findById(newEvent.getExistsEventId())
                    .orElseThrow(()->new RestException(HttpStatus.NOT_FOUND,
                            "Event with id <" + newEvent.getExistsEventId() + "> not found"));
            event.setLocation(location);
        }
        eventsRepository.save(event);
        return EventDto.from(event);
    }

    @Override
    public List<EventDto> getEventsOfLocation(Long locationId) {
        Location location = getLocationOrThrow(locationId);
        Set<Event> events = eventsRepository.findAllByLocationOrderById(location);
        return from(events);
    }

    @Override
    public EventDto deleteEventFromLocation(Long locationId, Long eventId) {
        Location location = getLocationOrThrow(locationId);

        Event event = eventsRepository.findByLocationAndId(location, eventId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "Event with id <" + eventId + "> not found in location with id <" + locationId + ">"));
        event.setLocation(null);
        eventsRepository.save(event);

        return from(event);
    }

    @Override
    public EventDto updateEventInLocation(Long locationId, Long eventId, UpdateEventDto updateEvent) {
        Location location = getLocationOrThrow(locationId);

        Event event = eventsRepository.findByLocationAndId(location, eventId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "Event with id <" + eventId + "> not found in location with id <" + locationId + ">"));

        event.setTitle(updateEvent.getTitle());

        if (event.getDescription()!= null){
            event.setDescription(updateEvent.getDescription());
        }  else {
        event.setDescription(null);
        }
        if (event.getBeginDate() != null) {
            event.setBeginDate(LocalDate.parse(updateEvent.getBeginDate()));
        } else {
            event.setBeginDate(null);
        }
        if (event.getEndDate() != null) {
            event.setEndDate(LocalDate.parse(updateEvent.getEndDate()));
        } else {
        event.setEndDate(null);
        }
        if (event.getStartTime() != null){
            event.setStartTime(LocalTime.parse(updateEvent.getStartTime()));
        } else {
            event.setStartTime(null);
        }
        if (event.getFinishTime()!= null) {
            event.setFinishTime(LocalTime.parse(updateEvent.getFinishTime()));
        } else {
            event.setFinishTime(null);
        }
        eventsRepository.save(event);
        return from(event);
    }



    private Location getLocationOrThrow(Long locationId) {
        return locationsRepository.findById(locationId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Location with id <" + locationId + "> not found"));
    }
}
