package de.ait.events.controllers.api;

import de.ait.events.dto.NewParticipantDto;
import de.ait.events.dto.ParticipantDto;
import de.ait.events.security.details.AuthenticatedUser;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tags(
        @Tag(name = "Participants")
)
@RequestMapping("/api/participants")
public interface ParticipantsApi {
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    @Operation(summary = "Get all participants", description = "Admin access")
    List<ParticipantDto> getAllParticipants();

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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    ParticipantDto register(@RequestBody @Valid NewParticipantDto newParticipant);

    @GetMapping("/profile")
    ParticipantDto getProfile(@Parameter(hidden = true) @AuthenticationPrincipal AuthenticatedUser participant);


}
