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

    protected TaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
    }

    private int generateId() {
        return ++seq;
    }

    protected void getAll() {
        System.out.println(tasks);
        System.out.println(epics);
        System.out.println(subTasks);
    }

    protected Object getId(int id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        } else if (epics.containsKey(id)) {
            return epics.get(id);
        } else if (subTasks.containsKey(id)) {
            return subTasks.get(id);
        } else System.out.println("Такой задачи нет");
        return id;
    }

    protected Task create(Task task) {
        task.setId(generateId());
        task.setStatus(Status.NEW);
        tasks.put(task.getId(), task);
        return task;
    }

    protected Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epic.setStatus(Status.NEW);
        epics.put(epic.getId(), epic);
        return epic;
    }

    protected SubTask createSubTask(int idEpic, SubTask subTask) {
        subTask.setId(generateId());
        subTask.setStatus(Status.NEW);
        subTasks.put(subTask.getId(), subTask);
        subTask.setEpic(epics.get(idEpic));
        return subTask;
    }

    protected Task update(int id, Task task) {
        task.setId(id);
        task.setStatus(Status.NEW);
        tasks.put(id, task);
        return task;
    }

    protected Epic updateEpic(int id, Epic epic) {
        deleteEpicById(id);
        epic.setStatus(Status.NEW);
        epic.setId(id);
        epics.put(epic.getId(), epic);
        return epic;
    }

    protected SubTask updateSubTask(int idSubTask, SubTask subTask) {
        deleteIdSubTask(idSubTask);
        subTask.setId(idSubTask);
        subTask.setStatus(Status.NEW);
        subTasks.put(idSubTask, subTask);
//        addStatusInProgress(id);
//        addStatusDone(id);
        return subTask;
    }

    protected void deleteAll() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
        seq = 0;
    }

    protected void deleteEpicById(int id) {
        for (SubTask subTask : epics.get(id).getSubTasks()) {
            subTasks.remove(subTask.getId());
            epics.remove(id);
        }
    }

    protected void deleteId(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else if (epics.containsKey(id)) {
            deleteEpicById(id);
        } else if (subTasks.containsKey(id)) {
            deleteIdSubTask(id);
        } else System.out.println("Такой задачи нет");
    }

    protected void deleteIdSubTask(int id) {
        SubTask subTask = subTasks.get(id);
        Epic epic = subTask.getEpic();
        epic.getSubTasks().remove(subTask);
        subTasks.remove(id);
        calculateStatus(epic.getId());
    }


    protected void calculateStatus(int id) {
        boolean getNew = false;
        boolean getDone = false;
        boolean getInProgress = false;
        if (epics.get(id).getSubTasks().size() == 0) {
            epics.get(id).setStatus(Status.NEW);


        } else if (epics.get(id).getSubTasks().size() > 0) {
            for (SubTask subTask : subTasks.values()) {
                if (subTask.getStatus().equals(NEW)) {
                    getNew = true;
                } else if (subTask.getStatus().equals(IN_PROGRESS)) {
                    getInProgress = true;
                } else if (subTask.getStatus().equals(DONE)) {
                    getDone = true;
                }
                if (getNew && getDone) {
                    epics.get(id).setStatus(IN_PROGRESS);
                } else if (!getInProgress && getDone) {
                    epics.get(id).setStatus(DONE);
                } else if (getInProgress && !getDone) {
                    epics.get(id).setStatus(IN_PROGRESS);
                } else
                    epics.get(id).setStatus(IN_PROGRESS);
            }
        }
    }

    protected void addStatus(int numberSubTask, String Status) {
        if (Status.equals("IN_PROGRESS")) {
            addStatusInProgress(numberSubTask);
        } else if (Status.equals("DONE")) {
            addStatusDone(numberSubTask);
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




