package de.ait.task_02.controllers;

import de.ait.task_02.modells.Event;
import de.ait.task_02.services.EventsService;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EventsController {

    public final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @PostMapping("/addEvent")
    public String addEvent(@RequestParam("eventName") String name,
                           @RequestParam("eventDesc") String desc) {
        eventsService.addEvent(name, desc);
        return null;
    }

    @GetMapping("/events")
    public String getEventsPage (Model model){
        List<Event> events = eventsService.getAllEvents();
        model.addAttribute("eventsList",events);
        return "events_page";
    }
}
