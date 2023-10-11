package de.ait.events.controllers;

import de.ait.events.dto.*;
import de.ait.events.services.EventsService;
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
@RequestMapping("/api/events")
@Tags(value = {
        @Tag(name = "Events")
})
public class EventsController {

    private final EventsService eventsService;

    @Operation(summary = "Add participant to event", description = "Manager access")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Participant was successfully added ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class)))

    })
    @PostMapping("/{event-id}/participants")
    public ResponseEntity<List<ParticipantDto>> addParticipantToEvent(@PathVariable("event-id") Long eventId,
                                                          @RequestBody @Valid ParticipantToEventDto participantData) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventsService.addParticipantToEvent(eventId,participantData));
    }

    @Operation(summary = "Get participants list", description = "All users access")
    @GetMapping("/{event-id}/participants")
    public ResponseEntity<List<ParticipantDto>> getParticipantsOfEvent(@PathVariable ("event-id") Long eventId){
        return ResponseEntity
                .ok(eventsService.getParticipantsOfEvent(eventId));
    }

}
