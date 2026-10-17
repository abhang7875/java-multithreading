package com.java.threads.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RobGame {
    private static final int MAX_PASS = 9999;
    static class Vault {
        private final int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean guess(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return this.password == guess;
        }
    }

    static abstract class HackerThread extends Thread{
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
        }
    }

    static class AscendingThread extends HackerThread {

        public AscendingThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int i = 0; i < MAX_PASS; i++) {
                if(vault.guess(i)) {
                    System.out.println(Thread.currentThread().getName() + " robbed a vault: " + i);
                    System.exit(0);
                }
            }
        }
    }

    static class DescendingThread extends HackerThread {

        public DescendingThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int i = MAX_PASS; i >= 0; i--) {
                if(vault.guess(i)) {
                    System.out.println(Thread.currentThread().getName() + " robbed a vault: " + i);
                    System.exit(0);
                }
            }
        }
    }

    static class PoliceThread extends Thread{
        @Override
        public void run() {
            for(int i = 0; i < 10; i++) {
                System.out.println(i+1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Robbers Caught..!");
            System.exit(0);
        }
    }

    static void main() {
        Random r = new Random();
        Vault v = new Vault(r.nextInt(MAX_PASS));

        List<Thread> threads = new ArrayList<>();
        threads.add(new AscendingThread(v));
        threads.add(new DescendingThread(v));
        threads.add(new PoliceThread());

        for(Thread thread: threads) {
            thread.start();
        }
    }
}