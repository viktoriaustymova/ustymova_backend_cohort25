package de.ait.events.controllers;

import de.ait.events.controllers.api.EventsApi;
import de.ait.events.dto.*;
import de.ait.events.services.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class EventsController implements EventsApi {

    private final EventsService eventsService;

    @Override
    public List<ParticipantDto> addParticipantToEvent( Long eventId, ParticipantToEventDto participantData) {
        return eventsService.addParticipantToEvent(eventId,participantData);
    }

    @Override
    public List<ParticipantDto> getParticipantsOfEvent(Long eventId){
        return eventsService.getParticipantsOfEvent(eventId);
    }

}
