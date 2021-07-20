package com.study.algorithms.class21_recursion_3.BottomUp;

import com.study.util.TreeNode;

public class MaxPathSumLeafToRoot {
    // 如果左孩子是null，右孩子是-100.那么会认为leaf到当前最大是-100 而不是0. 所以base case需要斟酌。
    // base case 有三种情况。 而且传入的root不能是null（否则base case NPE），后续的base case不需要考虑NPE，因为已经检测过root.left\right 非null了才会继续递归。
// 如果左孩子是null，右孩子是-100.那么会认为leaf到当前最大是-100 而不是0. 所以base case需要斟酌。
    // base case 有三种情况。 而且传入的root不能是null（否则base case NPE），后续的base case不需要考虑NPE，因为已经检测过root.left\right 非null了才会继续递归。
    public int maxPathSumLeafToRoot(TreeNode root) {
        if (root.right == null && root.left == null) {
            return root.key;
        }
        if (root.right == null) {
            return root.key + maxPathSumLeafToRoot(root.left);
        }
        if (root.left == null) {
            return root.key + maxPathSumLeafToRoot(root.right);
        }
        return root.key + Math.max(maxPathSumLeafToRoot(root.right), maxPathSumLeafToRoot(root.left));
    }
}
