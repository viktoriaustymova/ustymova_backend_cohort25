package de.ait.events.services;

import de.ait.events.dto.NewParticipantDto;
import de.ait.events.dto.ParticipantDto;
import de.ait.events.models.Participant;

import java.util.List;

public interface ParticipantsService {
    ParticipantDto register(NewParticipantDto newParticipant);

    ParticipantDto getParticipantById(Long currentParticipantId);

    List<ParticipantDto> getAllParticipants();

}
