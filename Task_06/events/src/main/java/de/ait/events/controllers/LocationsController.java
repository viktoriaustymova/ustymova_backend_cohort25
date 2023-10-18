package de.ait.events.controllers;

import de.ait.events.controllers.api.LocationsApi;
import de.ait.events.dto.*;
import de.ait.events.services.LocationsService;
import de.ait.events.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class LocationsController implements LocationsApi {

    private final LocationsService locationsService;

    @Override
    public LocationDto addLocation(NewLocationDto newLocation){
        return locationsService.addLocation(newLocation);
    }

    @Override
    public List<LocationDto> getLocations(){
        return locationsService.getLocations();
    }

    @Override
    public LocationDto getLocation(Long locationId){
        return locationsService.getLocation(locationId);
    }

    @Override
    public EventDto addEventToLocation(Long locationId, NewEventDto newEvent) {
        return locationsService.addEventToLocation(locationId, newEvent);
    }

    @Override
    public List<EventDto> getEventsOfLocation(Long locationId){
        return locationsService.getEventsOfLocation(locationId);
    }

    @Override
    public EventDto deleteEventFromLocation(Long locationId, Long eventId){
        return locationsService.deleteEventFromLocation(locationId,eventId);
    }

    @Override
    public EventDto updateEventInLocation(Long locationId, Long eventId, UpdateEventDto updateEvent){
        return locationsService.updateEventInLocation(locationId,eventId,updateEvent);
    }

}

