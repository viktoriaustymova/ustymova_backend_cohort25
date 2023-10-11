package de.ait.events.repositories;

import de.ait.events.models.Event;
import de.ait.events.models.Location;
import de.ait.events.models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface EventsRepository extends JpaRepository<Event, Long> {
    Set<Event> findAllByLocationOrderById(Location location);

    Optional<Event> findByLocationAndId(Location location, Long eventId);

}
