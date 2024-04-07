package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.*;

import static service.Status.*;

public class TaskManager {
    protected HashMap<Integer, Task> tasks;
    protected HashMap<Integer, Epic> epics;
    protected HashMap<Integer, SubTask> subTasks;

    private int seq = 0;
    private int num = 0;

    protected TaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
    }

    private int generateId() {
        return ++seq;
    }

    private int generatedIdInSubTask() {
        return ++num;
    }

    protected void getAll() {
        System.out.println(tasks);
        System.out.println(epics);
        System.out.println(subTasks);
    }

    protected void getId(int id) {
        System.out.println(tasks.get(id));
        tasks.get(id);
        System.out.println(epics.get(id));
        epics.get(id);
    }

    protected void getIdSubTask(int id) {
        System.out.println(subTasks.get(id));
        subTasks.get(id);
    }

    protected void create(Task task) {
        task.setId(generateId());
        task.setStatus(Status.NEW);
        tasks.put(task.getId(), task);
    }

    protected Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epic.setStatus(Status.NEW);
        epics.put(epic.getId(), epic);
        return epic;
    }

    protected SubTask createSubTask(int idEpic, SubTask subTask) {
        subTask.setId(generatedIdInSubTask());
        subTask.setStatus(Status.NEW);
        subTasks.put(subTask.getId(), subTask);
        subTask.setEpic(epics.get(idEpic));
        return subTask;
    }

    protected void update(int id, Task task) {
        task.setId(id);
        task.setStatus(Status.NEW);
        tasks.put(id, task);
    }

    protected Epic updateEpic(int id, Epic epic) {
        deleteEpicById(id);
        epic.setId(id);
        epics.put(epic.getId(), epic);
        return epic;
    }

    protected SubTask updateSubTask(int id, SubTask subTask) {
        subTask.setId(id);
        subTasks.put(id, subTask);
        addStatusInProgress(id);
        addStatusDone(id);
        return subTask;
    }

    protected void deleteAll() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
        seq = 0;
        num = 0;
    }

    protected void deleteEpicById(int id) {
        for (SubTask subTask : epics.get(id).getSubTasks()) {
            subTasks.remove(subTask.getId());
            epics.remove(id);
        }
    }

    protected void deleteId(int id) {
        tasks.remove(id);
        deleteEpicById(id);
    }

    protected void deleteIdSubTask(int id) {
        SubTask subTask = subTasks.get(id);
        Epic epic = subTask.getEpic();
        epic.getSubTasks().remove(subTask);
        subTasks.remove(id);
        calculateStatus(epic.getId());
    }


    protected void calculateStatus(int id) {
        boolean getDone = false;
        boolean getInProgress = false;
        if (epics.get(id).getSubTasks().size() == 0) {
            epics.get(id).setStatus(Status.NEW);
        } else if (epics.get(id).getSubTasks().size() > 0) {
            for (SubTask subTask : subTasks.values()) {
                if (subTask.getStatus().equals(IN_PROGRESS)) {
                    getInProgress = true;
                } else if (subTask.getStatus().equals(DONE)) {
                    getDone = true;
                }
                if (!getInProgress && getDone) {
                    epics.get(id).setStatus(DONE);
                } else if (getInProgress && !getDone) {
                    epics.get(id).setStatus(IN_PROGRESS);
                } else
                    epics.get(id).setStatus(IN_PROGRESS);
            }
        }
    }

    protected void addStatus(String numberTask, String numStatus) {
        if (numStatus.equals("1")) {
            addStatusInProgress(Integer.parseInt(numberTask));
        } else if (numStatus.equals("2")) {
            addStatusDone(Integer.parseInt(numberTask));
        }
    }

    protected void addStatusInProgress(int id) {
        for (Epic epic : epics.values()) {
            subTasks.get(id).setStatus(IN_PROGRESS);
            calculateStatus(epic.getId());
        }
    }

    protected void addStatusDone(int id) {
        for (Epic epic : epics.values()) {
            subTasks.get(id).setStatus(DONE);
            calculateStatus(epic.getId());
        }
    }

}




