package de.ait.task_03.repositories.impl;

import de.ait.task_03.models.Event;
import de.ait.task_03.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventsRepositoryFileImpl implements EventsRepository {

    private final String fileName;


    public EventsRepositoryFileImpl(@Value("${events.file-name}") String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Event findById(Long id) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            return reader.lines()
                    .map(line -> line.split("#"))
                    .map(parsed -> new Event(parsed[0],parsed[1]))
                    .collect(Collectors.toList());

        } catch (IOException e){
            throw new IllegalStateException("Проблемы с чтением из файла: " + e.getMessage());
        }
    }

    @Override
    public void save(Event event) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))){

            writer.write(event.getName() + "#" + event.getDesc());
            writer.newLine();

        } catch (IOException e){
            throw new IllegalStateException("Проблемы с записью в файл: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Event model) {

    }

    @Override
    public Event findOneByName(String name) {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

           return reader.lines()
                    .map(line -> line.split("#"))
                    .filter(parsed -> parsed[0].equals(name))
                    .findFirst()
                    .map(parsed -> new Event(null,null))
                    .orElse(null);

        } catch (IOException e){
            throw new IllegalStateException("Проблемы с чтением из файла: " + e.getMessage());
        }

    }
}
