package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MonthlyReport {
    static Scanner scanner = new Scanner(System.in);

    static List<String> readFileContents(String path) {
        try {

            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не был найден!");
            return Collections.emptyList();
        }

    }
}