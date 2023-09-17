package de.ait.task_04.repositories;

import de.ait.task_04.models.Event;

public interface EventsRepository extends CrudRepository<Event> {

    Event findOneByName(String name);
}
