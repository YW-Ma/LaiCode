package com.study.algorithms.class12_DP_2.重听;

public class ArrayHopperII {
    // index [0,1,2,3,4]
    // A     [2,3,1,1,4]
    // M     [      1 0] 代表跳到结尾需要的最小跳数
    //              能跳到 4（+1），然后查表M[4], 1+M[4] = 1
    // M     [    2 1 0] 代表跳到结尾需要的最小跳数
    //            能跳到M[3], 所以是 1+M[3] = 2
    // M     [  1 2 1 0] 代表跳到结尾需要的最小跳数
    //          能跳到M[2],M[3],M[4],
    //          options: {1+M[2], 1+M[3], 1+M[4]} ==> {3,2,1} ==> min is 1
    // M     [  1 2 1 0] 代表跳到结尾需要的最小跳数
    //         能跳到M[1],M[2]
    //         options:{1+M[1], 1+M[2]} ==> {2, 3} ==> min is 2
    // M     [2 1 2 1 0]
    //       所以M[0]是2，即从0跳到结尾需要至少跳2次。

    // meaning: M[i] stands for the min num of jumps from i-th element to the end
    // base case: M[n-1] = 0
    // induction rule: M[i] = min{1+M[j] | i < j <= i + A[i]}

    public int minJump(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        int[] M = new int[n];
        // base rule:
        M[n - 1] = 0;
        // induction rule: for each position(i), find all options(j), get the smallest one.
        for (int i = n - 2; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + A[i]; j++) {
                min = Math.min(min, 1 + M[j]);
            }
            M[i] = min;
        }
        return A[0];
    }
}
