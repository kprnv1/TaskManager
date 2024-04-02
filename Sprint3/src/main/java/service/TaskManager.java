package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.*;

import static service.Status.*;

public class TaskManager {
    protected Scanner sc = new Scanner(System.in);
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

    protected SubTask createSubTask(SubTask subTask) {
        subTask.setId(generatedIdInSubTask());
        subTask.setStatus(Status.NEW);
        subTasks.put(subTask.getId(), subTask);
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

    protected void updateSubTask(int id, SubTask subTask) { //при обновлении так же не обновляются в epic`e
        subTask.setId(id);
        subTasks.put(id, subTask);
        addStatus();
    }

    protected void deleteAll() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
    }

    protected void deleteEpicById(int id) {
        for (SubTask subTask : epics.get(id).getSubTasks()) { //  1 эпик
            subTasks.remove(subTask.getId());
            epics.remove(id);
        }
    }

    protected void deleteId(int id) {
        tasks.remove(id);
        deleteEpicById(id);
    }

    protected void deleteIdSubTask(int id) {
        for (Epic epic : epics.values()) {
            epic.getSubTasks().remove(id - 1);
            subTasks.remove(id);
        }
    }

    protected void addStatus() {
        System.out.println("В какой subTask меняем стутус? Введите номер subTask:");
        getAll();
        int number = sc.nextInt();
        System.out.println("В задаче номер: " + number);
        System.out.println("1 - меняем на IN_PROGRESS");
        System.out.println("2 - меняем на DONE");
        System.out.println("3 - ничего не меняем");
        int num = sc.nextInt();
        if (num == 1) {
            if (tasks.containsKey(number)) {
                tasks.get(number).setStatus(IN_PROGRESS);
            } else if (epics.containsKey(number)) {
                System.out.println("Статус epic`a поменять невозможно.");
            }
            if (subTasks.containsKey(number)) {
                subTasks.get(number).setStatus(IN_PROGRESS);
                for (Epic epic : epics.values()) {
                    epic.setStatus(IN_PROGRESS);
                }
            } else System.out.println("Ничего не меняем");
        } else if (num == 2) {
            if (tasks.containsKey(number)) {
                tasks.get(number).setStatus(DONE);
            } else if (epics.containsKey(number)) {
                System.out.println("Статус epic`a поменять невозможно.");
            }
            if (subTasks.containsKey(number)) {
                subTasks.get(number).setStatus(DONE);
                for (Epic epic : epics.values()) {
                    epic.setStatus(DONE);
                }
            } else System.out.println("");
        } else System.out.println("Ничего не меняем");

    }
}




