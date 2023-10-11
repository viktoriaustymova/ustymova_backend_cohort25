package de.ait.events.services;

import de.ait.events.dto.*;

import java.util.List;

public interface LocationsService {
    LocationDto addLocation(NewLocationDto newLocation);

    List<LocationDto> getLocations();

    LocationDto getLocation(Long locationId);

    EventDto addEventToLocation(Long locationId, NewEventDto newEvent);

    List<EventDto> getEventsOfLocation(Long locationId);

    EventDto deleteEventFromLocation(Long locationId, Long eventId);

    EventDto updateEventInLocation(Long locationId, Long eventId, UpdateEventDto updateEvent);


}

