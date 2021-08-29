package com.study.algorithms.class06_Binary_Search_Tree;

import com.study.util.TreeNode;

public class ClosestNumberBST {
    // iteration + check during iteration, O(k) time, kth eleemnt is the closet elements.
    public int closest(TreeNode root, int target) {
        // root not null
        TreeNode res = root;
        while (root != null) {
            if (root.key == target) {
                return root.key;
            } else {
                // 1. update
                if (Math.abs(root.key - target) < Math.abs(res.key - target)) {
                    res = root;
                }
                // 2. go further
                if (root.key > target) {
                    root = root.left;
                } else {
                    root = root.right;
                }
            }
        }
        return res.key;
    }
}
