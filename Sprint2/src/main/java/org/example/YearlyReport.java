package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YearlyReport {
    protected final InMemoryStorage inMemoryStorage = new InMemoryStorage();
    private Integer thisYear = 0;
    String PATH = "./src/main/resources/";

    protected final void loadYearReports() {
        File dir = new File(PATH);
        File[] arrFiles = dir.listFiles();
        assert arrFiles != null;
        List<File> lst = Arrays.asList(arrFiles);
        String str = lst.toString();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            int num = Integer.parseInt(matcher.group());
            if (num < 2999) {
                thisYear = num;
                String path1 = "./src/main/resources/y." + thisYear + ".csv";
                ArrayList<ItemYear> items = loadYearReport(path1);
                System.out.println(items);
                inMemoryStorage.saveYearReport(thisYear, items);
            } else return;
        }
    }

    protected final ArrayList<ItemYear> loadYearReport(String path) {
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

    protected final void printYearReportInfo() {
        System.out.println(inMemoryStorage.yearReports);
        System.out.println("Прибыль по каждому месяцу. ");
        System.out.println(thisYear);
        inMemoryStorage.getEarningMonthInYear(thisYear);

        Long earningYear = inMemoryStorage.getEarningYear(thisYear);
        System.out.println("Средний доход за все месяцы в году: " + earningYear);

        Long expenseYear = inMemoryStorage.getExpenseYear(thisYear);
        System.out.println("Средний расход за все месяцы в году: " + expenseYear);
    }

    protected final List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не существует!");
            return Collections.emptyList();
        }
    }

    protected final void revise() {
        for (int i = 0; i < inMemoryStorage.earningInYear.size(); i++) {
            System.out.println("Доход: " + inMemoryStorage.earningInYear.get(i));
        }
        for (int i = 0; i < inMemoryStorage.expenseInYear.size(); i++) {
            System.out.println("Расход: " + inMemoryStorage.expenseInYear.get(i));
        }
        for (int i = 0; i < inMemoryStorage.netProfit.size(); i++) {
            System.out.println("Чистая прибыль: " + inMemoryStorage.netProfit.get(i));
        }

    }
}


