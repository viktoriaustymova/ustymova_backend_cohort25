package de.ait.events.controllers.api;

import de.ait.events.dto.*;
import de.ait.events.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/locations")
@Tags(value = {
        @Tag(name = "Locations")
})
@ApiResponses({
        @ApiResponse(responseCode = "401",
                description = "User is not authorized ",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = StandardResponseDto.class))),
        @ApiResponse(responseCode = "403",
                description = "Forbidden",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = StandardResponseDto.class)))

})
public interface LocationsApi {
    @Operation(summary = "Create location", description = "Manager access")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Location was successfully created ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LocationDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class)))

    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    LocationDto addLocation(@RequestBody @Valid NewLocationDto newLocation);


    @Operation(summary = "Get locations list", description = "All users access")
    @GetMapping
    List<LocationDto> getLocations();


    @Operation(summary = "Get location", description = "All users access")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Request successfully processed",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LocationDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Location not found ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @GetMapping("/{location-id}")
    LocationDto getLocation(@Parameter(description = "location ID", example = "1")
                                                   @PathVariable("location-id") Long locationId);


    @Operation(summary = "Add event to location", description = "Manager access")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Event was successfully added ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LocationDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class)))

    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{location-id}/events")
    EventDto addEventToLocation(@PathVariable("location-id") Long locationId,
                                                       @RequestBody @Valid NewEventDto newEvent);


    @Operation(summary = "Get events of location", description = "All users access")
    @GetMapping("/{location-id}/events")
    List<EventDto> getEventsOfLocation(@PathVariable ("location-id") Long locationId);


    @Operation(summary = "Delete event from location", description = "Manager access")
    @DeleteMapping("/{location-id}/events/{event-id}")
    EventDto deleteEventFromLocation(@PathVariable ("location-id") Long locationId,
                                                            @PathVariable ("event-id") Long eventId);

    @Operation(summary = "Update event in location", description = "Manager access")
    @PutMapping("/{location-id}/events/{event-id}")
    EventDto updateEventInLocation(@PathVariable ("location-id") Long locationId,
                                                          @PathVariable ("event-id") Long eventId,
                                                          @RequestBody @Valid UpdateEventDto updateEvent);
}
