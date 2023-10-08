package de.ait.events.validation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ValidationError", description = "Validation error information")
public class ValidationErrorDto {
    @Schema(description = "name of the field in which error occurred", example = "beginDate")
    private String field;

    @Schema(description = "user entered value that was rejected by server",example = "12-2023-02")
    private String rejectedValue;

    @Schema(description = "message to be shown to user",example = "correct date format: yyyy-mm-dd")
    private String message;

}
