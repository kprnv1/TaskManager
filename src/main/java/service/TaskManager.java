package service;

import model.Epic;
import model.SubTask;
import model.Task;

public interface TaskManager {

    TaskManager();

    Object getId(int id);

    Task create(Task task);

    Epic createEpic(Epic epic);

    SubTask createSubTask(int idEpic, SubTask subTask);

    Task update(int id, Task task);

    Epic updateEpic(int id, Epic epic);

    SubTask updateSubTask(int idSubTask, SubTask subTask);

    void deleteAll();

    void deleteEpicById(int id);

    void deleteId(int id);

    void deleteIdSubTask(int id);

    void calculateStatus(int id);

    void addStatus(int numberSubTask, String Status);

    void addStatusInProgress(int id);

    void addStatusDone(int id);

}