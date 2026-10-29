package com.java.threads.join;

import java.math.BigInteger;

public class MultithreadedCalculation {

    static void main() throws Exception{
        BigInteger base1 = new BigInteger("1");
        BigInteger power1 = new BigInteger("1000");
        BigInteger base2 = new BigInteger("1");
        BigInteger power2 = new BigInteger("1000");
        PowerCalculation thread1 = new PowerCalculation(base1, power1);
        PowerCalculation thread2 = new PowerCalculation(base2, power2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(thread1.getResult().add(thread2.getResult()));
    }
    private static class PowerCalculation extends Thread {
        BigInteger result;
        BigInteger base, power;

        public PowerCalculation(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
            this.result = BigInteger.ONE;
        }

        @Override
        public void run() {
            calculate();
        }

        private void calculate() {
            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
