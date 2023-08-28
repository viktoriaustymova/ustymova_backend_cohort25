package de.ait.shop.repositories;

import de.ait.shop.modells.Event;

public interface EventsRepository extends CrudRepository<Event> {

    Event findOneByName(String name);
}
