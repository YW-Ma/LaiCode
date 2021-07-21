package com.study.algorithms.class21_recursion_3.TopDown;

import com.study.util.TreeNode;

public class MaxPathSumBinaryTreeIII {
    // max path any to any 一条线上
    // https://app.laicode.io/app/problem/140  注意这个题的坑点：
    // Two Requirements:
    // 1.只能是人字形path的一条腿
    // Both the starting and ending node
    //  of the subpath should be on the same
    //  path from root to one of the leaf nodes
    // 2.can only contains one node
    
    // Solution:
    // 1. What I expect: max sum from left & right
    // 2. what i do: inherit or not, and get the max one
    // 3. what i report: max
    
    // Problem: can not get the largest ↑    【坑点在这里】
    // e.g.:  100 10 -99999999 1000000,
    // then it will return 110, since 10 lose the 1000000.
    
    // Another problem: null needs return 0;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] max = new int[]{Integer.MIN_VALUE};
        maxPathSum(root, 0, max);
        return max[0];
    }
    
    private int maxPathSum(TreeNode root, int prefix, int[] max) {
        prefix = Math.max(0, prefix) + root.key;
        // 检查从当前root到任意后继结点的max value（prefix < 0 则不继承）。更新最大值
        if (root.left == null && root.right == null) {
            max[0] = Math.max(max[0], prefix);
            return prefix;
        }
        if (root.left == null) {
            int curMax = maxPathSum(root.right, prefix, max);
            max[0] = Math.max(max[0], curMax);
            return curMax;
        }
        if (root.right == null) {
            int curMax = maxPathSum(root.left, prefix, max);
            max[0] = Math.max(max[0], curMax);
            return curMax;
        }
        int curMax = Math.max(maxPathSum(root.right, prefix, max), maxPathSum(root.left, prefix, max));
        max[0] = Math.max(max[0], curMax);
        return curMax;
    }
}
