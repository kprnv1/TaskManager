package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import service.Status;

@Setter
@Getter
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
}
