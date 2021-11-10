package edu.neu.coe.info6205.sort.par;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
public class Main {

    public static void main(String[] args) {

        // default values in case command line arguments are not passed
        configuration.put("c", 2000); // cutoff mark for system sort
        configuration.put("t", 7); // thread count
        configuration.put("s", 2000000); // size of the array

        processArgs(args);
        ArrayList<Long> timeList;

        ParSort.cutoff = configuration.get("c");

        timeList = computeSortTime();
        try {
            FileOutputStream fis = new FileOutputStream("./src/results/parallelsort/co_" + ParSort.cutoff + "as_" + configuration.get("s") + "result.csv");
            OutputStreamWriter isr = new OutputStreamWriter(fis);
            BufferedWriter bw = new BufferedWriter(isr);
            int j = 0;
            for (long i : timeList) {
                String content = (int) Math.pow(2, j) + "," + i + "\n";
                j++;
                bw.write(content);
                bw.flush();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Long> computeSortTime() {
        Random random = new Random();
        int[] array = new int[configuration.get("s")];
        ArrayList<Long> timeList = new ArrayList<>();
        for (int k = 0; k < 11; k++) {
            int threadCount = (int) Math.pow(2,k);
            ParSort.forkJoinPool = new ForkJoinPool(threadCount);
            long time;
            long startTime = System.currentTimeMillis();
            for (int t = 0; t < 10; t++) {
                for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                ParSort.sort(array, 0, array.length);
            }
            long endTime = System.currentTimeMillis();
            time = (endTime - startTime);
            timeList.add(time);

            System.out.println("cutoff：" + ParSort.cutoff + "  :threads：" + threadCount + "  :arraySize：" + configuration.get("s") + "  :Time:  " + time + "ms");

        }
        return timeList;
    }

    private static void processArgs(String[] args) {
        String[] xs = args;
        while (xs.length > 0)
            if (xs[0].startsWith("-")) xs = processArg(xs);
        System.out.println(configuration);
    }

    private static String[] processArg(String[] xs) {
        String[] result = new String[xs.length-2];
        System.arraycopy(xs, 2, result, 0, xs.length - 2);
        processCommand(xs[0], xs[1]);
        return result;
    }

    private static void processCommand(String x, String y) {
        if (x.equalsIgnoreCase("-s")) setConfig("s", Integer.parseInt(y));
        else if (x.equalsIgnoreCase("-t")) setConfig("t", Integer.parseInt(y));
        else if (x.equalsIgnoreCase("-c")) setConfig("c", Integer.parseInt(y));
    }

    private static void setConfig(String x, int i) {
        configuration.put(x, i);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String, Integer> configuration = new HashMap<>();


}
