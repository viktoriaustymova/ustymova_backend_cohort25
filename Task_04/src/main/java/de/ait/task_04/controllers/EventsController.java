package de.ait.task_04.controllers;

import de.ait.task_04.dto.EventDto;
import de.ait.task_04.dto.NewEventDto;
import de.ait.task_04.services.EventsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Tags(value = @Tag(name = "Events"))
@RequiredArgsConstructor
@Controller
public class EventsController {

    public final EventsService eventsService;

    @Operation(summary = "Получение всех событий", description = "Доступно администратору системы")
    @GetMapping("/events")
    @ResponseBody
    public List<EventDto> getAllEvents (){
        return eventsService.getAllEvents();
    }

    @Operation(summary = "Добавление события", description = "Доступно администратору системы")
    @PostMapping("/events")
    @ResponseBody
    public EventDto addEvent(@RequestBody NewEventDto newEvent){
        return eventsService.addEvent(newEvent);
    }
}
