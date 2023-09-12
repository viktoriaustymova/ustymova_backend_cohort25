package de.ait.task_03.repositories;

import de.ait.task_03.models.Event;

public interface EventsRepository extends CrudRepository<Event> {

    Event findOneByName(String name);
}
