package de.ait.events.services.impl;

import de.ait.events.dto.EventDto;
import de.ait.events.dto.LocationDto;
import de.ait.events.dto.NewEventDto;
import de.ait.events.dto.NewLocationDto;
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
        Location location = locationsRepository.findById(locationId)
                .orElseThrow(()->new RestException(HttpStatus.NOT_FOUND,"Location with id <" + locationId + "> not found"));
        return from(location);
    }

    @Override
    public EventDto addEventToLocation(Long locationId, NewEventDto newEvent) {
        Location location = locationsRepository.findById(locationId)
                .orElseThrow(()->new RestException(HttpStatus.NOT_FOUND,"Location with id <" + locationId + "> not found"));

        Event event = Event.builder()
                .title(newEvent.getTitle())
                .description(newEvent.getDescription())
                .beginDate(LocalDate.parse(newEvent.getBeginDate()))
                .endDate(LocalDate.parse(newEvent.getEndDate()))
                .startTime(newEvent.getStartTime())
                .finishTime(newEvent.getFinishTime())
                .state(Event.State.DRAFT)
                .location(location)
                .build();

        eventsRepository.save(event);
        return EventDto.from(event);

    }

    @Override
    public List<EventDto> getEventsOfLocation(Long locationId) {

        Location location = locationsRepository.findById(locationId)
                .orElseThrow(()->new RestException(HttpStatus.NOT_FOUND,"Location with id <" + locationId + "> not found"));
        Set<Event> events = location.getEvents();
        return from(events);
    }
}
