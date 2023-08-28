package de.ait.shop;

import de.ait.shop.controllers.EventsController;
import de.ait.shop.repositories.EventsRepository;
import de.ait.shop.repositories.impl.EventsRepositoryFileImpl;
import de.ait.shop.repositories.impl.EventsRepositoryListImpl;
import de.ait.shop.services.impl.EventsServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventsRepository eventsRepositoryList = new EventsRepositoryListImpl();
        EventsRepository eventsRepositoryFile = new EventsRepositoryFileImpl("events.txt");
        EventsServiceImpl registrationService = new EventsServiceImpl(eventsRepositoryFile);
        EventsController eventsController = new EventsController(scanner,registrationService);

        boolean isRun = true;

        while (isRun){
            String command = scanner.nextLine();

            switch (command) {
                case "/addEvent" ->
                        eventsController.addEvent();
                case "/events" ->
                        eventsController.getAllEvents();
                case "/exit" ->
                        isRun = false;
            }
        }
    }
}
