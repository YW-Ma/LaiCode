package com.study.database;
import java.util.Date;
public class CacheMiss {
    static void runAdd(int matrix[][], int R, int C, int L) {
        for (int c = 0; c < L; ++c) {
            for (int i = 0; i < R; ++i) {
                for (int j = 0; j < C; ++j) {
                    matrix[i][j]++;
                }
            }
        }
    }

    static void runAddV(int matrix[][], int R, int C, int L) {
        for (int c = 0; c < L; ++c) {
            for (int j = 0; j < C; ++j) {
                for (int i = 0; i < R; ++i) {
                    matrix[i][j]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int m[][] = new int[1024][1024];
        long t1 = System.currentTimeMillis();
        runAddV(m, 1024, 1024, 1000);
        long t2 = System.currentTimeMillis();
        runAdd(m, 1024, 1024, 1000);
        long t3 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        System.out.println(t3 - t2);
        // 1674
        // 383
    }
}
