package com.interview.walmartlabs.leetcode;

import com.study.util.TreeNode;

public class LCA {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case:
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        
        // recursion rule:
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}
