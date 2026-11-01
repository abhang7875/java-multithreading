package com.java.threads.deadlock;

import java.util.Random;

public class TrainPass {

    static void main() {
        InterSection interSection = new InterSection();
        Thread one = new Thread(new TrainA(interSection));
        Thread two = new Thread(new TrainB(interSection));

        one.start();
        two.start();
    }
    private static class TrainA implements Runnable{
        private InterSection interSection;
        Random random = new Random();

        public TrainA(InterSection intersection) {
            this.interSection = intersection;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(random.nextInt(5));
                } catch(InterruptedException e) {
                    //ignore
                }
                interSection.takeRoadA();
            }
        }

    }

    private static class TrainB implements Runnable{
        private InterSection interSection;
        Random random = new Random();

        public TrainB(InterSection intersection) {
            this.interSection = intersection;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(random.nextInt(5));
                } catch(InterruptedException e) {
                    //ignore
                }
                interSection.takeRoadB();
            }
        }

    }
    private static class InterSection {
        private final Object roadA = new Object();
        private final Object roadB = new Object();

        public void takeRoadA() {
            synchronized (roadA) {
                System.out.println("Road A is blocked for : " + Thread.currentThread().getName());
                synchronized (roadB) {
                    System.out.println("Road A is available for pass");
                }
            }
        }
        public void takeRoadB() {
            synchronized (roadB) {
                System.out.println("Road B is blocked for : " + Thread.currentThread().getName());
                synchronized (roadA) {
                    System.out.println("Road B is available for pass");
                }
            }
        }
    }
}
