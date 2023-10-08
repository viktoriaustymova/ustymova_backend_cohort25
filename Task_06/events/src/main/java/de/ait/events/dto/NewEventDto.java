package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalTime;

@Data
@Schema(name = "NewEvent")
public class NewEventDto {

    @Schema(description = "event name", example = "Conference")
    @NotNull
    @NotBlank
    @NotEmpty
    private String title;

    @Schema(description = "event description", example = "Marketing conference")
    @Size(min = 5, max = 1000)
    @NotBlank
    @NotNull
    private String description;

    @Schema(description = "start date", example = "2024-02-02")
    @Pattern(regexp = "^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[0-1])$")
    private String beginDate;

    @Schema(description = "finish date", example = "2024-02-02")
    @Pattern(regexp = "^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[0-1])$")
    private String endDate;

    @Schema(description = "start time", example = "16:30")
    private LocalTime startTime;

    @Schema(description = "finish time", example = "20:00")
    private LocalTime finishTime;
}
