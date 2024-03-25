package model;

public class SubTask extends Task {
    Epic epic;

    public SubTask(String name) {
        this.setName(name);
    }

    public Epic getEpic() {
        return epic;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + getId() + " ," +
                "name=" + getName() + " ," +
                "status=" + getStatus() +
                '}';
    }

    public String getStatus() {
        return super.getStatus();
    }

}