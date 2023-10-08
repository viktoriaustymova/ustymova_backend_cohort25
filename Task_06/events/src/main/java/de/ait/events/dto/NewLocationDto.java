package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Schema(name = "NewLocation")
public class NewLocationDto {

    @Schema(description = "location name", example = "Mercedes-Benz Conference Center")
    @Size(min = 2, max = 50)
    @NotNull
    @NotBlank
    @NotEmpty
    private String name;


    @Schema(description = "country", example = "Germany")
    @NotNull
    @NotBlank
    @NotEmpty
    private String country;

    @Schema(description = "city", example = "Stuttgart")
    @NotNull
    @NotBlank
    @NotEmpty
    private String city;

    @Schema(description = "address", example = "Mercedesstrasse 132")
    @NotNull
    @NotBlank
    @NotEmpty
    private String address;

}
