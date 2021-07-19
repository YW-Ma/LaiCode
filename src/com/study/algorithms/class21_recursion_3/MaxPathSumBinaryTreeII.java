package com.study.algorithms.class21_recursion_3;

import com.study.util.TreeNode;

public class MaxPathSumBinaryTreeII {
    // https://app.laicode.io/app/problem/139
    public int maxPathSum(TreeNode root) {
        int[] globalMax = new int[]{Integer.MIN_VALUE};
        helper(globalMax, root);
        return globalMax[0];
    }
    
    private int helper(int[] globalMax, TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = Math.max(0, helper(globalMax, root.left));
        int right = Math.max(0, helper(globalMax, root.right));
        
        globalMax[0] = Math.max(globalMax[0], left + root.key + right);
        
        return Math.max(left + root.key, right + root.key);
    }
}
