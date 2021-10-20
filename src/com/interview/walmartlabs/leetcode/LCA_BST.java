package com.interview.walmartlabs.leetcode;

import com.study.util.TreeNode;

public class LCA_BST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case:
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        
        // recursion rule:
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root; // 被夹在中间的情况，说明分别在左右子树
        }
    }
}
