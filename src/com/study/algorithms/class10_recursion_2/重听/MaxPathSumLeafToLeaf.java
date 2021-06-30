package com.study.algorithms.class10_recursion_2.重听;

import com.study.util.TreeNode;

public class MaxPathSumLeafToLeaf {
    // Assumption:
    // 1. TreeNode
    // 2. if we cannot find a path, return Integer.MAX_VALUE


    // valid:
    //   Root
    //   /   \
    // left  right

    // invalid:   -->   cannot update globalMax
    //   root
    //   /   \
    //  null

    // globalMax

    // DFS
    //    expect child: return the single path rooted at this child that has the largest sum
    //    what it did: get the max single path from each child, compute the sum of leaf to leaf path, and if it's valid, --> update the globalMax
    //    what it return:
    //       1. both child is null:  root.key
    //       2. one child is null:   not null child return value + root.key
    //       3. no child is null:    max(left,right) + root.key    -->    also the valid situation for globalMax updating

    // Time: O(n)

//    public int maxPathSum(TreeNode root) {
//        int[] globalMax = new int[1]{Integer.MIN_VALUE}
//        if (root == null) {
//            return globalMax[0]; // assumption
//        }
//        getMaxSumPath(root, globalMax);
//    }
}
