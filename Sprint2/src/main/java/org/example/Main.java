package org.example;

import com.opencsv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
Movements("Sprint2/data/m.202103.cvs");
    }
    public static void Movements(String path) {
        ArrayList<String> movementList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                movementList.add(values[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//
//1.Считать все месячные отчёты
//2.Считать годовой отчёт
//3.Сверить отчёты
//4.Вывести информацию о всех месячных отчётах
//5.Вывести информацию о годовом отчёте