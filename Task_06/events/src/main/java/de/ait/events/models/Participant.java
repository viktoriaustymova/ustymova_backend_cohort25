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
@Table(name = "account")
public class Participant {

    public enum Role {
        ADMIN, MANAGER, PARTICIPANT
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String firstName;

    @Column(length = 20)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "participant_event",
            joinColumns =
            @JoinColumn(name = "participant_id", nullable = false, referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "event_id", nullable = false, referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"participant_id", "event_id"})

    )
    private Set<Event> events;
}
