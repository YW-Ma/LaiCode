package com.study.algorithms.class21_recursion_3.TopDown;

import com.study.util.TreeNode;

public class MaxPathSumLeafToRoot {
    // Top Down, pass the prefix sum
    public int maxPathSumLeafToRoot(TreeNode root) {
        return maxPathSumLeafToRoot(root, 0);
    }
    
    private int maxPathSumLeafToRoot(TreeNode root, int prefixSum) {
        // get prefixSum, return the max path
        // 和bottom-up一样，需要考虑四种情况（无孩子、一个左孩子、一个右孩子、两个孩子）
        // BottomUp中，为了汇报自己下面的max，分别（汇报自己，汇报自己+左孩子最大，汇报自己+右孩子最大，汇报自己+孩子中大的）
        // TopDown中，为了汇报自己下面的max，需要告知自己下一级别最新的prefixSum，然后按照情况返回对应孩子的返回值。
        
        // 他俩的区别在于，top-down的算法，是类似于DFS的，最底下的node知道的情报最多
        // bottom-up的算法，则是越靠近root的知道的情报越多
        if (root == null) {
            return prefixSum;
        }
        
        // recursion rule:
        if (root.right == null) {
            return maxPathSumLeafToRoot(root.left, prefixSum + root.key);
        }
        if (root.left == null) {
            return maxPathSumLeafToRoot(root.right, prefixSum + root.key);
        }
        return Math.max(maxPathSumLeafToRoot(root.left, prefixSum + root.key), maxPathSumLeafToRoot(root.right, prefixSum + root.key));
    }
}
