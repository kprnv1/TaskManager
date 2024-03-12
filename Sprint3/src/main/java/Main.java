import model.Task;
import service.TaskManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        Task task;
        List<Task> taskFromManager;

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
                taskFromManager = taskManager.getAll();
                System.out.println("Get task: " + taskFromManager);
                System.out.println("Всего задач: " + taskFromManager.size());
                System.out.println("Какую из задач вы хотели бы получить? Введите №:");
                task = taskManager.get(Integer.parseInt(sc.nextLine()));
                System.out.println(task);
                System.out.println("Закончили получать по идентификатору");
            } else if (line.equals("5")) {
                System.out.println("Начинаем удалять по идентификатору");
                taskFromManager = taskManager.getAll();
                System.out.println("Get task: " + taskFromManager);
                System.out.println("Всего задач: " + taskFromManager.size());
                System.out.println("Какую из задач вы хотели бы удалить? Введите №:");
                task = taskManager.delete(Integer.parseInt(sc.nextLine()));
                System.out.println("Задача удалена и = " + task);
                System.out.println("Закончили удалять по идентификатору");
            } else if (line.equals("6")) {
                System.out.println("Начинаем обновлять");
                taskFromManager = taskManager.getAll();
                System.out.println("Get task: " + taskFromManager);
                System.out.println("Какую из задач вы хотели бы обновить? Введите №:");
                int line1 = Integer.parseInt(sc.nextLine());
                System.out.println("Введите новую задачу");
                taskManager.update(line1, new Task(sc.nextLine()));
                System.out.println("Задача обновлена. = " + taskManager.getAll());
                System.out.println("Закончили обновлять");
            } else if (line.equals("7")) {
                System.out.println("Начинаем получать список всех подзадач");

                System.out.println("Заканчиваем получать список всех подзадач");
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
                4 - Получить по идентификатору.
                5 - Удалить по идентификатору.
                6 - Обновить.
                Выйти - нажмите любую кнопку.""");
    }
}
