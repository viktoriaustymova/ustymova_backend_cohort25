package de.ait.ec.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Поля для обновления, null - сохраняются в базе")
public class UpdateLessonDto {

    @NotBlank
    @NotEmpty
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
