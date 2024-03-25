package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.HashMap;
import java.util.Scanner;

import static service.Status.*;

public class TaskManager {
    Scanner sc = new Scanner(System.in);
    HashMap<Integer, Task> tasks;
    HashMap<Integer, Epic> epics;
    HashMap<Integer, SubTask> subTasks;

    Task task = null;
    Epic epic;
    SubTask subTask = null;


    int seq = 0;
    int num = 0;

    public TaskManager() {
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

    public Task create(Task task) {
        task.setStatus(String.valueOf(NEW));
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    public void createTaskAndEpic() {
        System.out.println("Какую задачу вы бы хотели создать?");
        System.out.println("1 - Task");
        System.out.println("2 - Epic");
        System.out.println("Enter a number:");

        String line = sc.nextLine();
        if (line.equals("1")) {
            System.out.println("Вы создаете простую задачу - Task.");
            task = create(new Task(sc.nextLine()));
            System.out.println(task);
        }
        if (line.equals("2")) {
            System.out.println("Вы создаете задачу - Epic.");
            epic = createEpic(new Epic(sc.nextLine()));
            System.out.println(epic);
            System.out.println("Задача Epic создана.");
            calculateEpicStatus();                         //подсчитываем статусы
            while (true) {
                System.out.println("Хотели бы вы создать подзадачу?");
                System.out.println("1 - Да, 2 - Нет");
                int num = Integer.parseInt(sc.nextLine());
                if (num == 1) {
                    System.out.println("Создаем подзадачу.");
                    subTask = createSubTask(new SubTask(sc.nextLine()));
                    epic.addSubtaskInEpic(subTask);
                    System.out.println("Подзадача создана.");
                    setStatus(String.valueOf(1));               //устанавливаем статусы
                    System.out.println(subTask);
                } else break;
            }
        }
    }

    public void getId() {
        System.out.println("Какую задачу вы хотели бы получить?");
        getAll();
        System.out.println("Введите номер:");
        int line = sc.nextInt();
        if (tasks.get(line) != null)
            System.out.println("Task get №" + line + ": " + tasks.get(line));
        else if (epics.get(line) != null) {
            System.out.println("Epic get №" + line + ": " + epics.get(line));
        } else System.out.println("Под таким номером задачи нет!");

    }

    public void update(int id, Task task) {   //     Обновить
        task.setStatus(String.valueOf(NEW));
        System.out.println("Обновляем Task");
        task.setId(id);
        tasks.put(id, task);

    }

    public void updateTaskAndEpic() {
        System.out.println("Какую задачу вы бы хотели обновить?");
        getAll();
        System.out.println("Enter a number:");
        int line = Integer.parseInt(sc.nextLine());
        System.out.println("Вы решили обновить " + line + " задачу");
        if (task != null && line == task.getId()) {
            System.out.println("Обновляем Task");
            task.setName(sc.nextLine());
            update(line, task);
            getAll();
        } else if (line == epic.getId()) {
            System.out.println("Обновить.");
            System.out.println("1 - задачу.");
            System.out.println("2 - подзадачу.");
            System.out.println("Введите цифру.");
            int nums = Integer.parseInt(sc.nextLine());
            if (nums == 1) {
                System.out.println("Обновляем Epic");
                epic.setName(sc.nextLine());
                updateEpic(line, epic);
                calculateEpicStatus();
                getAll();
            } else if (nums == 2) {
                System.out.println("Хотели бы вы обновить подзадачу этого Epic`a?");
                System.out.println("1 - Да, 2 - Нет");
                int num = Integer.parseInt(sc.nextLine());
                if (num == 1) {
                    System.out.println("Какую подзадачу вы хотели бы изменить?");
                    System.out.println("Всего: " + subTasks.size() + " задачи");
                    System.out.println("Номер:");
                    int number = Integer.parseInt(sc.nextLine());
                    if (number <= subTasks.size()) {
                        subTask = subTasks.get(number);
                        System.out.println("Меняем:");
                        subTask.setName(sc.nextLine());
                        System.out.println("Устанавливаем статус");
                        setStatus();
                        System.out.println("Подзадача обновлена.");

                    } else System.out.println("Нет такой подзадачи.");
                }
            }
        }
    }

    public void getAll() {
        System.out.println("Get Task: " + tasks.values());
        System.out.println("Get Epic: " + epics.values());
    }

    public void deleteAll() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
        System.out.println("All tasks deleted");
    }

    public void deleteId() {
        System.out.println("Какую задачу вы хотели бы удалить?");
        System.out.println("Введите номер:");
        int line = sc.nextInt();
        tasks.remove(line);
        epics.remove(line);
        System.out.println("Task №: " + line + " deleted");

    }

    public Epic createEpic(Epic epic) { //       Создать Epic
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public void updateEpic(int id, Epic epic) {
        epic.setId(id);
        epics.put(id, epic);
    }

    public SubTask createSubTask(SubTask subTask) { //    Создать subTask
        subTask.setId(generatedIdInSubTask());
        subTasks.put(subTask.getId(), subTask);
        return subTask;
    }

    public void deleteSubTask() {
        getAll();
        System.out.println("Выберите Epic в котором нужно будет удалить subTask?");
        int id = Integer.parseInt(sc.nextLine());
        if (task.getId() == id) {
            System.out.println("Тут нет подзадач, это простая задача!");
        } else {
            System.out.println("Введите номер подзадачи, которую хотели бы удалить.");
            int num = Integer.parseInt(sc.nextLine());
            getEpic(id).getSubTasks().remove(subTasks.get(num));
            getAll();
        }
    }

    public void setStatus(String num) {
        if (num.equals(String.valueOf(1))) {
            subTask.setStatus(String.valueOf(NEW));
        } else if (num.equals(String.valueOf(2))) {
            subTask.setStatus(String.valueOf(IN_PROGRESS));
        } else if (num.equals(String.valueOf(3))) {
            subTask.setStatus(String.valueOf(DONE));
        } else System.out.println("Нет такого статуса");

    }
    public void setStatus() {
        if (subTask.getStatus() == null) {
            setStatus(String.valueOf(1));
            getAll();
            System.out.println("Автоматически установлен статус NEW");
        } else if (!subTask.getStatus().isEmpty()) {
            System.out.println("Какой статус установить?");
            System.out.println(subTasks.values());
            System.out.println("В задаче номер:");
            String num = sc.nextLine();
            System.out.println("1 - оставляем статус NEW");
            System.out.println("2 - меняем на IN_PROGRESS");
            System.out.println("3 - меняем на DONE");
            System.out.println("В подзадаче номер: " + num);
            subTask = subTasks.get(Integer.parseInt(num));
            setStatus(sc.nextLine());
            System.out.println(subTasks);
        }
    }


    public void calculateEpicStatus() { //если epic = new ,а subTask = new и subTask = IN_PROGRESS, то epic = IN_PROGRESS
        if (epic.getStatus() == null) {
            epic.setStatus(String.valueOf(NEW));
//        } else if () {
            epic.setStatus(String.valueOf(IN_PROGRESS));
        }
    }
}



