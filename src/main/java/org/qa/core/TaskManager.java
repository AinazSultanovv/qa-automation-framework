
// Управление задачами и сортировка по приоритету
package org.qa.core;

import java.util.Arrays;

public class TaskManager {

    public static Task[] sortByPriority(Task[] tasks){

        Task []tasksCopy = Arrays.copyOf(tasks, tasks.length);

        for (int i = 0; i<tasksCopy.length; i++){
            for (int j = 0; j < tasksCopy.length - 1 - i; j++) {
                if (tasksCopy[j].getPriority() > tasksCopy[j+1].getPriority()){
                    Task temp = tasksCopy[j];
                    tasksCopy[j] = tasksCopy[j+1];
                    tasksCopy[j+1] = temp;
                }
            }

        }
        return tasksCopy;
    }
}
