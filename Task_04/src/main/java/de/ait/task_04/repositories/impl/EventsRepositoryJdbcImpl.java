package de.ait.task_04.repositories.impl;

import de.ait.task_04.models.Event;
import de.ait.task_04.repositories.EventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class EventsRepositoryJdbcImpl implements EventsRepository {

    private final DataSource dataSource;

    @Override
    public Event findById(Long id) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        return null;
    }

    @Override
    public void save(Event model) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource)
                .usingGeneratedKeyColumns("id");

        jdbcInsert.withTableName("events");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("event_name", model.getName());
        parameters.put("event_desc", model.getDesc());

        long generatedId = jdbcInsert.executeAndReturnKey(parameters).longValue();

        model.setId(generatedId);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Event model) {

    }

    @Override
    public Event findOneByName(String name) {
        return null;
    }
}
