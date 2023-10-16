package de.ait.events.services.impl;

import de.ait.events.dto.NewParticipantDto;
import de.ait.events.dto.ParticipantDto;
import de.ait.events.exceptions.RestException;
import de.ait.events.models.Participant;
import de.ait.events.repositories.ParticipantsRepository;
import de.ait.events.services.ParticipantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.events.dto.ParticipantDto.from;

@RequiredArgsConstructor
@Service
public class ParticipantsServiceImpl implements ParticipantsService {

    private final ParticipantsRepository participantsRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ParticipantDto register(NewParticipantDto newParticipant) {

        if (participantsRepository.existsByEmail(newParticipant.getEmail())){
            throw new RestException(HttpStatus.CONFLICT, "Participant with email <" + newParticipant.getEmail() + "> already exists" );
        }

        Participant participant = Participant.builder()
                .firstName(newParticipant.getFirstName())
                .lastName(newParticipant.getLastName())
                .email(newParticipant.getEmail())
                .password(passwordEncoder.encode(newParticipant.getPassword()))
                .role(Participant.Role.PARTICIPANT)
                .build();

        participantsRepository.save(participant);

        return from(participant);
    }

    @Override
    public ParticipantDto getParticipantById(Long currentParticipantId) {
        return from(participantsRepository.findById(currentParticipantId).orElseThrow());
    }

    @Override
    public List<ParticipantDto> getAllParticipants() {
        return from(participantsRepository.findAll());
    }
}
