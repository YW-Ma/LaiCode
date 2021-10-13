package com.interview.walmartlabs.leetcode;

public class CoinChange {
    //    https://leetcode.com/problems/coin-change/solution/
    // DP: 注意事项是可能在中间遇到无法找零的情况，比如 只给2这个coin，但是让凑3
    //  DP 数组init是：[0, _, _, _]
    //              ：[0, -1, _, _]    1-2 越界了
    //              ：[0, -1, 1, _]    2-2 = 0 继承0加一
    //              ：[0, -1, 1, -1]   3-2 没有越界，但是指向一个-1，即一个无法凑到的数字，所以不变。 最后会赋值-1
    //     返回 dp[amount] 即-1
    //  所以我的思路是有一个 fewer，初始化为Integer.MAX_VALUE, 然后尝试用coins中的来更新（idx服从不越界，且指向不为-1）。 最后检查如果还是MAX_VALUE, 说明更新失败，赋值-1.否则为fewer + 1.
    // TC: O(amount * coins.length)
    
    // base rule:
    //      dp[0] = 0;
    //      dp[1] = 1;
    // induction rule:
    //      dp[i] = min{dp[j] | j = i - each coin value} + 1   即检查一遍本次应该选哪个币值。
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; // for value i, fewest number of coins we need is dp[i]
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            // choose one of coins, check if it is valid.
            // if valid, grasp as "fewer" --> choose the fewest and +1.
            int fewer = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                int idx = i - coins[j]; // 满足两个条件：1. 边界合法，2. 指向一个有解的值才能继承（即非-1）
                if (idx < 0 || dp[idx] == -1) {
                    continue;
                }
                if (fewer > dp[idx]) {
                    fewer = dp[idx];
                }
            }
            // 下面这个if else 可以用三元表达式替换：
            dp[i] = (fewer == Integer.MAX_VALUE) ? -1 : fewer + 1;
//            if (fewer == Integer.MAX_VALUE) { // no valid predecessor
//                dp[i] = -1;
//            } else { // has valid predecessor
//                dp[i] = fewer + 1;
//            }
        }
        return dp[amount];
    }
    
    
}
