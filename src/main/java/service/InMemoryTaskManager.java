package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {

    protected HashMap<Integer, Task> tasks;
    protected HashMap<Integer, Epic> epics;
    protected HashMap<Integer, SubTask> subTasks;

    protected HashMap<Integer, Task> getTask() {
        return tasks;
    }

 @Override
    protected HashMap<Integer, Epic> getEpic() {
        return epics;
    }

    protected HashMap<Integer, SubTask> getSubtask() {
        return subTasks;
    }

    private int seq = 0;

    protected InMemoryTaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
    }

    private int generateId() {
        return ++seq;
    }

    public Object getId(int id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        } else if (epics.containsKey(id)) {
            return epics.get(id);
        } else if (subTasks.containsKey(id)) {
            return subTasks.get(id);
        } else System.out.println("Такой задачи нет");
        return id;
    }

     public Task create(Task task) {
        task.setId(generateId());
        task.setStatus(Status.NEW);
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epic.setStatus(Status.NEW);
        epics.put(epic.getId(), epic);
        return epic;
    }

    public SubTask createSubTask(int idEpic, SubTask subTask) {
        subTask.setId(generateId());
        subTask.setStatus(Status.NEW);
        subTasks.put(subTask.getId(), subTask);
        subTask.setEpic(epics.get(idEpic));
        calculateStatus(idEpic);
        return subTask;
    }

    public Task update(int id, Task task) {
        task.setId(id);
        task.setStatus(Status.NEW);
        tasks.put(id, task);
        return task;
    }

    public Epic updateEpic(int id, Epic epic) {
        deleteEpicById(id);
        epic.setStatus(Status.NEW);
        epic.setId(id);
        epics.put(epic.getId(), epic);
        return epic;
    }

    public SubTask updateSubTask(int idSubTask, SubTask subTask) {
        deleteIdSubTask(idSubTask);
        subTask.setId(idSubTask);
        subTask.setStatus(Status.NEW);
        subTasks.put(idSubTask, subTask);
        return subTask;
    }

    public void deleteAll() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
        seq = 0;
    }

    public void deleteEpicById(int id) {
        for (SubTask subTask : epics.get(id).getSubTasks()) {
            subTasks.remove(subTask.getId());
            epics.remove(id);
        }
    }

    public void deleteId(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else if (epics.containsKey(id)) {
            deleteEpicById(id);
        } else if (subTasks.containsKey(id)) {
            deleteIdSubTask(id);
        } else System.out.println("Такой задачи нет");
    }

    public void deleteIdSubTask(int id) {
        SubTask subTask = subTasks.get(id);
        Epic epic = subTask.getEpic();
        epic.getSubTasks().remove(subTask);
        subTasks.remove(id);
        calculateStatus(epic.getId());
    }

    public void calculateStatus(int id) {
        boolean getNew = false;
        boolean getDone = false;
        boolean getInProgress = false;
        for (SubTask subTask : subTasks.values()) {
            getNew = subTask.getStatus().equals(NEW) || getNew;
            getInProgress = subTask.getStatus().equals(IN_PROGRESS) || getInProgress;
            getDone = subTask.getStatus().equals(DONE) || getDone;
        }
        if (getNew && !getInProgress && !getDone) {
            epics.get(id).setStatus(NEW);
        } else if (!getNew && !getInProgress && getDone) {
            epics.get(id).setStatus(DONE);
        } else epics.get(id).setStatus(IN_PROGRESS);
    }


    public void addStatus(int numberSubTask, String Status) {
        if (Status.equals("IN_PROGRESS")) {
            addStatusInProgress(numberSubTask);
        } else if (Status.equals("DONE")) {
            addStatusDone(numberSubTask);
        }
    }

    public void addStatusInProgress(int id) {
        for (Epic epic : epics.values()) {
            subTasks.get(id).setStatus(IN_PROGRESS);
            calculateStatus(epic.getId());
        }
    }

    public void addStatusDone(int id) {
        for (Epic epic : epics.values()) {
            subTasks.get(id).setStatus(DONE);
            calculateStatus(epic.getId());
        }
    }

}