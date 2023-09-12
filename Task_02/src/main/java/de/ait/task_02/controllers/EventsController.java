package de.ait.task_02.controllers;

import de.ait.task_02.dto.RegisterDto;
import de.ait.task_02.models.Event;
import de.ait.task_02.services.EventsService;
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

    @PostMapping("/signUp")
    public String addEvent(RegisterDto registerDto) {
        eventsService.addEvent(registerDto);
        return "redirect:/success_signUp.html";
    }

    @GetMapping("/events")
    public String getEventsPage (Model model){
        List<Event> events = eventsService.getAllEvents();
       model.addAttribute("eventsList",events);
        return "events_page";
    }
}
