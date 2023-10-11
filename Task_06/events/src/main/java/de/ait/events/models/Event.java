package de.ait.events.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(exclude = "location")
public class Event {
    public enum State{
        DRAFT, PUBLISHED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(length = 1000)
    private String description;

    private LocalDate beginDate;

    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime finishTime;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @ToString.Exclude
    private Location location;

    @ManyToMany(mappedBy = "events")
    private Set<Participant> participants;
}
