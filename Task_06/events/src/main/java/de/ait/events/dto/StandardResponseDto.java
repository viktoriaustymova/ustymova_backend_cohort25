package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Message", description = "Server message")
public class StandardResponseDto {

    @Schema(description = "Possibly: error message, state change message etc.", example = "Event is not found")
    private String message;
}
