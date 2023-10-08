package de.ait.events.services;

import de.ait.events.dto.NewParticipantDto;
import de.ait.events.dto.ParticipantDto;

public interface ParticipantsService {
    ParticipantDto register(NewParticipantDto newParticipant);
}
