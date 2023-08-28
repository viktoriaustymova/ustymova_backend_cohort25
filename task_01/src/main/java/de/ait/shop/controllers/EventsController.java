package de.ait.shop.controllers;

import de.ait.shop.modells.Event;
import de.ait.shop.services.EventsService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EventsController {

    private final Scanner scanner;

    private final EventsService eventsService;

    public EventsController(Scanner scanner, EventsService eventsService) {
        this.scanner = scanner;
        this.eventsService = eventsService;
    }


    public void addEvent(){
        System.out.println("Введите название события: ");
        String name = scanner.nextLine();

        System.out.println("Введите дату начала (гггг-мм-дд): ");
        String typedStartDate = scanner.nextLine();
        if (!typedStartDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Неверный формат даты: ");
            throw new IllegalArgumentException("Неверный формат даты: " + typedStartDate);
        }
        LocalDate startDate = LocalDate.parse(typedStartDate);

        System.out.println("Введите дату окончания (гггг-мм-дд):");
        String typedEndDate = scanner.nextLine();
        if (!typedEndDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Неверный формат даты: ");
            throw new IllegalArgumentException("Неверный формат даты: " + typedEndDate);
        }
        LocalDate endDate = LocalDate.parse(typedEndDate);

        Event event = eventsService.addEvent(name,startDate,endDate);

        System.out.println(event);
    }

    public void getAllEvents(){
        List<Event> events = eventsService.getAllEvents();

        System.out.println(events);
    }
}
