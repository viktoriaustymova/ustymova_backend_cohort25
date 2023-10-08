package de.ait.events.repositories;

import de.ait.events.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationsRepository extends JpaRepository <Location, Long> {
}
