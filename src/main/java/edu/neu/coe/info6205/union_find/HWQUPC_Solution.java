package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class HWQUPC_Solution{

    static int count(int n) {
        int pairsGenerated = 0;
        Random random = new Random();
        int num1;
        int num2;
        UF_HWQUPC ufClient = new UF_HWQUPC(n);
        while (ufClient.components() != 1) {
            num1 = random.nextInt(n);
            num2 = random.nextInt(n);
            if (!ufClient.connected(num1, num2)) ufClient.union(num1, num2);
            pairsGenerated++;
        }
        return pairsGenerated;
    }

    public static void main(String[] args) {
        for (int i=100; i<110; i++) {
            System.out.println(count(i));
        }
    }
}
