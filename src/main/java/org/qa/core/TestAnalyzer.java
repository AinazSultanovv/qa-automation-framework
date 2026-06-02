
// Анализ результатов тестирования
package org.qa.core;

import java.util.ArrayList;
import java.util.List;

public class TestAnalyzer {
    private final List<String> results = new ArrayList<>();

    public TestAnalyzer() {

    }

    public void addResult(String result) {
        String normalized = result.toUpperCase().trim();

        if (normalized.equals("PASS") || normalized.equals("FAIL") || normalized.equals("SKIP")) {
            results.add(normalized);
        } else {
            System.out.println("Некорректный результат: " + result + "\"");
        }
    }

    public int count(String status) {
        String target = status.toUpperCase();
        int counter = 0;

        for (String res : results) {
            if (res.equals(target)) {
                counter++;
            }
        }
        return counter;
    }

    public void printReport() {
        System.out.println("📊 Отчёт:");
        System.out.println("Всего тестов: " + results.size());
        System.out.println("Passed: " + count("PASS"));
        System.out.println("Failed: " + count("FAIL"));
        System.out.println("Skipped: " + count("SKIP"));
    }


    public static void main(String[] args) {
        TestAnalyzer analyzer = new TestAnalyzer();
        analyzer.addResult("pass");
        analyzer.addResult("FAIL");
        analyzer.addResult("Skip");
        analyzer.addResult("ERROR");
        analyzer.addResult("PASS");

        analyzer.printReport();
    }
}