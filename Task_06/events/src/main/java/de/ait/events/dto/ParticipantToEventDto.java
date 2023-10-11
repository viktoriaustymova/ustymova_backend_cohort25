package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ParticipantToEventDto {

    @Schema(description = "participant ID", example = "1")
    private Long participantId;
}
