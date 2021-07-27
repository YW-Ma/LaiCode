package com.study.algorithms.final2;

public class PackingUpSwags {
    // DP:
    //
    // left part:   pending the pack (find results in DP array)
    // right part:  pack now
    
    // 10
    // 9 1,  8 2,  7 3,  6 4,  5 5,  4 6,  3 7,  2 8,  1 9
    // 1, 4, 9 are square number
    // So, DP[10] = min(DP[9] + 1,   DP[6] + 1,  DP[1] + 1)
    
    // base rule: DP[0] = 0(not used), DP[1] = 1
    // induction rule: DP[i] = min(DP[j] + 1), where 1<=j<i && j is square number
    
    // TC: O(n) * O(n) * O(1) = O(n), where n is the input num;
    
    public int getPacksNum(int num) {
        if (num <= 0) {
            return 0;
        }
        int[] DP = new int[num + 1];
        DP[0] = 0;
        DP[1] = 1;
        for (int i = 2; i <= num; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                if (isSquareNum(j)) {
                    min = Math.min(min, DP[i - j] + 1);
                }
            }
            DP[i] = min;
        }
        return DP[num];
    }
    
    private boolean isSquareNum(int j) {
        for (int i = 1; i <= j; i++) {
            if (i * i == j) {
                return true;
            }
        }
        return false;
    }
}
