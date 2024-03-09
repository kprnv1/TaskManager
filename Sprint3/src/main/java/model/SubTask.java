package model;

public class SubTask extends Task{
    Epic epic;

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

//    @Override                            //переопределить метод
//    public String getStatus() {
//        return super.getStatus();
//    }
}
