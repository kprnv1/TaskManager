import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        StepTracker stepTracker = new StepTracker(scanner);

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                stepTracker.addNewNumberStepsPerDay();
            } else if (command == 2) {
                System.out.println("Введите количество шагов: ");
                stepTracker.changeStepGoal();
            } else if (command == 3) {
                System.out.println("Печатаем статистику: ");
                stepTracker.printStatistic();
            } else if (command == 0) {
                System.out.println("Пока!");
                scanner.close();
                return;
            } else {
                System.out.println("Такой команды нет!");
            }
        }

    }


    static void printMenu() {
        StringBuilder builder = new StringBuilder();
        builder.append("Выберите: " + "\n")
                .append("1 - ввести количество шагов за определённый день;" + "\n")
                .append("2 - изменить цель по количеству шагов в день;" + "\n")
                .append("3 - напечатать статистику за определённый месяц;" + "\n")
                .append("0 - выйти из приложения." + "\n");
        System.out.println(builder);
    }
}
