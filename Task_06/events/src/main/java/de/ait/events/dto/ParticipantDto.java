package de.ait.events.dto;

import de.ait.events.models.Participant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Participant",description = "Participant data")
public class ParticipantDto {

    @Schema(description = "participant ID", example = "1")
    private Long id;

    @Schema(description = "participant first name", example = "Alex")
    private String firstName;

    @Schema(description = "participant last name", example = "Meyer")
    private String lastName;

    @Schema(description = "participant email", example = "alex@mail.com")
    private String email;

    @Schema(description = "participant role", example = "PARTICIPANT")
    private String role;

    public static ParticipantDto from (Participant participant){
        return ParticipantDto.builder()
                .id(participant.getId())
                .firstName(participant.getFirstName())
                .lastName(participant.getLastName())
                .email(participant.getEmail())
                .role(participant.getRole().toString())
                .build();
    }

    public static List<ParticipantDto> from(Set<Participant> participants){
       return  participants.stream()
               .map(ParticipantDto::from)
               .collect(Collectors.toList());
    }
}
