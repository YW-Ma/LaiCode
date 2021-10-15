package com.interview.walmartlabs.leetcode;

public class CoinChangeII {
    // 最重要的是防止重复
    // 尝试了三个方法：
    // 1. DP针对amount - 失败了，因为无法去重
    // 2. DFS针对coin分层 - 成功，注意for循环写法。
    // 3. DP针对coin，相当于2变成DP - 还没出结果。
    public int change(int amount, int[] coins) {
        int[] res = new int[1];
        dfs(res, amount, 0, coins);
        return res[0];
    }
    private void dfs(int[] res, int amount, int idx, int[] coins) {
        // base case:
        if (amount == 0) {
            res[0]++;
            return;
        }
        if (idx == coins.length) {
            return;
        }
        
        // recursion rule:
        for (int i = 0; i <= amount; i += coins[idx]) {
            dfs(res, amount - i, idx + 1, coins);
        }
    }
    // Time: O(coins ^ amount)
    
    
    public int changeDP(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        // base rule: dp[0] = 1;
        // induction rule: dp[x] = dp[x] + dp[x - coinValue]
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        
        return dp[amount];
    }
    // Time: O(amount * coins)
}
