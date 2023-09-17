package de.ait.task_04.dto;

import de.ait.task_04.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@Schema(name = "Event", description = "Информация о событии")
public class EventDto {

    @Schema(description = "Идентификатор события", example = "1")
    private Long id;

    @Schema(description = "Название события", example = "Birthday")
    private String eventName;
    @Schema(description = "Описание события", example = "Birthday party")
    private String eventDesc;

    public static EventDto from(Event event){
        return new EventDto(event.getId(), event.getName(), event.getDesc());
    }

    public static List<EventDto> from (List <Event> events){

        //List <EventDto> eventDtos = new ArrayList<>();
        //for (Event event: events){
           // EventDto eventDto = from(event);
           // eventDtos.add(eventDto);
           //}
       // return eventDtos;

        return events.stream()
                .map(EventDto::from)
                .collect(Collectors.toList());
    }
}
