package de.ait.events.services.impl;

import de.ait.events.dto.ParticipantDto;
import de.ait.events.dto.ParticipantToEventDto;
import de.ait.events.exceptions.RestException;
import de.ait.events.models.Event;
import de.ait.events.models.Participant;
import de.ait.events.repositories.EventsRepository;
import de.ait.events.repositories.ParticipantsRepository;
import de.ait.events.services.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static de.ait.events.dto.ParticipantDto.from;

@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventsService {

    private final EventsRepository eventsRepository;

    private final ParticipantsRepository participantsRepository;

    @Override
    public List<ParticipantDto> addParticipantToEvent(Long eventId, ParticipantToEventDto participantData) {
        Event event = getEventOrThrow(eventId);

        Participant participant = participantsRepository.findById(participantData.getParticipantId())
                .orElseThrow(()->new RestException(HttpStatus.NOT_FOUND,
                        "Participant with id <" + participantData.getParticipantId()+ "> not found"));

        if (!participant.getEvents().add(event)){
            throw new RestException(HttpStatus.BAD_REQUEST,
                    "Participant with id<" + participant.getId() + "> already in event with id <" + event.getId() + ">");
        }
        participantsRepository.save(participant);

        Set<Participant> participantsOfEvent = participantsRepository.findAllByEventsContainsOrderById(event);

        return from(participantsOfEvent);
    }

    @Override
    public List<ParticipantDto> getParticipantsOfEvent(Long eventId) {
        Event event = getEventOrThrow(eventId);

        Set<Participant> participantsOfEvent = participantsRepository.findAllByEventsContainsOrderById(event);

        return from(participantsOfEvent);
    }
    private Event getEventOrThrow(Long eventId) {
        return eventsRepository.findById(eventId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "Event with id <" + eventId + "> not found"));
    }
}
