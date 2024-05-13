package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.HashMap;

public interface TaskManager {

    HashMap<Integer, Task> getTask();

    HashMap<Integer, Epic> getEpic();

    HashMap<Integer, SubTask> getSubtask();

    //int generateId();

    Object getId(int id);

    void create(Task task);

    void createEpic(Epic epic);

    void createSubTask(int idEpic, SubTask subTask);

    void update(int id, Task task);

    void updateEpic(int id, Epic epic);

    void updateSubTask(int idSubTask, SubTask subTask);

    void deleteAll();

    void deleteEpicById(int id);

    void deleteId(int id);

    void deleteIdSubTask(int id);

    void calculateStatus(int id);

    void addStatus(int numberSubTask, String Status);

    void addStatusInProgress(int id);

    void addStatusDone(int id);

}