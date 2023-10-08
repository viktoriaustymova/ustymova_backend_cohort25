package de.ait.events.controllers;

import de.ait.events.dto.ParticipantDto;
import de.ait.events.dto.NewParticipantDto;
import de.ait.events.services.ParticipantsService;
import de.ait.events.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import javax.validation.Valid;

@RequiredArgsConstructor
@Tags(
        @Tag(name = "Participants")
)
@RestController
@RequestMapping("/api/participants")
public class ParticipantsController {

    private final ParticipantsService participantsService;

    @Operation(summary = "Participant registration", description = "Access for all users. Default role - USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
            description = "Participant was successfully registered",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ParticipantDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))),
            @ApiResponse(responseCode = "409",
                    description = "Participant with this email already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class)))
    })

    @PostMapping("/register")
    public ResponseEntity<ParticipantDto> register(@RequestBody @Valid NewParticipantDto newParticipant){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(participantsService.register(newParticipant));
    }
}
