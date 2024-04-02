package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.Scanner;

public class Run {
    protected Scanner sc = new Scanner(System.in);
    protected TaskManager taskManager = new TaskManager();

    protected Epic epic;
    protected SubTask subTask;


    public void start() {
        while (true) {
            printMenu();
            String line = sc.nextLine();
            if (line.isEmpty()) {
                return;
            } else if (line.equals("1")) {
                System.out.println("Начинаем создавать задачу");
                System.out.println("1-Task");
                System.out.println("2-Epic");
                String num = sc.nextLine();
                if (num.equals("1")) {
                    String task = sc.nextLine();
                    taskManager.create(new Task(task));
                } else if (num.equals("2")) {
                    String task = sc.nextLine();
                    epic = taskManager.createEpic(new Epic(task));
                    while (true) {
                        System.out.println("Создать subTask?");
                        System.out.println("1-Yes");
                        System.out.println("2-No");
                        String numSub = sc.nextLine();
                        if (numSub.equals("1")) {
                            subTask = taskManager.createSubTask(new SubTask(sc.nextLine()));
                            epic.addSubtaskInEpic(subTask);
                            taskManager.getAll();
                        } else if (numSub.equals("2")) {
                            System.out.println("subTask не создана.");
                            break;
                        } else break;
                    }
                } else {
                    System.out.println("Неверно! Вы ввели: " + num);
                }
                System.out.println("Закончили создавать задачу");
            } else if (line.equals("2")) {
                System.out.println("Начинаем получать список всех задач");
                taskManager.getAll();
                System.out.println("Закончили получать список всех задач");
            } else if (line.equals("3")) {
                System.out.println("Начинаем удалять все задачи");
                taskManager.deleteAll();
                System.out.println("Закончили удалять все задачи");
            } else if (line.equals("4")) {
                System.out.println("Начинаем получать задачу по id");
                taskManager.getAll();
                String num = sc.nextLine();
                taskManager.getId(Integer.parseInt(num));
                System.out.println("Закончили получать задачу по id");
            } else if (line.equals("5")) {
                System.out.println("Начинаем получать subTask по id");
                int num = Integer.parseInt(sc.nextLine());
                taskManager.getIdSubTask(num);
                System.out.println("Закончили получать subTask по id");
            } else if (line.equals("6")) {
                taskManager.getAll();
                System.out.println("Начинаем удалять задачу по id");
                String num = sc.nextLine();
                taskManager.deleteId(Integer.parseInt(num));
                taskManager.getAll();
                System.out.println("Закончили удалять задачу по id");
            } else if (line.equals("7")) {
                System.out.println("Начинаем удалять subTask по id");
                taskManager.deleteIdSubTask(Integer.parseInt(sc.nextLine()));
                taskManager.getAll();
                System.out.println("Закончили удалять subTask по id");
            } else if (line.equals("8")) {
                System.out.println("Начинаем обновлять задачу по id");
                taskManager.getAll();
                System.out.println("Какую задачу обновить?");
                String num = sc.nextLine();
                if (taskManager.tasks.containsKey(Integer.parseInt(num))) {
                    System.out.println("Task:");
                    taskManager.update(Integer.parseInt(num), new Task(sc.nextLine()));
                } else if (taskManager.epics.containsKey(Integer.parseInt(num))) {
                    System.out.println("Epic:");
                    epic = taskManager.updateEpic(Integer.parseInt(num), new Epic(sc.nextLine()));
                    while (true) {
                        System.out.println("Создать subTask?");
                        System.out.println("1-Yes");
                        System.out.println("2-No");
                        String numSub = sc.nextLine();
                        if (numSub.equals("1")) {
                            subTask = taskManager.createSubTask(new SubTask(sc.nextLine()));
                            epic.addSubtaskInEpic(subTask);
                            taskManager.getAll();
                        } else if (numSub.equals("2")) {
                            System.out.println("subTask не создана.");
                            break;
                        } else break;
                    }
                } else System.out.println("Нет такой задачи.");
                System.out.println("Закончили обновлять задачу по id");
            } else if (line.equals("9")) {  // не меняет в epic`e
                taskManager.getAll();
                System.out.println("Начинаем обновлять subTask по id");
                System.out.println("Какую subTask нужно обновить?");
                String num = sc.nextLine();
                System.out.println("Введите новое наименование");
                String name = sc.nextLine();
                taskManager.updateSubTask(Integer.parseInt(num), new SubTask(name));
                System.out.println("Закончили обновлять subTask по id");
            } else if (line.equals("10")) {
                System.out.println("Начинаем менять статус подзадачи");
                System.out.println("Какую задачу поменять?");
                taskManager.addStatus();
                System.out.println("Закончили менять статус подзадачи");
            } else {
                System.out.println("Неизвестная команда");
                break;
            }
        }
    }

    protected static void printMenu() {
        System.out.println("""
                Выберите действие:
                1 - Создать задачу.
                2 - Получить список всех задач.
                3 - Удалить все задачи.
                4 - Получить задачу по id.
                5 - Получить subTask по id.
                6 - Удалить задачу по id
                7 - Удалить subTask по id.
                8 - Обновить задачу по id.
                9 - Обновить subTask по id.
                10 - Установить статус.
                Выйти - нажмите любую кнопку.""");
    }
}