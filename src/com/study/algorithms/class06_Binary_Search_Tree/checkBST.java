package com.study.algorithms.class06_Binary_Search_Tree;

import com.study.util.TreeNode;

public class checkBST {
  // TC: O(n)
  // SC: O(height) worst O(n)
  public boolean isBST(TreeNode root) {
    return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private boolean isBST(TreeNode root, int minValue, int maxValue) {
    if (root == null) {
      return true;
    }
    if (root.key < minValue || root.key > maxValue) {
      return false;
    }
    return isBST(root.left, minValue, root.key - 1) && isBST(root.right, root.key + 1, maxValue);
  }
}
