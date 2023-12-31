package de.ait.ec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NewLessonDto {
    @Schema(description = "Идентификатор существующего урока")
    private Long existsLessonId;

    private String name;

    @NotBlank
    @NotEmpty
    private String startTime;

    @NotBlank
    @NotEmpty
    private String finishTime;

    @NotBlank
    @NotEmpty
    private String dayOfWeek;
}
