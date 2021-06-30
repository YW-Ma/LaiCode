package com.study.algorithms.class18_cross_training_1.LCA;

import com.study.util.TreeNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LCA_KNodes {
  // Follow up:
  // 如果有k个node，把它们的LCA找到，
  // 如果分析隶属关系就太复杂了，应该理解物理意义：
  // LCA(root, nodes)的含义是：
  //    represents, in the subtree rooted at root,
  //    the LCA of all input nodes.
  // 返回值含义：
  //    如果我是abcd中的一个，返回自己。
  //    如果左边非空、右边也非空，那么就返回自己。（子树范围内，我是LCA）
  //    如果有一边空，返回另一边。（子树范围内的LCA）


  // 思考方法：凡是k个xxx的问题：
  // 1. iterative reduction: 不要k个节点一起找，而是两个两个搞：(((a,b),c),d)。
  // 2. binary reduction: 找最大的和次大的那个puzzle。
  // 3. k-way reduction: 真的是k个一起做。

  // ---------------------------------------

  // 重听，用重听笔记：
  // TreeNode {int key; TreNode left; TreeNode right;}
  // must have all nodes
  // return the lca

  //     root
  //    / |||  \
  //   b1      bn
  //
  // what I expect from my child: tell me a LCA candidate (LCA or one of target, or null)
  // what I do: find out a LCA candidate
  // case 1: I am one of target nodes --> return myself
  // case 2: if got more than one --> return myself
  // case 3: if only one child found --> pass it (target or LCA)
  // case 4: all children return null --> return null

  // input:  Set<TreeNode>
  // output: LCA TreeNode

  public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
    // 最好是先变成set，不然影响时间复杂度
    Set<TreeNode> set = new HashSet<>(nodes);
    return helper(root, set);
  }

  private TreeNode helper(TreeNode root, Set<TreeNode> set) {
    if (root == null || set.contains(root)) {
      return root;
    }
    TreeNode left = helper(root.left, set);
    TreeNode right = helper(root.right, set);
    if (left != null && right != null) {
      return root;
    }
    return left == null ? right : left;
  }
}
