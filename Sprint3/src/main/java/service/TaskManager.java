package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    Scanner sc = new Scanner(System.in);
    HashMap<Integer, Task> tasks;
    HashMap<Integer, Epic> epics;
    HashMap<Integer, SubTask> subTasks;
    int seq = 0;

    public TaskManager() {
        this.tasks = new HashMap<>();
    }

    private int generateId() {
        return ++seq;
    }

    public Task create(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    public Task get(int id) {
        return tasks.get(id);
    }

    public void update(Task task) {
        tasks.put(task.getId(), task);
    }

//    public void updateSubTask(SubTask subTask) {
//        Epic epic = subTask.getEpic();
//        Epic savedEpic = epics.get(epic.getId());
//    }

    public List<Task> getAll() {
        if (tasks.isEmpty()) {
            System.out.println("The list is empty");
        }
        return new ArrayList<>(tasks.values());
    }

    public void deleteAll() {
        tasks.clear();
    }

    public void delete(int id) {
        tasks.remove(id);
    }

    public void updateEpic(Epic epic) {
        Epic saved = epics.get(epic.getId());
        saved.setName(epic.getName());
    }

    public void updateSubTask(SubTask subTask) {
        Epic epic = subTask.getEpic();
        Epic savedEpic = epics.get(epic.getId());
//        calculateEpicStatus(savedEpic);
//       savedEpic.calculateEpicStatus();

    }

    public void deleteSubTask(int id) {
        SubTask removeSubTask = subTasks.remove(id);

        Epic epic = removeSubTask.getEpic();
        Epic epicSaved = epics.get(epic.getId());
        epicSaved.getSubTasks().remove(removeSubTask);
//        calculateStatus(epicSaved);
    }
}
