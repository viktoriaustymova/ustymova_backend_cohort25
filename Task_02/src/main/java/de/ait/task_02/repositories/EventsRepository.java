package de.ait.task_02.repositories;

import de.ait.task_02.modells.Event;
import de.ait.task_02.repositories.CrudRepository;

public interface EventsRepository extends CrudRepository<Event> {

    Event findOneByName(String name);
}
