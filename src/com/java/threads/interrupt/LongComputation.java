package com.java.threads.interrupt;

import java.math.BigInteger;

public class LongComputation {

    public static void main() {
        Thread task = new Thread(new LongComputationTask(new BigInteger("20000"), new BigInteger("10000000")));

        task.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        task.interrupt();
    }

    private static class LongComputationTask implements Runnable {
        BigInteger base;
        BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + " ^ "+ power + " = " + find());
        }

        private BigInteger find() {
            BigInteger res = BigInteger.ONE;
            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("Exiting Prematurely");
                    return BigInteger.ZERO;
                }
                res = res.multiply(base);
            }
            return res;
        }
    }
}
