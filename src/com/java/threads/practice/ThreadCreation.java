package com.java.threads.practice;

public class ThreadCreation {
    static void main() throws Exception{
        Thread thread = new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " is executing");
        });
        System.out.println("Thread " + Thread.currentThread().getName() + " is executing (before)");
        thread.start();
        System.out.println("Thread " + Thread.currentThread().getName() + " is executing (after)");
        Thread.sleep(1000);
    }
}
