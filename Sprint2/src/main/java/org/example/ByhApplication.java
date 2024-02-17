package org.example;

import java.util.Scanner;

public class ByhApplication {
    private Scanner scanner;
    private MonthlyReport monthlyReport;
    private YearlyReport yearlyReport;
    InMemoryStorage inMemoryStorage = new InMemoryStorage();

    public void run() {
        System.out.println("Введите команду: ");
        scanner = new Scanner(System.in);
        monthlyReport = new MonthlyReport();
        yearlyReport = new YearlyReport();
        while (true) {
            printMenu();
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                return;
            } else if (line.equals("1")) {
                System.out.println("Начинаем считывать месячные отчеты");
                monthlyReport.loadMonthReports();
                System.out.println("Закончили считывать месячные отчеты");
            } else if (line.equals("2")) {
                System.out.println("Начинаем считывать годовой отчет");
                yearlyReport.loadYearReports();
                System.out.println("Закончили считывать годовой отчет");
            } else if (line.equals("3")) {
                System.out.println("Начинаем сверять отчеты");
//                yearlyReport.revise();
                inMemoryStorage.rev();
                System.out.println("Закончили сверять отчеты");
            } else if (line.equals("4")) {
                System.out.println("Начинаем выводить инфу о всех месячных отчетах");
                monthlyReport.printMonthReportInfo();
                System.out.println("Закончили выводить инфу о всех месячных отчетах");
            } else if (line.equals("5")) {
                System.out.println("Начинаем выводить инфу о всех годовых отчетах");
                yearlyReport.printYearReportInfo();
                System.out.println("Закончили выводить инфу о всех годовых отчетах");
            } else {
                System.out.println("Неизвестная команда");
                break;
            }
        }
    }

    public void printMenu() {
        System.out.println("Меню: ");
        System.out.println("1 - Считать все месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчеты");
        System.out.println("4 - Вывести информацию о всех месячных отчетов");
        System.out.println("5 - Вывести информацию о годовом отчете");
        System.out.println("Нажмите Enter для завершения программы");
    }
}
