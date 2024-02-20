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
    protected final InMemoryStorage inMemoryStorage = new InMemoryStorage();
    String PATH = "./src/main/resources/";

    protected final void loadMonthReports() {
        File dir = new File(PATH);
        File[] arrFiles = dir.listFiles();
        assert arrFiles != null;
        List<File> list = Arrays.asList(arrFiles);
        int i = 1;
        for (; i < list.size(); i++) {
            String path1 = "./src/main/resources/m.20210" + i + ".csv";
            ArrayList<ItemMonth> items = loadMonthReport(path1);
            System.out.println(items);
            inMemoryStorage.saveMonthReport(i, items);
        }
    }

    protected final ArrayList<ItemMonth> loadMonthReport(String path) {
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

    private String monthsNum(int num) {
        String[] month = {"Январь", "Февраль", "Март",
                "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь",
                "Октябрь", "Ноябрь", "Декабрь"};
        return month[num - 1];
    }

    protected void printMonthReportInfo() {
        System.out.println(inMemoryStorage.monthReports);
        int month = 1;

        while (month <= inMemoryStorage.monthReports.size()) {
            System.out.println("Месяц: " + getMonthName(month));

            inMemoryStorage.getEarning(month);
            inMemoryStorage.getExpense(month);

            ItemMonth maxEarning = inMemoryStorage.getMaxEarning(month);
            System.out.println("Максимальный доход. Товар: " + maxEarning.name + "." +
                    " Сумма: " + maxEarning.getTotal());

            ItemMonth maxExpense = inMemoryStorage.getMaxExpense(month);
            System.out.println("Максимальная трата. Товар: " + maxExpense.name + "." +
                    " Сумма: " + maxExpense.getTotal());
            month++;


        }
    }


    protected final List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не существует!");
            return Collections.emptyList();
        }
    }

}

