package com.jiuzhang.DFS;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class 二叉树的直径 {
    // https://leetcode.com/problems/diameter-of-binary-tree/solutions/1102557/official-solution/
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int unused = helper(root);
        return diameter;
    }

    private int helper(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }

        // get the depth of left and right
        // compute the diameter that pass me, update the global diameter
        // return the depth of my subtree.
        int leftDepth = helper(root.left);
        int rightDepth = helper(root.right);
        if (diameter < leftDepth + rightDepth) {
            diameter = leftDepth + rightDepth;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
