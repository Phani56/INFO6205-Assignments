package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.*;
import edu.neu.coe.info6205.util.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

public class InsertionSortBenchmark{

    public static void main(String[] args) {
        Random random = new Random();
        int n = 4000;

        final Supplier<Integer[]> integersSupplier = () -> {
            Integer[] result = (Integer[]) Array.newInstance(Integer.class, n);
            for (int i = 0; i < n; i++) result[i] = random.nextInt();
            return result;
        };

        final double runTime = new Benchmark_Timer<Integer[]>(
                "InsertionSort Benchmark",
                InsertionSort::sort
        ).runFromSupplier(integersSupplier, 100);
        System.out.println(runTime);
    }

}
