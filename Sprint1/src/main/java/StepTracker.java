import java.lang.reflect.Array;
import java.util.Scanner;

public class StepTracker {
    private final Scanner scanner;
    public static MonthData[] monthToData;
    private int goalByStepsPerDay;

    public StepTracker(Scanner scan) {
        scanner = scan;
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
        goalByStepsPerDay = 10_000;
    }

    public void changeStepGoal() {
        int stepsPerDay = scanner.nextInt();
        if (stepsPerDay <= 0) {
            System.out.println("Выберите другую цель!");
            return;
        } else goalByStepsPerDay = stepsPerDay;
        System.out.println("Новая цель : " + goalByStepsPerDay);
    }

    private String getMonthName(int month) {
        String[] numMonth = {"Января", "Февраля", "Марта",
                "Апреля", "Мая", "Июня",
                "Июля", "Августа", "Сентября",
                "Октября", "Ноября", "Декабря"};
        return Array.get(numMonth, month - 1).toString();
    }

    public void addNewNumberStepsPerDay() {
        System.out.println("Введите номер месяца от 1 до 12");
        int numMonth = scanner.nextInt();
        if (numMonth < 1 || numMonth > 12) {
            System.out.println("Ошибка! Введите номер месяца от 1 до 12 (включительно)");
        } else {
            System.out.println("Введите день от 1 до 30");
            int dayOfMonth = scanner.nextInt();
            if (dayOfMonth < 1 || dayOfMonth > 30) {
                System.out.println("Ошибка! Введите день от 1 до 30 (включительно)");
            } else {
                System.out.println("Введите количество шагов");
                int step = scanner.nextInt();
                if (step < 0) {
                    System.out.println("Ошибка! Введите количество шагов > 0");
                } else {
                    monthToData[numMonth - 1].days[dayOfMonth - 1] = step;
                    System.out.println("Отлично! Количество шагов за " +
                            dayOfMonth + " " + getMonthName(numMonth) +
                            ": " + monthToData[numMonth - 1].days[dayOfMonth - 1]);

                }
            }
        }
    }

    public void printStatistic() {
        System.out.println("Введите число месяца");
        int numMonth = scanner.nextInt();
        StringBuilder steps = new StringBuilder();
        if (numMonth < 0 || numMonth > 12) {
            System.out.println("Ошибка! Введенное значение находится вне диапазона 1 ... 12");
        } else {
            for (int i = 0; i < monthToData[numMonth - 1].days.length; i++) {
                steps.append(i + 1).append(" день: ").append(monthToData[numMonth - 1].days[i]);
                if (i < monthToData[numMonth - 1].days.length - 1) {
                    steps.append(", ");
                }
            }
            int stepsPerMonth = MonthData.sumStepsFromMonth();
            System.out.println("Статистика за " + getMonthName(numMonth) + ":");
            System.out.println(steps);
            System.out.println("Общее количество шагов за месяц: " + stepsPerMonth);
            System.out.println("Максимальное пройденное количество шагов в день за месяц: " + MonthData.maxSteps(numMonth));
            System.out.println("Среднее количество шагов: " + MonthData.getAverageSteps(numMonth));
            System.out.println("Пройденная дистанция (в км): " + Converter.convertToKm(stepsPerMonth));
            System.out.println("Количество сожжённых килокалорий: " + Converter.convertStepsToKilocalories(stepsPerMonth));
            System.out.println("Лучшая серия: " + MonthData.bestSeries(numMonth));
            System.out.println();
        }
    }
}
