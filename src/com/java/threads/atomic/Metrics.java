package com.java.threads.atomic;

import java.util.Random;

public class Metrics {

    static void main() {
        Metric metric = new Metric();
        BusinessLogic businessLogic1 = new BusinessLogic(metric);
        BusinessLogic businessLogic2 = new BusinessLogic(metric);
        MetricPrinter metricPrinter = new MetricPrinter(metric);

        businessLogic1.start();
        businessLogic2.start();
        metricPrinter.start();
    }

    private static class MetricPrinter extends Thread {
        private Metric metric;

        public MetricPrinter(Metric metric) {
            this.metric = metric;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    //ignore
                }
                System.out.println(metric.getAverage());
            }
        }
    }

    private static class BusinessLogic extends Thread {
        private Metric metric;
        Random random = new Random();

        public BusinessLogic(Metric metric) {
            this.metric = metric;
        }

        @Override
        public void run() {
            while(true) {
                long start = System.currentTimeMillis();
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (Exception e) {
                    //ignore
                }
                long end = System.currentTimeMillis();
                metric.addSample(end - start);
            }
        }
    }
    private static class Metric {
        private long count;
        private volatile double average;

        public void addSample(long sample) {
            double currSum = average * count;
            count++;
            average = (currSum + sample) / count;
        }

        public double getAverage() {
            return average;
        }
    }
}
