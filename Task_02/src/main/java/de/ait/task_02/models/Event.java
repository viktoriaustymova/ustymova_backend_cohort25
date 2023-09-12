package de.ait.task_02.models;

import java.util.Objects;

public class Event {
    private String name;
    private String desc;

    public Event(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(name, event.name) && Objects.equals(desc, event.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc);
    }
}
