package com.java.threads.atomic;

public class MinMaxMetrics {

        // Add all necessary member variables
        private volatile long min;
        private volatile long max;

        /**
         * Initializes all member variables
         */
        public MinMaxMetrics() {
            // Add code here
            min = Long.MAX_VALUE;
            max = Long.MIN_VALUE;
        }

        /**
         * Adds a new sample to our metrics.
         */
        public void addSample(long newSample) {
            // Add code here
            synchronized (this) {
                min = Math.min(min, newSample);
                max = Math.max(max, newSample);
            }
        }

        /**
         * Returns the smallest sample we've seen so far.
         */
        public long getMin() {
            // Add code here
            return this.min;
        }

        /**
         * Returns the biggest sample we've seen so far.
         */
        public long getMax() {
            // Add code here
            return this.max;
        }

}
