package de.ait.task_02.repositories;

import de.ait.task_02.models.Event;

public interface EventsRepository extends CrudRepository<Event> {

    Event findOneByName(String name);
}
