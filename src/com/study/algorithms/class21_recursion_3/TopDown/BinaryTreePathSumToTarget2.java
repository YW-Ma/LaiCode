package com.study.algorithms.class21_recursion_3.TopDown;

import com.study.util.TreeNode;

import java.util.*;

// 这是DP。 如果硬要归类，那么是TopDown（记录所有的prefix）
public class BinaryTreePathSumToTarget2 {
    // https://app.laicode.io/app/problem/141
    // Solution 1:
    // pre-order the tree
    // but instead of doing a for loop in each step
    // we maintain a path_prefix sum
    
    // if cur_prefix_sum - target = historical_prefix_sum
    // 即 当前路径 - 半中央到当前节点的路径 = root到某个历史上的点的路径
    // 那么我们知道，这个历史上的点，到当前节点，就是和威target的路径。
    
    // Time: O(n)
    
    public boolean exist(TreeNode root, int sum) {
        // HashMap<Integer, TreeNode> prefixSumTracker = new HashMap<>(); // map prefixPathValue to ending TreeNode
        // 如果只需要知道"有没有" 那么用Set就够了
        Set<Integer> prefixSumTracker = new HashSet<>();
        
        if (root == null) {
            return false;
        }
        prefixSumTracker.add(0);  // 【大坑2】 这里应先计入0，否则如果target path就是从root出发，就会被漏掉。
        return helper(root, prefixSumTracker, 0, sum);
    }
    
    private boolean helper(TreeNode root, Set<Integer> prefixSumTracker, int prevSum, int sum) {
        prevSum += root.key;
        // pre-order traverse
        if (prefixSumTracker.contains(prevSum - sum)) {
            return true;
        }
        boolean needRemove = prefixSumTracker.add(prevSum);  // 【大坑3】 这是一个set，它是不能重复的，所以一定要看一下是否needRemove。不能盲目吃了吐。（没吃就不能吐了）
        // 问：为什么不包括"left与right都为非null的情况" --> 答：这是pre-order，不是求sum。所以只需要每个分支走或不走。
        // 问：为什么下面的是错的：因为过早return了，如果走右边能找到呢？ 左边不能说是返回false就让它传递了。    【大坑1】
//        if (root.left != null) {
//            return helper(root.left, prefixSumTracker, prevSum, sum);
//        }
//        if (root.right != null) {
//            return helper(root.right, prefixSumTracker, prevSum, sum);
//        }
        
        if (root.left != null && helper(root.left, prefixSumTracker, prevSum, sum)) {
            return true; // false的那个情况不要返回，而是应该进一步考虑right那边能不能行
        }
        if (root.right != null && helper(root.right, prefixSumTracker, prevSum, sum)) {
            return true;
        }
        if (needRemove) {
            prefixSumTracker.remove(prevSum);
        }
        return false;
    }
}
