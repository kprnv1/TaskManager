package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.List;
import java.util.Scanner;

public class Run {
    Scanner sc = new Scanner(System.in);
    TaskManager taskManager = new TaskManager();
    List<Task> taskFromManager;
    Task task;
    Epic epic;
    SubTask subTask;


    public void start() {
        while (true) {
            printMenu();
            String line = sc.nextLine();
            if (line.isEmpty()) {
                return;
            } else if (line.equals("1")) {
                System.out.println("Начинаем создавать задачу");
                taskManager.createTaskAndEpic();
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
                System.out.println("Начинаем получать по id");
                taskManager.getId();
                System.out.println("Закончили получать по id");
            } else if (line.equals("5")) {
                System.out.println("Начинаем удалять по id");
                taskManager.deleteId();
                System.out.println("Закончили удалять по id");
            } else if (line.equals("6")) {
                System.out.println("Начинаем обновлять");
                taskManager.updateTaskAndEpic();
                System.out.println("Закончили обновлять");
            } else if (line.equals("7")) {
                System.out.println("Начинаем удалять подзадачу");
                taskManager.deleteSubTask();
                System.out.println("Закончили удалять подзадачу");
            } else if (line.equals("8")) {
                System.out.println("Начинаем менять статус подзадачи");
                taskManager.setStatus();
                System.out.println("Закончили менять статус подзадачи");
            } else {
                System.out.println("Неизвестная команда");
                break;
            }
        }

    }

    public static void printMenu() {
        System.out.println("""
                Выберите действие:
                1 - Создать задачу.
                2 - Получить список всех задач.
                3 - Удалить все задачи.
                4 - Получить по id.
                5 - Удалить по id.
                6 - Обновить по id.
                7 - Удалить подзадачу по id.
                8 - Установить статус.
                Выйти - нажмите любую кнопку.""");
    }
}


//                        System.out.println("какую - ?");
//                        Epic taskMan = taskManager.getEpic(Integer.parseInt(sc.nextLine()));
//                        System.out.println(taskMan);