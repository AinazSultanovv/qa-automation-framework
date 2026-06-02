
// Модель баг-репорта для системы отслеживания ошибок
package org.qa.models;

public class BugReport {
    private final String bugId;
    private final String title;
    private final String priority;

    public BugReport(String bugId, String title, String priority) {
        this.bugId = bugId;
        this.title = title;
        this.priority = priority;
    }

    public String getBugId() { return bugId; }
    public String getTitle() { return title; }
    public String getPriority() { return priority; }

    @Override
    public String toString() {
        return "BugReport{" + bugId + ", " + priority + ": " + title + "}";
    }
}