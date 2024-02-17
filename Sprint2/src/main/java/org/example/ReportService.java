//package org.example;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class ReportService {
//    InMemoryStorage inMemoryStorage = new InMemoryStorage();
//
//    public void loadMonthReports() {
//        int i = 1;
//        for (; i < 4; i++) {
//            String path = "./src/main/resources/m.20210" + i + ".csv";
//            ArrayList<Item> items = loadMonthReport(path);
//            System.out.println(items);
//            inMemoryStorage.saveMonthReport(2021, i, items);
//        }
//    }
//
//    ArrayList<Item> loadMonthReport(String path) {
//        List<String> lines = readFileContents(path);
//        ArrayList<Item> items = new ArrayList<>();
//        for (int i = 1; i < lines.size(); i++) {
//            String line = lines.get(i);
//            String[] rows = line.split(",");
//            Item item = new Item(rows[0],
//                    Boolean.parseBoolean(rows[1]),
//                    Integer.parseInt(rows[2]),
//                    Long.parseLong(rows[3]));
//            items.add(item);
//        }
//        return items;
//    }
//
//    public String monthsNum(int num) {
//        if (num == 1) {
//            return "Январь";
//        }
//        if (num == 2) {
//            return "Февраль";
//        }
//        if (num == 3) {
//            return "Март";
//        }
//        if (num == 4) {
//            return "Апрель";
//        }
//        if (num == 5) {
//            return "Май";
//        }
//        if (num == 6) {
//            return "Июнь";
//        }
//        if (num == 7) {
//            return "Июль";
//        }
//        if (num == 8) {
//            return "Август";
//        }
//        if (num == 9) {
//            return "Сентябрь";
//        }
//        if (num == 10) {
//            return "Октябрь";
//        }
//        if (num == 11) {
//            return "Ноябрь";
//        }
//        if (num == 12) {
//            return "Декабрь";
//        } else
//            return "Такого месяца нет";
//    }
//
//    public void printMonthReportInfo() {
//        System.out.println(inMemoryStorage.monthReports);
//        int month;
//        for (int i = 1; i <= inMemoryStorage.monthReports.size(); ) {
//            month = i;
//            System.out.println("Месяц: " + getMonthName(month));
//
//            Item maxEarning = inMemoryStorage.getMaxEarning(month);
//            System.out.println("Максимальный доход. Товар: " + maxEarning.name + "." +
//                    " Сумма: " + maxEarning.getTotal());
//
//            Item maxExpense = inMemoryStorage.getMaxExpense(month);
//            System.out.println("Максимальная трата. Товар: " + maxExpense.name + "." +
//                    " Сумма: " + maxExpense.getTotal());
//            i++;
//        }
//    }
//
//    private String getMonthName(int month) {
//        return monthsNum(month);
//    }
//
//    List<String> readFileContents(String path) {
//        try {
//            return Files.readAllLines(Path.of(path));
//        } catch (IOException e) {
//            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не существует!");
//            return Collections.emptyList();
//        }
//    }
//
//    ArrayList<Item> loadYearReport(String path) {
//        List<String> lines = readFileContents(path);
//        ArrayList<Item> items = new ArrayList<>();
//        for (int i = 1; i < lines.size(); i++) {
//            String line = lines.get(i);
//            String[] rows = line.split(",");
//            Item item = new Item(rows[0],
//                    Boolean.parseBoolean(rows[1]),
//                    Integer.parseInt(rows[2]),
//                    Long.parseLong(rows[3]));
//            items.add(item);
//        }
//        return items;
//    }
//}
