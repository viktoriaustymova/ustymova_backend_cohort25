package de.ait.events.services;


import de.ait.events.dto.ParticipantDto;
import de.ait.events.dto.ParticipantToEventDto;

import java.util.List;

public interface EventsService {
    List<ParticipantDto> addParticipantToEvent(Long eventId, ParticipantToEventDto participantData);

    List<ParticipantDto> getParticipantsOfEvent(Long eventId);
}



