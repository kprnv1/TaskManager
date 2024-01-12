import java.util.Scanner;

public class StepTracker {
    Scanner scanner;
    static MonthData[] monthToData;
    int goalByStepsPerDay;

    StepTracker(Scanner scan) {
        scanner = scan;
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
        goalByStepsPerDay = 10_000;
    }

    void changeStepGoal() {
        int stepsPerDay = scanner.nextInt();
        if (stepsPerDay <= 0) {
            System.out.println("Выберите другую цель!");
            return;
        } else goalByStepsPerDay = stepsPerDay;
        System.out.println("Новая цель : " + goalByStepsPerDay);
    }

    String getMonthName(int month) {
        String monthName = "";
        switch (month) {
            case (1):
                monthName = "Января";
                break;
            case (2):
                monthName = "Февраля";
                break;
            case (3):
                monthName = "Марта";
                break;
            case (4):
                monthName = "Апреля";
                break;
            case (5):
                monthName = "Мая";
                break;
            case (6):
                monthName = "Июня";
                break;
            case (7):
                monthName = "Июля";
                break;
            case (8):
                monthName = "Августа";
                break;
            case (9):
                monthName = "Сентября";
                break;
            case (10):
                monthName = "Октября";
                break;
            case (11):
                monthName = "Ноябра";
                break;
            case (12):
                monthName = "Декабря";
                break;
        }
        return monthName;
    }


    void addNewNumberStepsPerDay() {
        System.out.println("Введите номер месяца");
        int numMonth = scanner.nextInt();
        // ввод и проверка номера месяца
        if (numMonth < 1 && numMonth > 12) {
            System.out.println("Ошибка! Введите номер месяца от 1 до 12 (включительно)");
        } else {
            System.out.println("Введите день от 1 до 30");
            // ввод и проверка дня
            int dayOfMonth = scanner.nextInt();
            if (dayOfMonth < 1 && dayOfMonth > 30) {
                System.out.println("Ошибка! Введите день от 1 до 30 (включительно)");
            } else {
                System.out.println("Введите количество шагов");
                int step = scanner.nextInt();
                if (step < 0) {
                    System.out.println("Ошибка! Введите количество дней");
                } else {
                    monthToData[numMonth - 1].days[dayOfMonth - 1] = step;

                    StringBuilder outSuccessful = new StringBuilder();
                    outSuccessful.append("Отлично! Количество шагов за ");
                    outSuccessful.append(dayOfMonth);
                    outSuccessful.append(" ");
                    outSuccessful.append(getMonthName(numMonth));
                    outSuccessful.append(": ");
                    outSuccessful.append(monthToData[numMonth - 1].days[dayOfMonth - 1]);
                    System.out.println(outSuccessful);
                }
            }
        }
    }


    void printStatistic() {
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
// ввод и проверка номера месяца
//        monthData = ;// получение соответствующего месяца
//        int sumSteps = ;// получение суммы шагов за месяц
// вывод общей статистики по дням
// вывод суммы шагов за месяц
// вывод максимального пройденного количества шагов за месяц
// вывод среднего пройденного количества шагов за месяц
// вывод пройденной за месяц дистанции в км
// вывод количества сожжённых килокалорий за месяц
// вывод лучшей серии

}
