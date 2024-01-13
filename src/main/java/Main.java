import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker(scanner);

        int command;
        do {
            printMenu();
            command = scanner.nextInt();
            if (command == 1) {
                stepTracker.addNewNumberStepsPerDay();
            } else if (command == 2) {
                System.out.println("Введите количество шагов: ");
                stepTracker.changeStepGoal();
            } else if (command == 3) {
                System.out.println("Печатаем статистику: ");
                stepTracker.printStatistic();
            } else {
                System.out.println("Такой команды нет!");
            }
        } while (!(command == 0));
    }

    public static void printMenu() {
        System.out.println("Выберите:\n" +
                "1 - ввести количество шагов за определённый день;\n" +
                "2 - изменить цель по количеству шагов в день;\n" +
                "3 - напечатать статистику за определённый месяц;\n" +
                "0 - выйти из приложения.");
    }
}
