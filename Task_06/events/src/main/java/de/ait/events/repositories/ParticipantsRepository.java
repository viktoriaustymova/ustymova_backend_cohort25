package de.ait.events.repositories;

import de.ait.events.models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantsRepository  extends JpaRepository<Participant, Long> {
    boolean existsByEmail(String email);
}
