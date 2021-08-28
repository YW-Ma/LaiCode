package com.study.algorithms.class06_Binary_Search_Tree;

import com.study.util.TreeNode;

public class Search {
    // Recursion
    public TreeNode search_recursive(TreeNode root, int target) {
        if (root == null || root.key == target) {
            return root;
        }
        if (root.key > target) {
            return search_recursive(root.left, target); // tail recursion
        } else {
            return search_recursive(root.right, target);
        }
    }

    // Iteration
    public TreeNode search(TreeNode root, int target) {
        while (root != null && root.key != target) {
            root = root.key > target ? root.left : root.right;
        }
        return root; // null or target
    }
}
