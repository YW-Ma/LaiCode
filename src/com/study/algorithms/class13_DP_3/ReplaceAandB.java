package com.study.algorithms.class13_DP_3;

public class ReplaceAandB {
    // abaab --> aaaab 1 step
    //       --> abbbb 2 steps
    // 思考：先DP，然后BFS，然后DFS。
    // DP: abaab的子问题是 abaa?

    //    abaab
    // M 001112  ->   M[i]     change first i elements into 'a',  i [0,len]
    // N  322100  <-  N[len-i] change last i elements into 'b',
    // 中文：
    // M[i] 代表前i个字母变成a的开销。base case是i==0，即一个字母都不变（开销0）。 最大是len，即前len个字母都变'a'.
    // N[len - i] 代表后i个字母变成b的开销。base case是i==0，即N[len]，即一个字母都不变（开销0）。 最大是len，即N[0]，即后len个字母都变'b'.

    // globalMin = min(M[k] + N[k+1]), change first i into 'a' and remaining part to 'b',  k in [0, len - 1]
    // i.e.:   abaab
    //        333322
    //        ^       --> first 0 elemnts to 'a', rest part to 'b'
    //             ^  --> all to 'a'

    // len == 1 --> M, N is valid, globalMin is not valid   >>>> so len <= 1 should be put in base case.
    // len == 2 --> M, N is valid, globalMin is valid

    public int minReplacements(String input) {
        if (input == null || input.length() <= 1) {
            return 0;
        }
        int len = input.length();
        int[] M = new int[len + 1];
        int[] N = new int[len + 1];
        int globalMin = Integer.MAX_VALUE;

        // base rule:
        M[0] = 0;
        N[len] = 0;

        // induction rule:
        for (int i = 1; i <= len; i++) { // first i elements
            M[i] = M[i - 1];
            if (input.charAt(i - 1) != 'a') { // index of first i elements is (i-1)
                M[i]++;
            }

            N[len - i] = N[len - i + 1];
            if (input.charAt(len - i) != 'b') {
                N[len - i]++;
            }
        }

        // find the globalMin
        for (int k = 0; k <= len - 1; k++) { // change first k elements into 'a', the rest to 'b'
            int cur = M[k] + N[k + 1];
            globalMin = Math.min(globalMin, cur);
        }

        return globalMin;
    }
}

