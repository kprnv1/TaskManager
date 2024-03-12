package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Task {

    private int id;
    private String name;
    private String status;
    private String description;

    public Task(String name) {
        this.name = name;
    }

    public Task() {
    }

}
