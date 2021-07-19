package com.study.algorithms.class21_recursion_3;

import com.study.util.TreeNode;

public class MaxPathSumBinaryTreeIII {
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
    
    // Problem: can not get the largest ↑
    // e.g.:  100 10 -99999999 1000000,
    // then it will return 110, since 10 lose the 1000000.
    
    // Another problem: null needs return 0;
    public int maxPathSum(TreeNode root) {
        int[] max = new int[]{Integer.MIN_VALUE};
        maxPathSum(root, max);
        return max[0];
    }
    
    private int maxPathSum(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        // recursion rule:
        // get the max single path from left / right child
        int left = maxPathSum(root.left, max);
        int right = maxPathSum(root.right, max);
        // compute the maxSumPath of current layer ( one path, no "八" path)
        int curMax = Math.max(Math.max(left, right), 0) + root.key;
        max[0] = Math.max(max[0], curMax);
        // return the max single path from root
        return curMax;
    }
}
