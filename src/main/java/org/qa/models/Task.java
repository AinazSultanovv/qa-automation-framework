
// Модель задачи с приоритетом и статусом
package org.qa.models;

public class Task {

    private String title;
    private int priority;
    private boolean isDone;

    public Task(String title, int priority, boolean isDon){
        this.title = title;
        this.priority = priority;
        this.isDone = isDon;
    }
    public String getTitle(){return title;};
    public int getPriority(){return priority;};
    public boolean getisDone(){return isDone;};

    @Override
    public String toString() {
        return "Task{" + title + ", priority=" + priority + ", done=" + isDone + "}";
    }
}
