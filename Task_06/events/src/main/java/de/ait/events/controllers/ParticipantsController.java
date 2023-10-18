package de.ait.events.controllers;

import de.ait.events.controllers.api.ParticipantsApi;
import de.ait.events.dto.ParticipantDto;
import de.ait.events.dto.NewParticipantDto;

import de.ait.events.security.details.AuthenticatedUser;
import de.ait.events.services.ParticipantsService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController

public class ParticipantsController implements ParticipantsApi {

    private final ParticipantsService participantsService;

    @Override
    public ParticipantDto register(NewParticipantDto newParticipant){
        return participantsService.register(newParticipant);
    }

    @Override
    public ParticipantDto getProfile(AuthenticatedUser participant) {
        Long currentParticipantId = participant.getId();
        return participantsService.getParticipantById(currentParticipantId);
    }

    @Override
    public List<ParticipantDto> getAllParticipants(){
        return participantsService.getAllParticipants();

    }

}
