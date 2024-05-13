package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.*;

import static service.Status.*;

public class InMemoryTaskManager implements TaskManager {

    protected HashMap<Integer, Task> tasks;
    protected HashMap<Integer, Epic> epics;
    protected HashMap<Integer, SubTask> subTasks;

    @Override
    public HashMap<Integer, Task> getTask() {
        return tasks;
    }

    @Override
    public HashMap<Integer, Epic> getEpic() {
        return epics;
    }

    @Override
    public HashMap<Integer, SubTask> getSubtask() {
        return subTasks;
    }

    HistoryManager historyManager;//13.05
    private int seq = 0;

    public InMemoryTaskManager(HistoryManager historyManager){ //defaultHistory) {//13.05
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
        this.historyManager = historyManager;////13.05
    }

   // @Override  //13.05
    public int generateId() {
        return ++seq;
    }

    @Override
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

    @Override
    public void create(Task task) {
        task.setId(generateId());
        task.setStatus(NEW);
        tasks.put(task.getId(), task);
    }

    @Override
    public void createEpic(Epic epic) {
        epic.setId(generateId());
        epic.setStatus(NEW);
        epics.put(epic.getId(), epic);
    }

    @Override
    public void createSubTask(int idEpic, SubTask subTask) {
        subTask.setId(generateId());
        subTask.setStatus(NEW);
        subTasks.put(subTask.getId(), subTask);
        subTask.setEpic(epics.get(idEpic));
        calculateStatus(idEpic);
    }

    @Override
    public void update(int id, Task task) {
        task.setId(id);
        task.setStatus(NEW);
        tasks.put(id, task);
    }

    @Override
    public void updateEpic(int id, Epic epic) {
        deleteEpicById(id);
        epic.setStatus(NEW);
        epic.setId(id);
        epics.put(epic.getId(), epic);
    }

    @Override
    public void updateSubTask(int idSubTask, SubTask subTask) {
        deleteIdSubTask(idSubTask);
        subTask.setId(idSubTask);
        subTask.setStatus(NEW);
        subTasks.put(idSubTask, subTask);
    }

    @Override
    public void deleteAll() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
        seq = 0;
    }

    @Override
    public void deleteEpicById(int id) {
        for (SubTask subTask : epics.get(id).getSubTasks()) {
            subTasks.remove(subTask.getId());
            epics.remove(id);
        }
    }

    @Override
    public void deleteId(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else if (epics.containsKey(id)) {
            deleteEpicById(id);
        } else if (subTasks.containsKey(id)) {
            deleteIdSubTask(id);
        } else System.out.println("Такой задачи нет");
    }

    @Override
    public void deleteIdSubTask(int id) {
        SubTask subTask = subTasks.get(id);
        Epic epic = subTask.getEpic();
        epic.getSubTasks().remove(subTask);
        subTasks.remove(id);
        calculateStatus(epic.getId());
    }

    @Override
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

    @Override
    public void addStatus(int numberSubTask, String Status) {
        if (Status.equals("IN_PROGRESS")) {
            addStatusInProgress(numberSubTask);
        } else if (Status.equals("DONE")) {
            addStatusDone(numberSubTask);
        }
    }

    @Override
    public void addStatusInProgress(int id) {
        for (Epic epic : epics.values()) {
            subTasks.get(id).setStatus(IN_PROGRESS);
            calculateStatus(epic.getId());
        }
    }

    @Override
    public void addStatusDone(int id) {
        for (Epic epic : epics.values()) {
            subTasks.get(id).setStatus(DONE);
            calculateStatus(epic.getId());
        }
    }

}