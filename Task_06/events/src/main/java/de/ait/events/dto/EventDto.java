package de.ait.events.dto;

import de.ait.events.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Event", description = "Event description")
public class EventDto {
    @Schema(description = "event ID", example = "1")
    private Long id;

    @Schema(description = "event name", example = "Conference")
    private String title;

    @Schema(description = "event description", example = "Marketing conference")
    private String description;

    @Schema(description = "start date", example = "2024-02-02")
    private String beginDate;

    @Schema(description = "finish date", example = "2024-02-02")
    private String endDate;

    @Schema(description = "start time", example = "18:30")
    private String startTime;

    @Schema(description = "finish time", example = "20:00")
    private String finishTime;

    @Schema(description = "event status - DRAFT, PUBLISHED", example = "PUBLISHED")
    private String state;

    @Schema(description = "location ID", example = "1")
    private Long locationId;

    public static EventDto from(Event event){
        EventDto result = EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .beginDate(event.getBeginDate().toString())
                .endDate(event.getEndDate().toString())
                .startTime(event.getStartTime().toString())
                .finishTime(event.getFinishTime().toString())
                .state(event.getState().toString())
                .build();
        if (event.getLocation() != null){
            result.setLocationId(event.getLocation().getId());
        }
        return result;
    }

    public static List<EventDto> from(Set<Event> events){
        return events
                .stream()
                .map(EventDto::from)
                .collect(Collectors.toList());
    }
}
