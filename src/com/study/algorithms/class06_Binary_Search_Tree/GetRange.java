package com.study.algorithms.class06_Binary_Search_Tree;

import com.study.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class GetRange {
  public List<Integer> getRange(TreeNode root, int min, int max) {
    List<Integer> range = new ArrayList<>();
    inOrder(root, min, max, range);
    return range;
  }

  private void inOrder(TreeNode root, int min, int max, List<Integer> range) {
    if (root == null) {
      return;
    }
    if (root.key >= min) {
      inOrder(root.left, min, max, range);
    }
    if (root.key >= min && root.key <= max) {
      range.add(root.key);
    }
    if (root.key <= max) {
      inOrder(root.right, min, max, range);
    }
  }
}