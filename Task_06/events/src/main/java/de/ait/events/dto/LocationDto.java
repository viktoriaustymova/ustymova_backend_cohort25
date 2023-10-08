package de.ait.events.dto;

import de.ait.events.models.Location;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Location", description = "Location data")
public class LocationDto {
    @Schema(description = "location id", example = "1")
    private Long id;

    @Schema(description = "location name", example = "Mercedes-Benz Conference Center")
    private String name;

    @Schema(description = "country", example = "Germany")
    private String country;

    @Schema(description = "city", example = "Stuttgart")
    private String city;

    @Schema(description = "address", example = "Mercedesstrasse 132")
    private String address;


    public static LocationDto from(Location location){
        return LocationDto.builder()
                .id(location.getId())
                .name(location.getName())
                .country(location.getCountry())
                .city(location.getCity())
                .address(location.getAddress())
                .build();
    }

    public static List<LocationDto> from(List<Location> locations){
        return locations
                .stream()
                .map(LocationDto:: from)
                .collect(Collectors.toList());
    }
}
