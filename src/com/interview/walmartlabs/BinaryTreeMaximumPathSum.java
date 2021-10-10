package com.interview.walmartlabs;

public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        maxPathSum(null);
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    /*
    What I expected from my children: report the maximum subpath ends at this child.

    What I will do: update globalMax using:
        (left + right + root.val)       if both positive
        (max(left, right) + root.val) if one is negative
        (root.val) if both negative

    What I will report: the maximum subpath ends at root

    Base case: leaf node, covered by recursion rule.
    */
    public static int maxPathSum(TreeNode root) throws IllegalArgumentException {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        int[] globalMax = new int[]{Integer.MIN_VALUE};
        helper(root, globalMax);
        return globalMax[0];
    }

    private static int helper(TreeNode root, int[] globalMax) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, globalMax);
        int right = helper(root.right, globalMax);
        int maxSubPath = Math.max(Math.max(0, right), left) + root.val;
        int maxPath = Math.max(0, right) + Math.max(0, left) + root.val;
        if (maxPath > globalMax[0]) {
            globalMax[0] = maxPath;
        }
        return maxSubPath;
    }
}
