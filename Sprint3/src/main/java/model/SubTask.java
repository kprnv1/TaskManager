package model;

import service.Status;

public class SubTask extends Task {
    private Epic epic;

    public SubTask(String name) {
        this.setName(name);
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + getId() + " ," +
                "name=" + getName() + " ," +
                "status=" + getStatus() +
                '}';
    }

    public Status getStatus() {
        return super.getStatus();
    }

}