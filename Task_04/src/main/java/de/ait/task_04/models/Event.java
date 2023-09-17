package de.ait.task_04.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Event {

    private Long id;
    private String name;
    private String desc;
}
