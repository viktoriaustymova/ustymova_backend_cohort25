package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(name = "NewParticipant",description = "Registration data")
public class NewParticipantDto {

    @Schema(description = "participant first name", example = "Alex")
    @NotNull
    @NotBlank
    @NotEmpty
    private String firstName;

    @Schema(description = "participant last name", example = "Meyer")
    @NotNull
    @NotBlank
    @NotEmpty
    private String lastName;


    @Email
    @NotNull
    @Schema(description = "participant email", example = "alex@mail.com")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    @Schema(description = "participant password", example = "Qwerty007!")
    private String password;
}
