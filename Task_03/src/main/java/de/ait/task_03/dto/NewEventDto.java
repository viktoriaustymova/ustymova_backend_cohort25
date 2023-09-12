package de.ait.task_03.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "NewEvent", description = "Информация для добавления  события")
public class NewEventDto {
    @Schema(description = "Название события", example = "Birthday")
    private String eventName;
    @Schema(description = "Описание события", example = "Birthday party")
    private String eventDesc;
}
