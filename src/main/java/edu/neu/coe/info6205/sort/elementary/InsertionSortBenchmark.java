package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.util.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

public class InsertionSortBenchmark{


    public static void main(String[] args) {
        Random random = new Random();
        int n = 4000;

        for (int count=0; count<7; count++) {
            int finalN = n;

            // LIST OF SUPPLIERS TO BE USED
            final Supplier<Integer[]> randomIntegersSupplier = () -> {
                Integer[] result = (Integer[]) Array.newInstance(Integer.class, finalN);
                for (int i = 0; i < finalN; i++) result[i] = random.nextInt();
                return result;
            };

            final Supplier<Integer[]> orderedIntegersSupplier = () -> {
                Integer[] result = (Integer[]) Array.newInstance(Integer.class, finalN);
                for (int i = 0; i < finalN; i++) result[i] = i;
                return result;
            };

            final Supplier<Integer[]> reverseOrderedIntegersSupplier = () -> {
                Integer[] result = (Integer[]) Array.newInstance(Integer.class, finalN);
                for (int i = 0; i < finalN; i++) result[i] = finalN - i;
                return result;
            };

            final double runTime = new Benchmark_Timer<Integer[]>(
                    "InsertionSort Benchmark",
                    (xs) -> Arrays.copyOf(xs, xs.length),
                    InsertionSort::sort,
                    null
            ).runFromSupplier(reverseOrderedIntegersSupplier, 100); // REPLACE SUPPLIER AND TEST FOR EACH TYPE
            System.out.println("Runtime for n value " + n + " is :" +runTime);
            n = n*2;
        }
    }
}
