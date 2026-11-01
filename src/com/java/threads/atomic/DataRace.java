package com.java.threads.atomic;

public class DataRace {

    static void main() throws InterruptedException {
        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(() -> {
            for(int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedResource.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for(int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedResource.checkForDataRace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
    private static class SharedResource {
        private volatile int x = 0;
        private volatile int y = 0;

        public void increment() {
            x++;
            y++;
        }

        public void checkForDataRace() {
            if(y > x) {
                System.out.println("y > x : Data race condition should not be possible.");
            }
         }
    }

}
