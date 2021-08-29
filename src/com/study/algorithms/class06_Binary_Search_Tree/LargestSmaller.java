package com.study.algorithms.class06_Binary_Search_Tree;

import com.study.util.TreeNode;

public class LargestSmaller {
    public int largestSmaller_beforeReview(TreeNode root, int target) {
        // find the largest element in the node smaller than target.
        // if there is no such number, return -2^31 --> Integer.MIN_VALUE
        
        // in-order traversal while updating results;
        TreeNode res = null;
        while (root != null) {
            if (root.key >= target) {
                root = root.left;
            } else if (root.key < target) {
                if ((res == null) || (target - root.key < target - res.key)) {
                    res = root;
                }
                
                root = root.right; // 实际上这个分支，都是 root = root.right, 所以 res只可能越来越大。 根本无需上面的if分支。
            }
        }
        return res == null ? Integer.MIN_VALUE : res.key;
    }
    
    public int largestSmaller(TreeNode root, int target) {
        // find the largest element in the node smaller than target.
        // if there is no such number, return -2^31 --> Integer.MIN_VALUE
        
        // in-order traversal while updating results;
        int res = Integer.MIN_VALUE; // 改用int记录，并初始化为最小值。
        while (root != null) {
            if (root.key >= target) {
                root = root.left;
            } else {
                res = root.key; // root必然比res大，毋庸if判断。
                root = root.right;
            }
        }
        return res; // 如果进不去else，就一直是Integer.MIN_VALUE.
    }
}
