package de.ait.events.services;

import de.ait.events.dto.EventDto;
import de.ait.events.dto.LocationDto;
import de.ait.events.dto.NewEventDto;
import de.ait.events.dto.NewLocationDto;

import java.util.List;

public interface LocationsService {
    LocationDto addLocation(NewLocationDto newLocation);

    List<LocationDto> getLocations();

    LocationDto getLocation(Long locationId);

    EventDto addEventToLocation(Long locationId, NewEventDto newEvent);

    List<EventDto> getEventsOfLocation(Long locationId);
}

