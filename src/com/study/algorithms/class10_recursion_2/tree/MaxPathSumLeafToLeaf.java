package com.study.algorithms.class10_recursion_2.tree;

import com.study.util.TreeNode;

public class MaxPathSumLeafToLeaf {
  // So the solution consisits two part: leaf to a turnning node, turnning node to another leaf.
// Assumption:
// 1. root is not null
// 2. input a root, output the sum
// 3. Class TreeNode {int key; TreeNode left; TreeNode right}
// https://app.laicode.io/app/problem/138

// Method: Recursion
/*
            m(5)            -- omit m() funciton call in this tree
           1     2
        3   -1  #  12
      #  #  # #     # #

      each node:
      1. expect the maxPathSum from left and right child
      2. update the globalMax using (left + right + root.key)
      3. return the max(left, right) + root.key

*/

  // Assumption:
  // 1. class TreeNode {int key; TreeNode left; TreeNode right}
  // 2. input is a root node;    output is the int sum
  // 3. if we cannot find the leaf to leaf path, return [Integer.MIN_VALUE] --> or throw an exception
  // 4. invalid situations when updating globalMax
  //      root
  //      /  \
  //    null  null

  //      root
  //      /  \
  //    null  right

  //      root
  //      /  \
  //    left  null

  // 5. valid situation when updating
  //      root
  //      /  \
  //    left  right   (tell me the largest single path from a leaf to this child)  --> single path

  // Solution:
  // DFS:
  // base case: reach the leaf(null), return 0
  // recursive rule:
  //    1.from child:  get the max sum of values in the path from one child to a leaf.
  //    2.what I do:   if it is a valid situation, try to update globalMax. 与下一步的valid情况合并。
  //    3.what I return: (the single path rooted at me)
  //            if both children are not null: max(children results) + my.key,     [并且在这里做update globalMax, 相当于第二步]
  //            if only one child is null: return (the other one) + my.key
  //            if both are null: my.key


  public int maxPathSum(TreeNode root) {
    int[] globalMax = new int[1];
    globalMax[0] = Integer.MIN_VALUE;
    maxPathSum(root, globalMax);
    return globalMax[0];
  }

  private int maxPathSum(TreeNode root, int[] globalMax) {
    // base case:
    if (root == null) {
      return 0;
    }

    // recursion rule:
    // 1. get the left, right single path value
    int left = maxPathSum(root.left, globalMax);
    int right = maxPathSum(root.right, globalMax);
    // 2. update if possible
    // 常见错误2，如果左右孩子中有null，那么这个值是invalid的，不可以去更新。
    if (root.left != null && root.right != null) { // 这才是一个valid的case。
      int sum = left + right + root.key;
      globalMax[0] = Math.max(sum, globalMax[0]);
    }

    // 3. return the max single path
    // 常见错误3，因为孩子的返回值可能是负数，
    //  如果root的孩子是一负数、一个null
    //  那么返回的是 负数 和 0
    //  这个时候就不能求max了，要讨论一下。
    // -- return Math.max(left, right) + root.key;
    if (root.left == null && root.right == null) {
      return root.key;
    } else if (root.left == null) {
      return root.key + right;
    } else if (root.right == null) {
      return root.key + left;
    } else {
      // 这里才能安心求max
      return Math.max(left, right) + root.key;
    }
  }
}
