package de.ait.task_03.models;

import lombok.*;

import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Event {

    private String name;
    private String desc;
}
