package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MonthlyReport {
    InMemoryStorage inMemoryStorage = new InMemoryStorage();

    public void loadMonthReports() {
        String path = "./src/main/resources/";
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        int i = 1;
        for (; i < lst.size(); i++) {
            String path1 = "./src/main/resources/m.20210" + i + ".csv";
            ArrayList<ItemMonth> items = loadMonthReport(path1);
            System.out.println(items);
            inMemoryStorage.saveMonthReport(i, items);
        }
    }

    ArrayList<ItemMonth> loadMonthReport(String path) {
        List<String> lines = readFileContents(path);
        ArrayList<ItemMonth> items = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] rows = line.split(",");
            ItemMonth item = new ItemMonth(rows[0],
                    Boolean.parseBoolean(rows[1]),
                    Integer.parseInt(rows[2]),
                    Long.parseLong(rows[3]));
            items.add(item);
        }
        return items;
    }

    private String getMonthName(int month) {
        return monthsNum(month);
    }

    public String monthsNum(int num) {
        if (num == 1) {
            return "Январь";
        }
        if (num == 2) {
            return "Февраль";
        }
        if (num == 3) {
            return "Март";
        }
        if (num == 4) {
            return "Апрель";
        }
        if (num == 5) {
            return "Май";
        }
        if (num == 6) {
            return "Июнь";
        }
        if (num == 7) {
            return "Июль";
        }
        if (num == 8) {
            return "Август";
        }
        if (num == 9) {
            return "Сентябрь";
        }
        if (num == 10) {
            return "Октябрь";
        }
        if (num == 11) {
            return "Ноябрь";
        }
        if (num == 12) {
            return "Декабрь";
        } else
            return "Такого месяца нет";
    }

    public void printMonthReportInfo() {
        System.out.println(inMemoryStorage.monthReports);
        int month;
        for (int i = 1; i <= inMemoryStorage.monthReports.size(); ) {
            month = i;
            System.out.println("Месяц: " + getMonthName(month));

            inMemoryStorage.getEarning(month);
            inMemoryStorage.getExpense(month);

            ItemMonth maxEarning = inMemoryStorage.getMaxEarning(month);
            System.out.println("Максимальный доход. Товар: " + maxEarning.name + "." +
                    " Сумма: " + maxEarning.getTotal());

            ItemMonth maxExpense = inMemoryStorage.getMaxExpense(month);
            System.out.println("Максимальная трата. Товар: " + maxExpense.name + "." +
                    " Сумма: " + maxExpense.getTotal());

            i++;
        }
    }

    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не существует!");
            return Collections.emptyList();
        }
    }

}

