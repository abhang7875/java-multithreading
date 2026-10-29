package com.java.threads.join;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactorialCalculation {
    static void main() throws Exception{
        List<Long> numbers = Arrays.asList(0L, 100000000L);

        List<FactorialThread> threads = new ArrayList<>();
        for(long number: numbers) {
            threads.add(new FactorialThread(number));
        }

        for(FactorialThread thread: threads) {
            thread.start();
        }

        for(FactorialThread thread: threads) {
            thread.join(5000);
        }

        for(FactorialThread thread: threads) {
            if(thread.isFinished()) {
                System.out.println("Execution finished for " + thread.curr + " & res = " + thread.res);
            } else {
                thread.interrupt();
            }
        }
    }

    private static class FactorialThread extends Thread {
        private long curr;
        private boolean finished;
        private BigInteger res;

        public FactorialThread(long curr) {
            this.curr = curr;
            this.res = BigInteger.ONE;
        }

        @Override
        public void run() {
            factorial();
            finished = true;
        }

        private void factorial() {
            for(long i = curr; i > 0; i--) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("Exiting Prematurely for: "+ this.curr);
                    res = BigInteger.ZERO;
                    break;
                }
                res = res.multiply(new BigInteger(String.valueOf(i)));
            }
        }

        public boolean isFinished() {
            return this.finished;
        }

        public BigInteger getResult() {
            return this.res;
        }
    }
}
