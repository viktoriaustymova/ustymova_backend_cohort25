package de.ait.events.controllers;

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
@RequestMapping("/api/locations")
@Tags(value = {
        @Tag(name = "Locations")
})
public class LocationsController{

    private final LocationsService locationsService;

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
    @PostMapping
    public ResponseEntity<LocationDto> addLocation(@RequestBody @Valid NewLocationDto newLocation){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locationsService.addLocation(newLocation));
    }

    @Operation(summary = "Get locations list", description = "All users access")
    @GetMapping
    public ResponseEntity<List<LocationDto>> getLocations(){
        return ResponseEntity
                .ok(locationsService.getLocations());
    }

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
    public ResponseEntity<LocationDto> getLocation(@Parameter(description = "location ID", example = "1")
                                             @PathVariable("location-id") Long locationId){
        return ResponseEntity.ok(locationsService.getLocation(locationId));
    }

    @Operation(summary = "Create event", description = "Manager access")
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
    @PostMapping("/{location-id}/events")
    public ResponseEntity<EventDto> addEventToLocation(@PathVariable("location-id") Long locationId,
                                                       @RequestBody @Valid  NewEventDto newEvent) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locationsService.addEventToLocation(locationId, newEvent));
    }

    @Operation(summary = "Get events of location", description = "All users access")
    @GetMapping("/{location-id}/events")
    public ResponseEntity<List<EventDto>> getEventsOfLocation(@PathVariable ("location-id") Long locationId){
        return ResponseEntity
                .ok(locationsService.getEventsOfLocation(locationId));
    }

}

