package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YearlyReport {
    InMemoryStorage inMemoryStorage = new InMemoryStorage();
    int THIS_YEAR = 2021;

    public void loadYearReports() {
        String path = "./src/main/resources/y.2021.csv";
        ArrayList<ItemYear> items = loadYearReport(path);
        System.out.println(items);
        inMemoryStorage.saveYearReport(THIS_YEAR, items);

    }

    ArrayList<ItemYear> loadYearReport(String path) {
        List<String> lines = readFileContents(path);
        ArrayList<ItemYear> items = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] rows = line.split(",");
            ItemYear item = new ItemYear(Integer.parseInt(rows[0]),
                    Long.parseLong(rows[1]),
                    Boolean.parseBoolean(rows[2]));
            items.add(item);
        }
        return items;
    }

    public void printYearReportInfo() {
        System.out.println(inMemoryStorage.yearReports);
        System.out.println("Прибыль по каждому месяцу. ");
        inMemoryStorage.getEarningMonthInYear(THIS_YEAR);

        Long earningYear = inMemoryStorage.getEarningYear(THIS_YEAR);
        System.out.println("Средний доход за все месяцы в году: " + earningYear);

        Long expenseYear = inMemoryStorage.getExpenseYear(THIS_YEAR);
        System.out.println("Средний расход за все месяцы в году: " + expenseYear);
    }

    List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не существует!");
            return Collections.emptyList();
        }
    }

    public void revise() {             //сверка
        for (int i = 0; i < inMemoryStorage.earningInYear.size(); i++) {
            System.out.println("Доход: " +  + inMemoryStorage.earningInYear.get(i));
        }
        for (int i = 0; i < inMemoryStorage.expenseInYear.size(); i++) {
            System.out.println("Расход: " + inMemoryStorage.expenseInYear.get(i));
        }
        for (int i = 0; i < inMemoryStorage.netProfit.size(); i++) {
            System.out.println("Чистая прибыль: " + inMemoryStorage.netProfit.get(i));
        }
//        for (int i = 0; i < inMemoryStorage.earningInMonths.size(); i++) {
//            System.out.println("+++" + inMemoryStorage.earningInMonths.get(i));
//        }
//        for (int i = 0; i < inMemoryStorage.expenseInMonths.size(); i++) {
//            System.out.println("---" + inMemoryStorage.expenseInMonths.get(i));
//        }

    }
}


