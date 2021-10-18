package com.interview.walmartlabs.leetcode;

public class PaintHouse_II {
    // 和PaintHouse一模一样的思路，DFS的layer也是房子，这里也是每次算一个房子的min（省去了不同color的子节点拥有相同color时重复计算的开销）
    public int minCostII(int[][] costs) {
        // n houses, k colors, costs is nxk
        int n = costs.length;
        int k = costs[0].length;
        int[] prevCost = new int[k];
        int[] curCost = new int[k];
        
        // base rule:
        for (int i = 0; i < k; i++) {
            prevCost[i] = costs[0][i];
        }
        
        // induction rule:
        for (int i = 1; i < n; i++) {
            // for each row, check the cost and prevCost
            for (int j = 0; j < k; j++) {
                // get min prevCost
                int minPrevCost = Integer.MAX_VALUE;
                for (int m = 0; m < k; m++) {
                    if (m == j) {
                        continue;
                    }
                    minPrevCost = Math.min(minPrevCost, prevCost[m]);
                }
                
                // get cur cost
                curCost[j] = costs[i][j] + minPrevCost;
            }
            
            // write back:
            for (int j = 0; j < k; j++) {
                prevCost[j] = curCost[j];
            }
        }
        
        // return the smallest one
        int smallestCost = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            smallestCost = Math.min(smallestCost, prevCost[i]);
        }
        return smallestCost;
    }
    // O(N*k) time, O(1) space
}
