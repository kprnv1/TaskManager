package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YearlyReport {
    Scanner scanner = new Scanner(System.in);
    InMemoryStorage inMemoryStorage = new InMemoryStorage();
    Integer THIS_YEAR = 0;


//    int THIS_YEAR = 2021;
//    public void loadYearReports() {
//        String path = "./src/main/resources/y.2021.csv";
//        ArrayList<ItemYear> items = loadYearReport(path);
//        System.out.println(items);
//        inMemoryStorage.saveYearReport(THIS_YEAR, items);
//
//    }

    public void loadYearReports() {
        String path = "./src/main/resources/";
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        String str = lst.toString();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            int num = Integer.parseInt(matcher.group());
            if (num < 2999) {
                THIS_YEAR = num;
                String path1 = "./src/main/resources/y." + THIS_YEAR + ".csv";
                ArrayList<ItemYear> items = loadYearReport(path1);
                System.out.println(items);
                inMemoryStorage.saveYearReport(THIS_YEAR, items);
            } else return;
        }
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
        System.out.println(THIS_YEAR);
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

    public void revise() {
        for (int i = 0; i < inMemoryStorage.earningInYear.size(); i++) {
            System.out.println("Доход: " + +inMemoryStorage.earningInYear.get(i));
        }
        for (int i = 0; i < inMemoryStorage.expenseInYear.size(); i++) {
            System.out.println("Расход: " + inMemoryStorage.expenseInYear.get(i));
        }
        for (int i = 0; i < inMemoryStorage.netProfit.size(); i++) {
            System.out.println("Чистая прибыль: " + inMemoryStorage.netProfit.get(i));
        }

    }
}


