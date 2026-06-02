
// Модель результата выполнения теста
package org.qa.models;

import java.util.HashSet;
import java.util.Objects;

public class TestResult {
    private final String testName;
    private final String status;
    private final long durationMs;

    public TestResult(String testName, String status, long durationMs) {
        this.testName = testName;
        this.status = status;
        this.durationMs = durationMs;
    }

    public String getTestName() { return testName; }
    public String getStatus() { return status; }
    public long getDurationMs() { return durationMs; }

    @Override
    public String toString() {
        return "TestResult{" + testName + ", " + status + ", " + durationMs + "ms}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestResult that = (TestResult) o;

        return durationMs == that.durationMs &&
                Objects.equals(testName, that.testName) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testName, status, durationMs);
    }

    public static void main(String[] args) {
        TestResult r1 = new TestResult("LoginTest", "PASS", 1200);
        TestResult r2 = new TestResult("LoginTest", "PASS", 1200);
        TestResult r3 = new TestResult("LoginTest", "FAIL", 1200);

        System.out.println("=== Сравнение ===");
        System.out.println("r1 == r2: " + (r1 == r2));
        System.out.println("r1.equals(r2): " + r1.equals(r2));
        System.out.println("r1.equals(r3): " + r1.equals(r3));

        System.out.println("\n=== Проверка HashSet ===");
        HashSet<TestResult> set = new HashSet<>();
        set.add(r1);
        set.add(r2);
        set.add(r3);

        System.out.println("Размер множества: " + set.size());
        System.out.println("Содержимое: " + set);
    }
}