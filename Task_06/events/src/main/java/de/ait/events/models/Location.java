package de.ait.events.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50,nullable = false)
    private String name;

    @Column(length = 20,nullable = false)
    private String country;

    @Column(length = 20,nullable = false)
    private String city;

    @Column(length = 200,nullable = false)
    private String address;

    @OneToMany(mappedBy = "location")
    private Set<Event> events;

}
