package com.interview.tiktok;

public class GasStation_AldinArray {
    // https://medium.com/beyond-programming/alaaddin-and-his-carpet-algorithm-problem-of-the-week-i-8b4e39cc13bb
    // https://leetcode-cn.com/problems/gas-station/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--30/
    // 暴力思想: 对每个点计算能否走一圈。 for-while-loop.  是O(n^2)
    // 更新：如果i最远只能到达j，那么[i+1, j] 这个区段不可能有开始点，他们都超不过j。 所以下次直接尝试 i = j + 1的情况。 O(n)
    // 这个更新思路原版在力扣里面，简化版是medium那个网站的

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        // 对每个点
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            while (remain - cost[j] >= 0) {
                remain = remain + cost[i] + gas[(j + 1) % n]; // remian更新为走一段路并获得补给后的值
                j = (j + 1) % n;
                if (j == i) {
                    return i;
                }
            }

            // 不可能有答案的情况：已知[0, i] 都没有答案才会走到这里，但是发现j<i, 即说明[i, n) 有没有答案
            if (j < i) {
                return -1;
            }
            // 跳过[i+1,j] 这个不可能区段:
            i = j;
        }
        return -1; // 不可能走到这里。
    }
}
