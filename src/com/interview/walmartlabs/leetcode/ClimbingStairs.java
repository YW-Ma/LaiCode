package com.interview.walmartlabs.leetcode;

public class ClimbingStairs {
    // I am climbing a staircase, which has n steps.
    // I can climb 1 or 2 steps
    // How many distinct ways can you climb to the top (由于有序列，所以应该按照amount的角度，而不是1、2的角度来划分)
    // 如此，DFS每个layer代表一次决策后，DP[i] 则会代表到第i步的不同走法。（这个unique和coin的不同，有先后顺序）
    
    // 1: Naive DFS   O(2^n) --> Time Limit Exceeded
    public int climbStairs1(int n) {
        int[] res = new int[]{0};
        dfs1(res, 0, n);
        return res[0];
    }
    private void dfs1(int[] res, int cur, int n) {
        if (cur >= n) { // base case
            if (cur == n) { // valid
                res[0]++;
            }
            return;
        }
        dfs1(res, cur + 1, n);
        dfs1(res, cur + 2, n);
    }
    
    
    // 2: DFS with memory    T:O(n) S:O(n)
    public int climbStairs2(int n) {
        int[] memo = new int[n + 1];
        return dfs2(0, n, memo);
    }
    private int dfs2(int cur, int n, int[] memo) {     // 有三个base case。
        if (cur > n) { // base case
            return 0;
        }
        if (cur == n) { // valid
            return 1;
        }
        if (memo[cur] > 0) { // from memo
            return memo[cur];
        }
        memo[cur] = dfs2(cur + 1, n, memo) + dfs2(cur + 2, n, memo);
        return memo[cur];
    }
    
    // DP T:O(n) S:O(1)     --> DP[i] = DP[i-1] + DP[i-2]  we only need three variable, no need O(n) space
    public int climbStair(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int dp1 = 0;
        int dp2 = 1;
        for (int i = 2; i <= n; i++) {
            int tmp = dp1 + dp2;
            dp1 = dp2;
            dp2 = tmp;
        }
        return dp1 + dp2;
    }
}
