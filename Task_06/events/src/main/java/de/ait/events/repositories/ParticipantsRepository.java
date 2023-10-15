package de.ait.events.repositories;

import de.ait.events.models.Event;
import de.ait.events.models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ParticipantsRepository  extends JpaRepository<Participant, Long> {
    boolean existsByEmail(String email);

    Set<Participant> findAllByEventsContainsOrderById(Event event);

    Optional<Participant> findByEmail(String email);
}
