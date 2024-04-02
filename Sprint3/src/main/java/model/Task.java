package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import service.Status;

@Getter
@Setter
@ToString
public class Task {

    private int id;
    private String name;
    private Status status;
    private String description;

    public Task(String name) {
        this.name = name;
    }

    public Task() {
    }
    public String toString() {
        return "Task{" +
                "id=" + getId() + ", " +
                "name=" + getName() + ", " +
                "status=" + getStatus() + ", " +
                "description=" + getDescription() + ", " +
                '}';
    }
}
