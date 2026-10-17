package com.java.threads.practice;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {
    List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void exexuteAll() {
        System.out.println("Executing "+ tasks.size() + " tasks.");
        List<Thread> threads = new ArrayList<>();

        for(Runnable task: tasks) {
            threads.add(new Thread(task));
        }

        for(Thread task: threads) {
            task.start();
        }
    }
}
