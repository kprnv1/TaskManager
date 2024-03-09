import model.Task;
import service.TaskManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        Task task = new Task();
        List<Task> taskFromManager = new ArrayList<>();
        Task taskFromManager1 = new Task();

        while (true) {
            printMenu();
            String line = sc.nextLine();
            if (line.isEmpty()) {
                return;
            } else if (line.equals("1")) {
                System.out.println("Начинаем создавать задачу");
                task = taskManager.create(new Task(sc.nextLine()));
                System.out.println("Create task: " + task);
                System.out.println("Закончили создавать задачу");
            } else if (line.equals("2")) {
                System.out.println("Начинаем получать список всех задач");
                taskFromManager = taskManager.getAll();
                System.out.println("Get task: " + taskFromManager);
                System.out.println("Закончили получать список всех задач");
            } else if (line.equals("3")) {
                System.out.println("Начинаем удалять все задачи");
                taskManager.deleteAll();
                System.out.println("All tasks deleted");
                System.out.println("Закончили удалять все задачи");


            } else if (line.equals("4")) {
                System.out.println("Начинаем получать по идентификатору");
                if(sc.nextLine().equals(String.valueOf(task.getId()))) {
                    System.out.println("равны");
                    taskFromManager1 = taskManager.get(task.getId());
                    System.out.println("Get task: " + taskFromManager1);
                }
                System.out.println("Закончили получать по идентификатору");
            } else if (line.equals("5")) {
                System.out.println("Начинаем удалять по идентификатору");
//                taskManager.delete(taskFromManager.getId());
//                System.out.println("Delete: " + task);
                System.out.println("Закончили удалять по идентификатору");
            } else if (line.equals("6")) {
                System.out.println("Начинаем обновлять");
//                taskFromManager.setName("New name");
//                taskManager.update(taskFromManager);
                System.out.println("Update task: " + taskFromManager);
                System.out.println("Закончили обновлять");
            } else {
                System.out.println("Неизвестная команда");
                break;
            }
        }

//            String input = sc.nextLine();
//            Task task = taskManager.create(new Task(input));
//            System.out.println("Create task: " + task);
//
//            Task taskFromManager = taskManager.get(task.getId());
//            System.out.println("Get task: " + taskFromManager);
//
//            taskFromManager.setName("New name");
//            taskManager.update(taskFromManager);
//            System.out.println("Update task: " + taskFromManager);
//
//            taskManager.delete(taskFromManager.getId());
//            System.out.println("Delete: " + task);

    }

    public static void printMenu() {
        System.out.println("""
                Выберите действие:
                1 - Создать задачу.
                2 - Получить список всех задач.
                3 - Удалить все задачи.
                4 - Получить по идентификатору.
                5 - Удалить по идентификатору.
                6 - Обновить.
                Выйти - нажмите любую кнопку.""");
    }
}
